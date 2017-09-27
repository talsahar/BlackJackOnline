package command.authroizeCommands;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import src.Config;
import view.View;

public class LoginSendCommand extends AbstractCommand {

	public LoginSendCommand(View view, Model model) {
		super(Config.loginCommand, view, model);
	}

	@Override
	public void execute() {
		if (params.size() == 2)
			myModel.getCliHandler().sendLogin(params.get(0), params.get(1));
	}
}
