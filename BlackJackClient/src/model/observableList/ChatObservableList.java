package model.observableList;

import java.util.LinkedList;
import java.util.stream.Collectors;

import javafx.application.Platform;
import model.MyModel;
import view.User;

public class ChatObservableList extends AbstractObservableList<String> {

	public void update(String line) {
		Platform.runLater(() -> list.add(line));

	}

}
