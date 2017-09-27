package listview;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class LVHandler implements ListViewHandler {

	private ObservableList<String> list;
	
	public LVHandler() {
		list = FXCollections.observableArrayList();
	}
	
	@Override
	public void add(String s) {
Platform.runLater(()->list.add(s));
	}

	@Override
	public void remove(String s) {
		Platform.runLater(()->list.removeIf(it -> it.startsWith(s)));
	}

	public void addObserver(ListView listView) {
		listView.setItems(list);		
	}

}
