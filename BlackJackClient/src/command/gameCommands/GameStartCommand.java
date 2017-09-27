package command.gameCommands;

import command.AbstractCommand;
import controller.MyController;
import model.Model;
import src.Config;
import view.View;

public class GameStartCommand extends AbstractCommand {

	public GameStartCommand(View view, Model model) {
		super(Config.GRnewGameNotify, view, model);
	}

	@Override
	public void execute() {
		this.addObserver(MyController.getInstance());
		setChanged();
		notifyObservers(Config.clearGameData);
		myView.onGameStart();
	
	}

}
