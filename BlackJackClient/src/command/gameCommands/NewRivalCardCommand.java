package command.gameCommands;

import java.awt.image.ImageProducer;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import command.AbstractCommand;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import model.Properties;
import model.CardImgLoader;
import model.Model;
import src.Config;
import view.View;

public class NewRivalCardCommand extends AbstractCommand {

	public NewRivalCardCommand(View view, Model model) {
		super(Config.GRnewRivalCard, view, model);
	}

	@Override
	public void execute() {
		myModel.getCardLoader().newRivalCard();
		myView.getDisplayer().drawRivalCard(myModel.getCardLoader().getImage("unknown"),
				myModel.getCardLoader().getRivalX());

	}

}
