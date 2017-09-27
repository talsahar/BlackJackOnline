package command.gameInvitationCommands;

import java.util.LinkedList;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import src.Config;
import view.User;
import view.View;

public class SendInvationCommand extends AbstractCommand {

	public SendInvationCommand(View view,Model model) {
		super(Config.inviteCommand,view,model);}

	@Override
	public void execute() {
			if (!params.isEmpty()) {
				myModel.getCliHandler().invite(params.get(0));
				myModel.getProperties().setLabel("An invitation sent to " + params.remove(0));
			}
			

	}

}
