package blackjack;

import java.util.Observable;
import java.util.function.Consumer;

import clienthandler.GameClientHandler;
import server.broker.GameBroker;
import src.Config;

public class BlackJackBroker extends GameBroker{

	BlackJackModel model;

	public BlackJackBroker(GameClientHandler c1, GameClientHandler c2, Consumer<GameClientHandler> onGameOver) {
		super(c1, c2, onGameOver);
		model = new BlackJackModel(c1, c2);
		model.addObserver(this);

	}

	@Override
	public void startGame() {
		model.startGame();
		

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		arg0.deleteObserver(this);
		gameOver();
		

	}



}
