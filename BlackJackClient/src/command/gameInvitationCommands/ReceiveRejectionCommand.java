package command.gameInvitationCommands;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Model;
import model.MyModel;
import src.Config;
import view.User;
import view.View;

public class ReceiveRejectionCommand extends AbstractCommand {

	public ReceiveRejectionCommand(View view, Model model) {
		super(Config.Rreject, view, model);
	}

	@Override
	public void execute() {

		if (!params.isEmpty())
			myModel.getProperties().setLabel(params.get(0) + " rejected your invitation");

	}

}
