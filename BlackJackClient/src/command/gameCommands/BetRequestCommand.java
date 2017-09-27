package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Properties;
import model.Model;
import src.Config;
import view.View;

public class BetRequestCommand extends AbstractCommand {

	public BetRequestCommand(View view, Model model) {
		super(Config.GRreqBet, view, model);
	}

	@Override
	public void execute() {

		final FutureTask<Double> future = new FutureTask(() -> myView.getGameStartDialogs()
				.enterBetDialog(myModel.getProperties().getMinCash(), "Enter Bet"));
		Platform.runLater(future);
		try {
			myModel.getCliHandler().sendBet(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
