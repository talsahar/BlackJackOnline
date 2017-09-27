package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import controller.MyController;
import javafx.application.Platform;
import model.Properties;
import model.Model;
import src.Config;
import view.View;

public class SendMeToLobby extends AbstractCommand {

	public SendMeToLobby(View view, Model model) {
		super(Config.GCtoLobby, view, model);
	}

	@Override
	public void execute() {
		myModel.getCliHandler().getMeToLobby();
		this.addObserver(MyController.getInstance());
		setChanged();
		notifyObservers(Config.clearGameData);
		myView.showLobby(myModel.getProperties().getMyName().get());
	}

}
