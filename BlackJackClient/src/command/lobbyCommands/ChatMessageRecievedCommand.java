package command.lobbyCommands;

import java.util.LinkedList;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import src.Config;
import view.User;
import view.View;

public class ChatMessageRecievedCommand extends AbstractCommand {

	public ChatMessageRecievedCommand(View view, Model model) {
		super(Config.RglobalMessage, view, model);
	}

	@Override
	public void execute() {

		StringBuilder builder = new StringBuilder("");
		while (!params.isEmpty())
			builder.append(params.remove(0)).append(" ");

		myModel.getChatMessages().update(builder.toString());

	}

}
