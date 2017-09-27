package command.lobbyCommands;

import java.util.LinkedList;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import src.Config;
import view.User;
import view.View;

public class ChatMessageSendCommand extends AbstractCommand {


	public ChatMessageSendCommand(View view,Model model) {
		super(Config.CsendGlobalMessage,view,model);	}

	@Override
	public void execute() {

			
			LinkedList<User> list = new LinkedList<>();
			StringBuilder builder=new StringBuilder(myModel.getProperties().getMyName().get()+": ");
			while (!params.isEmpty())
				builder.append(params.remove(0)).append(" ");
			
			myModel.getCliHandler().sendChatMessage(builder.toString());
		

	}

}
