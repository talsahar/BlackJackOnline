package model.client;

public interface CliHandler {

	void sendRegister(String username, String password);

	void sendLogin(String username, String password);

	void sendChatMessage(String string);

	void invite(String cliname);

	void accept(String invitor);

	void reject(String cliname);

	void sendBet(Double double1);

	void sendStand();

	void sendHit();

	void getMeToLobby();

	void sendRematchOffer();


	void reMatchAccept();

	void reMatchreject();

	void sendExit();

	void disconnect();

}
