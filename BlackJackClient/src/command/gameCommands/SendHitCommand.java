package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Properties;
import model.Model;
import src.Config;
import view.View;

public class SendHitCommand extends AbstractCommand {

	public SendHitCommand(View view, Model model) {
		super(Config.GChit, view, model);
	}

	@Override
	public void execute() {

	myModel.getCliHandler().sendHit();

	}

}
