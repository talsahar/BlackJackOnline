package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.other.ImageCreator;

public class GameView extends Canvas {
	private GraphicsContext gc;
	private ImageCreator imageCreator;
	int nextPlayerYCard,nextRivalYCard;
	int cardWidth,cardHeight;
	
	//rival x came from model
	public GameView() {
		gc = getGraphicsContext2D();
		imageCreator = new ImageCreator();
		cardWidth=86;
		cardHeight=125;
		//nextPlayerXCard=220;
		nextPlayerYCard=235;
		//nextRivalXCard=313;
		nextRivalYCard=29;
	}

	private void drawBackground(String s) {
		Image image = imageCreator.create(s);
		gc.drawImage(image, 0, 0, getWidth(), getHeight());
	}

	public void drawLogin() {
		drawBackground("/blackjack.jpg");

	}

	public void showLobby() {
		drawBackground("/lobbyBackground.png");
	}

	public void drawGame() {
		gc.clearRect(0, 0, getWidth(), getHeight());
		drawBackground("/gameBackground.png");
		
	}

	public void drawPlayerCard(Image image,int x) {
		
		gc.drawImage(image, x, nextPlayerYCard,cardWidth, cardHeight);
		//nextPlayerXCard+=50;

	}

	public void drawRivalCard(Image image,int x) {
		gc.drawImage(image, x, nextRivalYCard,cardWidth, cardHeight);
	//	nextRivalXCard-=50;
	}

}
