package command.lobbyCommands;

import java.util.LinkedList;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import src.Config;
import view.User;
import view.View;

public class RecvLabelMsgCommand extends AbstractCommand {


	public RecvLabelMsgCommand(View view,Model model) {
		super(Config.RSimpleMessage,view,model);	}

	@Override
	public void execute() {

			StringBuilder sb = new StringBuilder("");
			while (!params.isEmpty())
				sb.append(params.remove(0)).append(" ");
			myModel.getProperties().setLabel(sb.toString());

	}

}
