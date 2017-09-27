package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Properties;
import model.Model;
import src.Config;
import view.View;

public class WaitForClientStand extends AbstractCommand {

	public WaitForClientStand(View view, Model model) {
		super(Config.GRwaitForAnotherToStand, view, model);
	}

	@Override
	public void execute() {

		myModel.getProperties().setBigMessage("Wait for rival's stand");
		myView.getHitButton().setVisible(false);
		myView.getStandButton().setVisible(false);
	}

}
