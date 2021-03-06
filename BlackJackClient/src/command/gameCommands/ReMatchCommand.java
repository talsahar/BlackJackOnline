package command.gameCommands;

import command.AbstractCommand;
import model.Model;
import src.Config;
import view.View;

public class ReMatchCommand extends AbstractCommand {

	public ReMatchCommand(View view, Model model) {
		super(Config.Crematch, view, model);
	}

	@Override
	public void execute() {
			myModel.getCliHandler().sendRematchOffer();
	}

}
