package model.observableList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class AbstractObservableList<T> {
	protected ObservableList<T> list;
	
	public AbstractObservableList() {
		list = FXCollections.observableArrayList();
	}
	
	public ObservableList<T> getList(){
		return list;
	}

	
}
