package model.client;

import src.Config;

public class ClientHandler implements CliHandler {
	private BJClient client;

	public ClientHandler(String ip, int port) {
		client = new BJClient(ip, port);
	}

	public BJClient getClient() {
		return client;

	}

	@Override
	public void sendRegister(String username, String password) {
		client.send(Config.Clogin + username + ":" + password);
	}

	@Override
	public void sendLogin(String username, String password) {
		client.send(Config.CRegister + username + ":" + password);

	}

	@Override
	public void invite(String cliname) {
		client.send(Config.Cinvite + cliname);
	}

	@Override
	public void accept(String invitor) {
		client.send(Config.Caccept + invitor);

	}

	@Override
	public void reject(String cliname) {
		client.send(Config.Creject + cliname);
	}

	@Override
	public void sendChatMessage(String string) {
		client.send(Config.CsendGlobalMessage + " " + string);
	}

	@Override
	public void sendBet(Double bet) {
		client.send(Config.GCbet + " " + bet);
	}

	@Override
	public void sendStand() {
		client.send(Config.GCstand);

	}

	@Override
	public void sendHit() {
		client.send(Config.GChit);

	}

	@Override
	public void getMeToLobby() {
client.send(Config.GCtoLobby);		
	}

	@Override
	public void sendRematchOffer() {
client.send(Config.Crematch);		
	}

	@Override
	public void reMatchAccept() {
		client.send(Config.CrematchAccept);		
		
	}

	@Override
	public void reMatchreject() {
		client.send(Config.CrematchReject);
		
	}

	@Override
	public void sendExit() {
		client.send(Config.Cexit);
		
	}

	@Override
	public void disconnect() {
		client.stop();
	}
}
