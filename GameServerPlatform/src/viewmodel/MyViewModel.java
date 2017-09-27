package viewmodel;

import java.util.Observable;

import commands.RunnableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import listview.LVHandler;
import listview.ListViewController;
import model.Model;
import src.ConfigServer;

public class MyViewModel implements ViewModel {

	private Model model;
	private StringProperty idField;
	private StringProperty passField;
	private StringProperty labelField;
	private StringProperty textField;
	private LVHandler listViewA;
	private LVHandler listViewB;
	private LVHandler listViewC;

	private MyViewModel() {
		idField = new SimpleStringProperty();
		passField = new SimpleStringProperty();
		labelField = new SimpleStringProperty();
		textField = new SimpleStringProperty();
		listViewA = new LVHandler();
		listViewB = new LVHandler();
		listViewC = new LVHandler();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		labelField.set((String) arg1);

	}

	@Override
	public void onLogin(RunnableList actionList) {
		model.adminLogin(idField.get(), passField.get(),
				actionList.add(() -> labelField.set("Welcome back administrator")));
	}

	@Override
	public void onRunServer(RunnableList actionList) {
		model.runServer(actionList.add(() -> labelField.set("server runs on port " + ConfigServer.serverPORT)),
				new ListViewController(listViewA, listViewB, listViewC));
	}

	@Override
	public void onStopServer(RunnableList actionList) {
		model.stopServer(actionList.add(() -> labelField.set("server closed for connections")));
	}

	@Override
	public void onSettings() {

	}

	
	// getters+setters
	@Override
	public Model getModel() {
		return model;
	}

	@Override
	public StringProperty getIdField() {
		return idField;
	}

	@Override
	public StringProperty getPassField() {
		return passField;
	}

	@Override
	public StringProperty getLabelField() {
		return labelField;
	}

	@Override
	public LVHandler getListViewA() {
		return listViewA;
	}

	@Override
	public LVHandler getListViewB() {
		return listViewB;
	}

	@Override
	public LVHandler getListViewC() {
		return listViewC;
	}

	public StringProperty getTextField() {
		return textField;
	}

	public void setModel(Model model) {
		this.model = model;

	}

	@Override
	public void sendCommand(String str) {
		model.analyzeCommand(str);
	}

	public static class holder {
		public static final MyViewModel instance = new MyViewModel();

	}

	public static MyViewModel getInstance() {
		return holder.instance;
	}

}
