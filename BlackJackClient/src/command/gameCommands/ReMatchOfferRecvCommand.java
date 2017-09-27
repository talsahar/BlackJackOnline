package command.gameCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import model.Model;
import src.Config;
import view.View;

public class ReMatchOfferRecvCommand extends AbstractCommand {

	public ReMatchOfferRecvCommand(View view, Model model) {
		super(Config.GRrematchOffer, view, model);
	}

	@Override
	public void execute() {

		if (!params.isEmpty()) {
			String invitor=params.remove(0);

			final FutureTask<Boolean> future = new FutureTask(() -> myView.getGameStartDialogs().rematchOffer());
			Platform.runLater(future);
			try {
				if (future.get()) {
					myModel.getCliHandler().reMatchAccept();
				} else 
					myModel.getCliHandler().reMatchreject();
				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}

}
