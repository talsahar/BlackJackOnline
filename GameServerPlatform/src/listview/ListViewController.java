package listview;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ListViewController {

	ArrayList<Consumer<String>> addArray;
	ArrayList<Consumer<String>> removeArray;

	public ListViewController(ListViewHandler... list) {
		addArray = new ArrayList<>();
		removeArray = new ArrayList<>();
		for (ListViewHandler l : list) {
			addArray.add(s -> l.add(s));
			removeArray.add(s -> l.remove(s));
		}
	}

	public void addTo(int index, String... str) {
		for (String s : str)
			addArray.get(index).accept(s);

	}

	public void removeFrom(int index, String... str) {
		for (String s : str)
			removeArray.get(index).accept(s);
	}

}
