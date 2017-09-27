package view;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import controller.MyController;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.MyModel;
import src.Config;
import view.other.AuthenticationDialogs;
import view.other.ButtonImageLoader;
import view.other.GameStartDialogs;
import view.other.MvcBuilder;
import view.other.OnlineUsersTableBuilder;

public class MainWindowController extends Observable implements View, Initializable {
	@FXML
	GameView gameDisplayer;
	@FXML
	ImageView exitButton;

	@FXML
	AnchorPane authenticationPane;
	@FXML
	ImageView registerButton;
	@FXML
	ImageView loginButton;
	@FXML
	AnchorPane lobbyPane;
	@FXML
	TableView<User> onlineTable;

	@FXML
	ListView<String> chatView;
	@FXML
	TextField chatTextField;

	@FXML
	Label label;

	// game
	@FXML
	AnchorPane gamePane;
	@FXML
	ImageView standButton;
	@FXML
	ImageView hitButton;
	@FXML
	Text myCashText;
	@FXML
	Text currentBetText;
	@FXML
	Text rivalNameText;
	@FXML
	Text myNameText;
	@FXML
	Text rivalCashText;
	@FXML
	Text bigGameMessage;
	@FXML
	ImageView playAgainButton;

	@FXML
	ImageView lobbyButton;

	private AuthenticationDialogs authenticationDialogs;
	private GameStartDialogs gameStartDialogs;

	// *******************************
	// initialize
	// *******************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		notify(Config.bindCommand);
		gameDisplayer.setVisible(true);
		showLogin();
		onlineTable = new OnlineUsersTableBuilder(onlineTable).buildColumns()
				.defineDoubleClickedMethod(user -> inviteUser(user)).build();
		new ButtonImageLoader().loadLoginButton(loginButton).loadRegisterButton(registerButton).loadHitButton(hitButton)
				.loadStandButton(standButton).loadExitButton(exitButton).loadPlayAgainButton(playAgainButton)
				.loadLobbyButton(lobbyButton);
	}

	public MainWindowController() {
		new MvcBuilder().build(this, MyController.getInstance(), MyModel.getInstance());
		authenticationDialogs = new AuthenticationDialogs();
		gameStartDialogs = new GameStartDialogs();
	}

	// *******************************
	// LOGIN
	// *******************************

	public void showLogin() {
		authenticationPane.setVisible(true);
		lobbyPane.setVisible(false);
		gamePane.setVisible(false);
		gameDisplayer.drawLogin();

	}

	public void login() {
		authenticationDialogs
				.onLogin(pair -> notify(Config.registerCommand + " " + pair.getKey() + " " + pair.getValue()));

	}

	public void register() {
		authenticationDialogs
				.onRegister(pair -> notify(Config.loginCommand + " " + pair.getKey() + " " + pair.getValue()));
	}

	// *******************************
	// LOBBY
	// *******************************

	@Override
	public void showLobby(String username) {
		authenticationPane.setVisible(false);
		gamePane.setVisible(false);
		lobbyPane.setVisible(true);
		gameDisplayer.showLobby();

	}

	public void inviteUser(User user) {
		if (gameStartDialogs.inviteDialog(user.getName()))
			notify(Config.inviteCommand + " " + user.getName());

	}

	public void chatFieldKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			send();

	}

	public void send() {
		String s = chatTextField.getText();
		if (!s.isEmpty()) {

			notify(Config.messageCommand + " " + s);
			chatTextField.clear();
		}
	}

	// *******************************
	// GAME
	// *******************************

	@Override
	public void onGameStart() {
		lobbyPane.setVisible(false);
		authenticationPane.setVisible(false);
		playAgainButton.setVisible(false);
		lobbyButton.setVisible(false);
		hitButton.setVisible(true);
		standButton.setVisible(true);
		gamePane.setVisible(true);
		gameDisplayer.drawGame();
	}

	public void onStand() {

		notify(Config.GCstand);
	}

	public void onHit() {
		notify(Config.GChit);
	}

	public void onPlayAgainButton() {
		notify(Config.Crematch);

	}

	public void onExitButton() {
		notify(Config.Cexit);
	}

	public void onLobbyButton() {
		notify(Config.GCtoLobby);
	}
	// *******************************
	// OTHER
	// *******************************

	@Override
	public void notify(String msg) {
		setChanged();
		notifyObservers(msg);
	}

	// *******************************
	// GETTERS+SETTERS
	// *******************************

	@Override
	public GameStartDialogs getGameStartDialogs() {
		return gameStartDialogs;
	}

	@Override
	public TableView<User> getOnlineTable() {
		return onlineTable;
	}

	@Override
	public ListView<String> getChatView() {
		return chatView;
	}

	@Override
	public Text getMyCashText() {
		return myCashText;
	}

	@Override
	public Text getCurrentBetText() {
		return currentBetText;
	}

	@Override
	public Text getRivalNameText() {
		return rivalNameText;
	}

	@Override
	public Text getMyNameText() {
		return myNameText;
	}

	@Override
	public Text getRivalCashText() {
		return rivalCashText;
	}

	@Override
	public Text getBigGameMessage() {
		return bigGameMessage;
	}

	@Override
	public GameView getDisplayer() {
		return gameDisplayer;
	}

	@Override
	public Label getLabel() {
		return label;
	}

	@Override
	public ImageView getStandButton() {
		return standButton;
	}

	@Override
	public ImageView getHitButton() {
		return hitButton;
	}

	@Override
	public Node getToLobbyButton() {
		return lobbyButton;
	}

	@Override
	public Node getPlayAgainButton() {
		return playAgainButton;
	}

}
