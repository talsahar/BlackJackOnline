package model;

import java.util.Observer;

import commands.RunnableList;
import listview.ListViewController;

public interface Model extends Observer {
	public void adminLogin(String user, String pass, RunnableList actionList);

	public void runServer(RunnableList actionList, ListViewController listViewConsumers);

	public void stopServer(RunnableList actionList);

	public void analyzeCommand(String str);
}
