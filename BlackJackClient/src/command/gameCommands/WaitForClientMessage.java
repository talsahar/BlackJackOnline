package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Properties;
import model.Model;
import src.Config;
import view.View;

public class WaitForClientMessage extends AbstractCommand {

	public WaitForClientMessage(View view, Model model) {
		super(Config.GRwaitForBet, view, model);
	}

	@Override
	public void execute() {

	myModel.getProperties().setBigMessage("Wait for rival's bet");

	}

}
