package server;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;

import api.MyBlackJackScoreAPI;
import clienthandler.GameClientHandler;
import commands.RunnableList;
import listview.ListViewController;
import server.broker.BrokerManager;
import server.dispatcher.Dispatcher;
import server.login.UserLogin;
import server.proxy.ProxyServer;
import src.Config;

public class MainServer extends Observable implements Observer {
	private ListViewController listViewConsumers;
	private ProxyServer proxy;
	private UserLogin userLogin;
	private Dispatcher dispatcher;
	private BrokerManager brokerManager;
	private boolean isRunning;
	MyBlackJackScoreAPI scoreDb;

	public MainServer(ListViewController listViewConsumers) {
		this.listViewConsumers = listViewConsumers;
		proxy = new ProxyServer(onNewConnection);
		userLogin = new UserLogin(onLogin,(cli) -> listViewConsumers.removeFrom(1, cli.getClientName()));
		dispatcher = new Dispatcher(onGameStart, (cli) -> listViewConsumers.removeFrom(1, cli.getClientName()));

		brokerManager = new BrokerManager(onGameOver);
		scoreDb = new MyBlackJackScoreAPI();
	}

	public void start(RunnableList actionList) {
		if (!isRunning) {
			isRunning = true;
			proxy.start();
			actionList.runAll();
		}

	}

	public void stop(RunnableList actionList) {
		if (isRunning) {
			isRunning = false;
			proxy.stop();
			userLogin.stop();
			dispatcher.stop();
			actionList.runAll();
		}

	}

	public void sendToAll(String string) {
		dispatcher.sendToAll(string);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

	public void changed(String msg) {
		setChanged();
		notifyObservers(msg);
	}

	Consumer<Socket> onNewConnection = (socket) -> {
		listViewConsumers.addTo(0, socket.getInetAddress().toString());
		userLogin.add(socket);
	};

	Consumer<GameClientHandler> onLogin = (cli) -> {
		listViewConsumers.removeFrom(0, cli.fromAddr());
		listViewConsumers.addTo(1, cli.getClientName());
		dispatcher.add(cli);
	};

	public interface Function<T> {
		public void apply(T t1, T t2);
	}

	Function<GameClientHandler> onGameStart = (c1, c2) -> {
		listViewConsumers.removeFrom(1, c1.getClientName(), c2.getClientName());
		listViewConsumers.addTo(2, c1.getClientName(), c2.getClientName());
		brokerManager.newBroker(c1, c2);
	};

	Consumer<GameClientHandler> onGameOver = (cli) -> {
		listViewConsumers.removeFrom(2, cli.getClientName());
		if (cli.isConnected()) {
			listViewConsumers.addTo(1, cli.getClientName());
			dispatcher.add(cli);
		} else
		scoreDb.save(cli.getClientName(), cli.getScore());
	};
}
