package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Model;
import src.Config;
import view.View;

public class ExitCommand extends AbstractCommand {

	public ExitCommand(View view, Model model) {
		super(Config.Cexit, view, model);
	}

	@Override
	public void execute() {

		myModel.getCliHandler().sendExit();
		
	}

}
