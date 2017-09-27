package blackjack;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.function.Consumer;

import api.MyBlackJackScoreAPI;
import clienthandler.GameClientHandler;
import src.Config;

public class BlackJackModel extends Observable implements Observer {

	private GameClientHandler cli1, cli2;
	Hand hand1, hand2;
	double currentBet;
	private CardStack stack;
	boolean coin;

	public BlackJackModel(GameClientHandler cli1, GameClientHandler cli2) {
		stack = new CardStack();
		this.cli1 = cli1;
		this.cli2 = cli2;
		doubleConsumer(cli -> cli.addObserver(this));
		this.currentBet = 0;
		hand1 = new Hand();
		hand2 = new Hand();
		coin = false;
	}

	public void startGame() {

		doubleConsumer(cli -> getHandByHandler(cli).setStand(false));
		doubleConsumer(cli -> cli.send(Config.GRnewGameNotify));
		doubleConsumer(cli -> cli.send(Config.GRscore + " " + cli.getClientName() + ":" + cli.getScore() + " "
				+ getOther(cli).getClientName() + ":" + getOther(cli).getScore()));
		betRequest();
	}

	public void betRequest() {
		GameClientHandler c1 = null;
		if (coin)
			c1 = cli1;
		else
			c1 = cli2;
		c1.send(Config.GRreqBet);
		getOther(c1).send(Config.GRwaitForBet);
		coin = !coin;

	}

	public void newBetArrived(double bet) {
		currentBet = bet;
		doubleConsumer(cli -> cli.setScore(cli.getScore() - bet));
		doubleConsumer(cli -> cli.send(Config.GRtheBetIs + " " + bet));
		takeFirstCards();
		sendCards();
	}

	public void takeFirstCards() {
		do {
			doubleConsumer(cli -> getHandByHandler(cli).clear());
			stack.init();
			doubleConsumer(cli -> getHandByHandler(cli).add(stack.pop(), stack.pop()));
		} while (!hand2.isLegal() || !hand1.isLegal());
	}

	public void sendCards() {
		doubleConsumer(cli -> cli.send(Config.GRnewHand + " " + getHandByHandler(cli).toString() + " "
				+ getHandByHandler(getOther(cli)).unknownToString()));

	}

	public void onHit(GameClientHandler cli) {
		if (!getHandByHandler(cli).isStand()) {
			Hand hand = getHandByHandler(cli);
			String card = stack.pop();
			hand.add(card);
			cli.send(Config.GRnewPlayerCard + " " + card);
			getOther(cli).send(Config.GRnewRivalCard);
			if (!hand.isLegal())
				onStand(cli);
		}
	}

	public void onStand(GameClientHandler cli) {
		if (!getHandByHandler(cli).isStand()) {
			getHandByHandler(cli).setStand(true);
			if (!checkStandStatus()) {
				cli.send(Config.GRwaitForAnotherToStand);
			} else {
				GameClientHandler winner = winnerBow();
				if (winner == null) {
					draw();
				} else {
					onWin(winner);
					lose(getOther(winner));
				}
			}
		}
	}

	private GameClientHandler winnerBow() {
		GameClientHandler winner = null;
		int sum1 = hand1.getSum();
		int sum2 = hand2.getSum();

		if (sum1 == sum2)
			return null;
		if (sum1 > 21 && sum2 > 21)
			return null;
		if (sum1 > 21 && sum2 <= 21)
			return cli2;
		if (sum2 > 21 && sum1 <= 21)
			return cli1;

		if (sum1 > sum2)
			return cli1;
		return cli2;

	}

	private void onWin(GameClientHandler cli) {
		cli.send(Config.GRGameoverStatus + " " + 1 + " " + currentBet * 2 + " " + getHandByHandler(cli).toString() + " "
				+ getHandByHandler(getOther(cli)).toString());
		cli.setScore(cli.getScore() + currentBet * 2);
		currentBet=0;
	}

	private void lose(GameClientHandler cli) {
		cli.send(Config.GRGameoverStatus + " " + -1 + " " + 0 + " " + getHandByHandler(cli).toString() + " "
				+ getHandByHandler(getOther(cli)).toString());
		currentBet=0;
	}

	private void draw() {
		doubleConsumer(cli -> cli.send(Config.GRGameoverStatus + " " + 0 + " " + (currentBet) + " "
				+ getHandByHandler(cli).toString() + " " + getHandByHandler(getOther(cli)).toString()));
		currentBet=0;
	}

	public void update(Observable o, Object arg) {

		GameClientHandler ready = (GameClientHandler) o;
		String tmp = (String) arg;
		String[] args = tmp.split(" ");
		// bet
		if (args[0].equals(Config.GCbet)) {
			newBetArrived(Double.parseDouble(args[1]));

		}
		if (args[0].equals(Config.GChit)) {

			onHit(ready);

		}
		if (args[0].equals(Config.GCstand)) {
			onStand(ready);
		}

		if (args[0].equals(Config.GCtoLobby)) {
			toLobby(ready);
		}

		if (args[0].equals(Config.Crematch)) {
			ready.send(Config.RSimpleMessage + " " + "wait for rival's respond");
			getOther(ready).send(Config.GRrematchOffer + " " + ready.getClientName());
		}
		if (args[0].equals(Config.CrematchAccept)) {
			startGame();
		}
		if (args[0].equals(Config.CrematchReject)) {
			toLobby(ready);
		}
		if (args[0].equals(Config.Cexit)) {
			onExit(ready);
		}

	}

	private void onExit(GameClientHandler ready) {
		if(currentBet!=0) {
			onWin(getOther(ready));
		}
		ready.close();
toLobby(ready);
}

	private void toLobby(GameClientHandler ready) {
		getOther(ready).send(Config.GCtoLobby);
		getOther(ready).send(Config.RglobalMessage + " " + ready.getClientName() + " left");
		ready.send(Config.RglobalMessage + " " + "You have left to the lobby");
		doubleConsumer(cli -> cli.deleteObserver(this));
		setChanged();
		notifyObservers();
	}

	private boolean checkStandStatus() {
		return (hand1.isStand() && hand2.isStand()) ? true : false;
	}

	private Hand getHandByHandler(GameClientHandler cli) {
		return cli == cli1 ? hand1 : hand2;
	}

	public GameClientHandler getOther(GameClientHandler cli) {
		return (cli == cli1) ? cli2 : cli1;
	}

	public void doubleConsumer(Consumer<GameClientHandler> func) {
		func.accept(cli1);
		func.accept(cli2);

	}

}
