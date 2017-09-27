package view;

import viewmodel.ViewModel;

public interface View {
	void setViewModel(ViewModel vm);
	void login();
	void runServer();
	void stopServer();
	void settings();
}
