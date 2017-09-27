package model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.client.BJClient;
import model.client.CliHandler;
import model.client.ClientHandler;
import model.observableList.ChatObservableList;
import model.observableList.OnlineObservableList;
import src.Config;
import src.ConfigServer;
import view.User;

public class MyModel extends Observable implements Model, Observer {

	private ClientHandler clientHandler;
	private ChatObservableList chatMessages;
	private OnlineObservableList onlineUsers;
	private Properties properties;
	private CardImgLoader cardLoader;

	@Override
	public CardImgLoader getCardLoader() {
		return cardLoader;
	}

	

	private MyModel() {
		clientHandler = new ClientHandler(ConfigServer.serverIP, ConfigServer.serverPORT);
		clientHandler.getClient().addObserver(this);
		onlineUsers = new OnlineObservableList();
		chatMessages = new ChatObservableList();
		properties = new Properties();
		cardLoader = new CardImgLoader();
	}

	public Properties getProperties() {
		return properties;
	}

	@Override
	public CliHandler getCliHandler() {
		return clientHandler;
	}

	private static class Holder {
		public static final Model model = new MyModel();
	}

	public static Model getInstance() {
		return Holder.model;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);

	}

	

	public ChatObservableList getChatMessages() {
		return chatMessages;
	}

	public OnlineObservableList getOnlineUsers() {
		return onlineUsers;
	}

}
