package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import controller.MyController;
import javafx.application.Platform;
import model.Model;
import src.Config;
import view.View;

public class ExitAcceptCommand extends AbstractCommand {

	public ExitAcceptCommand(View view, Model model) {
		super(Config.RexitAccept, view, model);
	}

	@Override
	public void execute() {

	
		MyController.getInstance().stop();
		Platform.exit();
	}

}
