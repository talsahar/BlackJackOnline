package server.login;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;

import api.AuthenticationAPI;
import api.MyAuthenticationAPI;
import api.MyBlackJackScoreAPI;
import clienthandler.GameClientHandler;
import src.Config;

public class UserLogin implements Observer {

	AuthenticationAPI auth;
	MyBlackJackScoreAPI scoreApi;
	ArrayList<GameClientHandler> clientArr;
	Consumer<GameClientHandler> onlogin;
	Consumer<GameClientHandler> onCliDisconnect;

	public UserLogin(Consumer<GameClientHandler> onLogin,Consumer<GameClientHandler> onClientClose) {
		auth = new MyAuthenticationAPI();
		scoreApi = new MyBlackJackScoreAPI();
		this.onlogin = onLogin;
		clientArr = new ArrayList<>();
	this.onCliDisconnect=onClientClose;
	}

	

	public void stop() {
		clientArr.forEach(cli -> cClose(cli));

	}

	public void add(Socket socket) {

		GameClientHandler clientHandler = new GameClientHandler(socket);
		clientHandler.addObserver(this);
		clientHandler.start();
		clientArr.add(clientHandler);
	}

	@Override
	public void update(Observable o, Object arg) {
		GameClientHandler cli = (GameClientHandler) o;
		String msg = (String) arg;

		if (msg.startsWith(Config.Clogin))
			cLogin(cli, msg.split(":"));

		else if (msg.startsWith(Config.CRegister))
			cRegister(cli, msg.split(":"));

		else if (msg.startsWith(Config.Cexit))
			cClose(cli);

	}

	public void cLogin(GameClientHandler cli, String[] params) {

		if (auth.userLogin(params[1], params[2])) {
			cli.setClientName(params[1]);
			cli.setScore(scoreApi.load(cli.getClientName()));
			
			cli.send(Config.RloginSucceed + " " + cli.getClientName()+" "+cli.getScore());
			
			clientArr.remove(cli);
			cli.deleteObserver(this);
			onlogin.accept(cli);
		} else
			cli.send(Config.RSimpleMessage+" "+"Error login: check your info");

	}

	public void cRegister(GameClientHandler cli, String[] params) {

		if (auth.userRegister(params[1], params[2])) {
			scoreApi.save(params[1], 5000);
			cli.send(Config.RregisterSucceed);
		}

		else
			cli.send(Config.RSimpleMessage+" "+"Error register: token username");

	}

	public void cClose(GameClientHandler cli) {
		clientArr.remove(cli);
		cli.close();
		onCliDisconnect.accept(cli);

	}

}
