package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import view.other.GameStartDialogs;

public interface View {


	void showLobby(String username);

	void onGameStart();

	void notify(String msg);
//getters+setters;
	GameStartDialogs getGameStartDialogs();

	TableView<User> getOnlineTable();

	ListView<String> getChatView();

	Text getMyCashText();

	Text getCurrentBetText();

	Text getRivalNameText();

	Text getMyNameText();

	Text getRivalCashText();

	Text getBigGameMessage();



	GameView getDisplayer();

	Label getLabel();

	ImageView getHitButton();

	ImageView getStandButton();

	Node getToLobbyButton();

	Node getPlayAgainButton();



}
