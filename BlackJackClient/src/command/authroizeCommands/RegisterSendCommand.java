package command.authroizeCommands;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import src.Config;
import view.View;

public class RegisterSendCommand extends AbstractCommand {
	public RegisterSendCommand(View view, Model model) {
		super(Config.registerCommand, view, model);
	}

	@Override
	public void execute() {

		if (params.size() == 2)
			myModel.getCliHandler().sendRegister(params.get(0), params.get(1));

	}
}
