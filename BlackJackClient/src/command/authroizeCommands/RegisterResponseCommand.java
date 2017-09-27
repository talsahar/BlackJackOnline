package command.authroizeCommands;

import command.AbstractCommand;
import model.Model;
import src.Config;
import view.View;

public class RegisterResponseCommand extends AbstractCommand {

	public RegisterResponseCommand(View view, Model model) {
		super(Config.RregisterSucceed, view, model);
	}

	@Override
	public void execute() {
		myModel.getProperties().setLabel("You have been successfully registered");

	}
}
