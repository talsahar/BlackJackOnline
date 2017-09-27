package viewmodel;

import java.util.Observer;

import commands.RunnableList;
import javafx.beans.property.StringProperty;
import listview.LVHandler;
import model.Model;

public interface ViewModel extends Observer {
	void onLogin(RunnableList actionList);

	void onRunServer(RunnableList actionList);

	void onStopServer(RunnableList actionList);

	public void onSettings();


	public Model getModel();

	public StringProperty getIdField();

	public StringProperty getPassField();

	public StringProperty getLabelField();

	public LVHandler getListViewA();

	public LVHandler getListViewB();

	public LVHandler getListViewC();
	public StringProperty getTextField();

	void sendCommand(String string);

}
