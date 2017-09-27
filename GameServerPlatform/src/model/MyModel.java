package model;

import java.util.Observable;

import api.AuthenticationAPI;
import api.MyAuthenticationAPI;
import commands.RunnableList;
import listview.ListViewController;
import server.MainServer;
import src.Config;

public class MyModel extends Observable implements Model {

	private MainServer server;

	@Override
	public void adminLogin(String user, String pass, RunnableList actionList) {
		AuthenticationAPI auth = new MyAuthenticationAPI();
		if (auth.AdminLogin(user, pass))
			actionList.runAll();

	}

	@Override
	public void runServer(RunnableList actionList, ListViewController listViewConsumers) {
		if (server == null) {
			server = new MainServer(listViewConsumers);
			server.addObserver(this);
		}
		server.start(actionList);
	}

	@Override
	public void stopServer(RunnableList actionList) {
		if (server != null)
			server.stop(actionList);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void analyzeCommand(String str) {

		server.sendToAll(Config.RglobalMessage + " Admin: " + str);

	}

	private MyModel() {
		
	}
	
	public static class holder {
		public static final MyModel instance = new MyModel();

	}

	public static MyModel getInstance() {
		return holder.instance;
	}

}
