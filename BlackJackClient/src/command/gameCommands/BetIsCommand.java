package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Properties;
import model.Model;
import src.Config;
import view.View;

public class BetIsCommand extends AbstractCommand {

	public BetIsCommand(View view, Model model) {
		super(Config.GRtheBetIs, view, model);
	}

	@Override
	public void execute() {

		if (!params.isEmpty()) {
			double bet = Double.parseDouble(params.remove(0));
			myModel.getProperties().setBigMessage("");
			myModel.getProperties().setCurrentBet(bet);
			myModel.getProperties().updateRivalCash(bet*-1);
			myModel.getProperties().updateMyCash(bet*-1);
		}

	}

}
