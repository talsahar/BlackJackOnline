package api;

import javax.ws.rs.core.Response;

public class MyBlackJackScoreAPI extends Connection implements BlackJackScoreAPI {

	public double load(String user) {
		Response response = get("http://localhost:8080/GamesWS/webapi/blackjack/load?username=" + user);
		return getDoubleResponse(response);
	}

	public boolean save(String user, double d) {
		Response response = post("http://localhost:8080/GamesWS/webapi/blackjack/save", user + ":" + d);
		return getBooleanResponse(response);
	}
	
	

}
