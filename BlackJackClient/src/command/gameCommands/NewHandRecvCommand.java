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

public class NewHandRecvCommand extends AbstractCommand {

	public NewHandRecvCommand(View view, Model model) {
		super(Config.GRnewHand, view, model);
	}

	@Override
	public void execute() {
		if (params.size() == 2) {
			CardImgLoader imageHolder = myModel.getCardLoader();
			String my = params.remove(0);
			String rival = params.remove(0);
			System.out.println(my);
			System.out.println(rival);
			String[] myHand = my.split(":");
			String[] rivalHand = rival.split(":");
			for (String s : myHand) {
				imageHolder.newPlayerCard();
				myView.getDisplayer().drawPlayerCard(imageHolder.getImage(s), imageHolder.getPlayerX());
			}
			imageHolder.newRivalCard();
			myView.getDisplayer().drawRivalCard(imageHolder.getImage(rivalHand[0]), imageHolder.getRivalX());
			imageHolder.newRivalCard();
			myView.getDisplayer().drawRivalCard(imageHolder.getImage("unknown"), imageHolder.getRivalX());

		}

	}
}
