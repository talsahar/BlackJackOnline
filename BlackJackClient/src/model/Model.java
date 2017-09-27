package model;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import model.client.CliHandler;
import model.observableList.ChatObservableList;
import model.observableList.OnlineObservableList;
import view.User;

public interface Model {
	ChatObservableList getChatMessages();

	OnlineObservableList getOnlineUsers();

	CliHandler getCliHandler();


	Properties getProperties();



	CardImgLoader getCardLoader();

}
