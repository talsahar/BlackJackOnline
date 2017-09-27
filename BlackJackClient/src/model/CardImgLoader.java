package model;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import view.other.ImageCreator;

public class CardImgLoader {

	ImageCreator imageCreator;
	int playerX;
	int rivalX;

	public CardImgLoader() {
		imageCreator = new ImageCreator();
		init();
	}

	public void init() {
		playerX = 180;
		rivalX = 353;
	}

	public void newPlayerCard() {
		playerX += 40;
	}

	public void newRivalCard() {
		rivalX -= 40;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getRivalX() {
		return rivalX;
	}

	public Image getImage(String card) {
		return imageCreator.create("/smallCards/" + card + ".jpg");
	}

}
