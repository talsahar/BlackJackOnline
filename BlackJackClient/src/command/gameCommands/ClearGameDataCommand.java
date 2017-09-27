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

public class ClearGameDataCommand extends AbstractCommand {

	public ClearGameDataCommand(View view, Model model) {
		super(Config.clearGameData, view, model);
	}

	@Override
	public void execute() {
		
		myModel.getProperties().clear();
		myModel.getCardLoader().init();
		myView.getDisplayer().drawGame();
		
		}



}
