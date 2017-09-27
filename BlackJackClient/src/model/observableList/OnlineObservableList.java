package model.observableList;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import model.MyModel;
import view.User;

public class OnlineObservableList extends AbstractObservableList<User> {

	public void update(LinkedList<User> users,String except) {
		list.removeIf(user -> !users.contains(user));
		users.forEach(u->list.removeIf(u2->u2.collusion(u)));
		list.addAll(users.parallelStream().filter(user -> !list.contains(user)).filter(user->!user.getName().equals(except)).collect(Collectors.toList()));
		
	}

}
