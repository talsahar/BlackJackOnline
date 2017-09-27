package command.lobbyCommands;

import java.util.LinkedList;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import src.Config;
import view.User;
import view.View;

public class UpdateUserListCommand extends AbstractCommand {

	public UpdateUserListCommand(View view, Model model) {
		super(Config.Ruserlist, view, model);
	}

	@Override
	public void execute() {

			LinkedList<User> list = new LinkedList<>();

			while (!params.isEmpty()) {
				String user=params.remove(0);
				String score=params.remove(0);
				list.add(new User(user,Double.parseDouble(score)));
			}
				
			myModel.getOnlineUsers().update(list,myModel.getProperties().getMyName().getValue());

	}

}
