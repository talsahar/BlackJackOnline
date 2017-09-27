package command.gameInvitationCommands;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Model;
import model.MyModel;
import src.Config;
import view.User;
import view.View;

public class ReceiveInvationCommand extends AbstractCommand {

	public ReceiveInvationCommand(View view,Model model) {
		super(Config.Rinvite,view,model);	}

	@Override
	public void execute() {

			if (!params.isEmpty()) {
				String invitor=params.remove(0);

				final FutureTask<Boolean> future = new FutureTask(() -> myView.getGameStartDialogs().rematchOffer());
				Platform.runLater(future);
				try {
					if (future.get()) {
						myModel.getCliHandler().accept(invitor);
					} else 
						myModel.getCliHandler().reject(invitor);
					
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}


	}

}
