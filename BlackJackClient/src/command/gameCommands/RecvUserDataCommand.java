package command.gameCommands;

import command.AbstractCommand;
import model.Properties;
import model.Model;
import src.Config;
import view.View;

public class RecvUserDataCommand extends AbstractCommand {

	public RecvUserDataCommand(View view, Model model) {
		super(Config.GRscore, view, model);
	}

	@Override
	public void execute() {

		if (params.size() == 2) {// name1:score1 name2:score2
			String[] local = params.remove(0).split(":");
			String[] remote = params.remove(0).split(":");
			myModel.getProperties().setMyName(local[0]);
			myModel.getProperties().setMyCash(Double.parseDouble(local[1]));
			myModel.getProperties().setRivalName(remote[0]);
			myModel.getProperties().setRivalCash(Double.parseDouble(remote[1]));
		}

	}

}
