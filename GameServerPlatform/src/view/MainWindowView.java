package view;

import java.net.URL;
import java.util.ResourceBundle;

import commands.RunnableList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import model.MyModel;
import mvvmConnector.MvvmConnector;
import viewmodel.MyViewModel;
import viewmodel.ViewModel;

public class MainWindowView implements View, Initializable {

	ViewModel viewModel;
	@FXML
	TextField userField;
	@FXML
	TextField passField;
	@FXML
	HBox listViewHBox;
	@FXML
	ListView listViewA;
	@FXML
	ListView listViewB;
	@FXML
	ListView listViewC;
	@FXML
	Label label;
	@FXML
	Button Blogin;
	@FXML
	Button Bsettings;
	@FXML
	Button Bstart;
	@FXML
	Button Bstop;

	@FXML
	Button BenterCommand;

	@FXML
	TextField commandLine;

	public MainWindowView() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new MvvmConnector().connect(this, MyViewModel.getInstance(), MyModel.getInstance());
		autoRun();
	}

	private void autoRun() {
		login();
		new Thread(() -> {

			try {
				Thread.sleep(2000);
				Platform.runLater(() -> this.runServer());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

	}

	public void textFieldKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			textFieldButton();
	}

	public void textFieldButton() {

		viewModel.sendCommand(commandLine.getText());
		commandLine.clear();
	}

	@Override
	public void login() {
		viewModel.onLogin(RunnableList.getInstance().add(() -> buttonsOnLogin()));

	}

	@Override
	public void runServer() {
		viewModel.onRunServer(RunnableList.getInstance().add(() -> controlButtons(true)));

	}

	@Override
	public void stopServer() {
		viewModel.onStopServer(RunnableList.getInstance().add(() -> controlButtons(false)));
	}

	@Override
	public void settings() {
		viewModel.onSettings();
	}

	private void buttonsOnLogin() {
		userField.setDisable(true);
		passField.setDisable(true);
		Blogin.setDisable(true);
		Bstart.setDisable(false);
	}

	// start?true:false
	private void controlButtons(boolean b) {

		listViewHBox.setDisable(!b);
		Bstart.setDisable(b);
		Bstop.setDisable(!b);
		BenterCommand.setDisable(!b);
		commandLine.setDisable(!b);

	}
	//******************
	//******************
	////getters+setters
	//******************
	//******************
	//******************
	
	
	public TextField getUserField() {
		return userField;
	}

	public TextField getPassField() {
		return passField;
	}

	public ListView getListViewA() {
		return listViewA;
	}

	public ListView getListViewB() {
		return listViewB;
	}

	public ListView getListViewC() {
		return listViewC;
	}

	public Label getLabel() {
		return label;
	}

	public TextField getCommandLine() {
		return commandLine;
	}

	@Override
	public void setViewModel(ViewModel vm) {
		this.viewModel = vm;

	}

}
