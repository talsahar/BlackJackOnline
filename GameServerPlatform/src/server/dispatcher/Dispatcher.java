package server.dispatcher;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;

import api.MyBlackJackScoreAPI;
import clienthandler.GameClientHandler;
import server.MainServer.Function;

import src.Config;

public class Dispatcher implements Observer {

	LinkedList<GameClientHandler> clientArr;
	int maxConnections;
	Function<GameClientHandler> onGameStart;
	Consumer<GameClientHandler> onDisconnect;
	public Dispatcher(Function<GameClientHandler> onGameStart,Consumer<GameClientHandler> onDisconnect) {
		this.onGameStart = onGameStart;
		this.onDisconnect=onDisconnect;
		clientArr = new LinkedList<>();
	}

	public void add(GameClientHandler cli) {
		cli.addObserver(this);
		clientArr.add(cli);
		updateAllClientsList();
	}

	private void updateAllClientsList() {
		StringBuilder builder = new StringBuilder(Config.Ruserlist);

		clientArr.forEach(c -> builder.append(" ").append(c.getClientName()).append(" ").append(c.getScore()));
		clientArr.forEach(c -> c.send(builder.toString()));

	}

	public void stop() {
		clientArr.forEach(c -> c.close());
		clientArr.clear();
	}

	@Override
	public void update(Observable o, Object arg) {
		GameClientHandler cli = (GameClientHandler) o;
		String[] msg = ((String) arg).split(":");

		if (((String) arg).startsWith(Config.Cinvite))
			clientInvites(cli, getClientByName(msg[1]));

		else if (((String) arg).startsWith(Config.Caccept))
			clientAccepts(cli, getClientByName(msg[1]));

		else if (((String) arg).startsWith(Config.Creject))
			clientRejects(cli, getClientByName(msg[1]));

		else if (((String) arg).startsWith(Config.Cexit))
			clientCloses(cli);

		else if (((String) arg).startsWith(Config.CsendGlobalMessage))
			sendToAll((String) arg);

	}

	private void clientRejects(GameClientHandler cli, GameClientHandler invitor) {
		if (invitor != null)
			invitor.send(Config.Rreject + " " + cli.getClientName());
	}

	private void clientCloses(GameClientHandler cli) {
		clientArr.remove(cli);
		onDisconnect.accept(cli);
		cli.close();
	}

	private void clientAccepts(GameClientHandler acceptor, GameClientHandler invitor) {
		if (invitor != null) {
			invitor.deleteObserver(this);
			acceptor.deleteObserver(this);
			clientArr.remove(invitor);
			clientArr.remove(acceptor);
			updateAllClientsList();
			onGameStart.apply(invitor, acceptor);
		} else
			acceptor.send(Config.RclientNotFound);
	}

	private void clientInvites(GameClientHandler invitator, GameClientHandler other) {
		if (other != null)
			other.send(Config.Rinvite + " " + invitator.getClientName());
		else
			invitator.send(Config.RclientNotFound);

	}

	private GameClientHandler getClientByName(String name) {

		for (GameClientHandler c : clientArr)
			if (c.getClientName().equals(name))
				return c;

		return null;

	}

	public void sendToAll(String string) {
		clientArr.forEach(c -> c.send(string));
	}

}
