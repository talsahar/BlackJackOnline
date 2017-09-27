package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import controller.MyController;
import javafx.application.Platform;
import model.Properties;
import model.CardImgLoader;
import model.Model;
import src.Config;
import view.View;

public class GameOverStatusCommand extends AbstractCommand {

	public GameOverStatusCommand(View view, Model model) {
		super(Config.GRGameoverStatus, view, model);
	}

	@Override
	public void execute() {
		switch (Integer.parseInt(params.remove(0))) {
		case 1:
			myModel.getProperties().setBigMessage("You Won");
			break;
		case -1:
			myModel.getProperties().setBigMessage("You Lose");
			break;
		case 0:
			myModel.getProperties().setBigMessage("Draw");
			break;

		}
		myModel.getProperties().updateMyCash(Double.parseDouble(params.remove(0)));

		String[] playerHand=params.remove(0).split(":");
		String[] rivalHand=params.remove(0).split(":");
		
		myView.getDisplayer().drawGame();
		CardImgLoader loader=myModel.getCardLoader();
		loader.init();
		for(String s:playerHand) {
		System.out.println(s);
			loader.newPlayerCard();
			myView.getDisplayer().drawPlayerCard(loader.getImage(s), loader.getPlayerX());
		}

		for(String s:rivalHand) {
			loader.newRivalCard();
			myView.getDisplayer().drawRivalCard(loader.getImage(s), loader.getRivalX());
		}
		
		this.addObserver(MyController.getInstance());
		setChanged();
		notifyObservers(Config.gameOverCommand);

	}

}
