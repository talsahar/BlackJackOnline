package command.gameCommands;

import command.AbstractCommand;
import model.Model;
import src.Config;
import view.View;

public class GameOverCommand extends AbstractCommand {

	public GameOverCommand(View view, Model model) {
		super(Config.gameOverCommand, view, model);
	}

	@Override
	public void execute() {

		myView.getHitButton().setVisible(false);
		myView.getStandButton().setVisible(false);
		myView.getPlayAgainButton().setVisible(true);
		myView.getToLobbyButton().setVisible(true);
		
	}

}
