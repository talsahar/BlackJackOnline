package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Properties;
import model.Model;
import src.Config;
import view.View;

public class SendStandCommand extends AbstractCommand {

	public SendStandCommand(View view, Model model) {
		super(Config.GCstand, view, model);
	}

	@Override
	public void execute() {

	myModel.getCliHandler().sendStand();

	}

}
