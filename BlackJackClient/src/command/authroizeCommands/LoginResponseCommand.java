package command.authroizeCommands;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import model.Properties;
import src.Config;
import view.View;

public class LoginResponseCommand extends AbstractCommand {

	public LoginResponseCommand(View view, Model model) {
		super(Config.RloginSucceed, view, model);
	}

	@Override
	public void execute() {
		if (!params.isEmpty()) {
			String user = params.remove(0);
			String amount = params.remove(0);
			myModel.getProperties().setLabel(user + ", you have been successfully logged in");
			myModel.getProperties().setMyName(user);
			myModel.getProperties().setMyCash(Double.parseDouble(amount));
			myView.showLobby(user);

		}

	}
}
