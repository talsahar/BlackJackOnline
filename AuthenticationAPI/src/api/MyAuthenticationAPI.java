package api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class MyAuthenticationAPI extends Connection implements AuthenticationAPI {

	public boolean userLogin(String user, String pass) {
		Response response = get(
				"http://localhost:8080/GamesWS/webapi/users/login?username=" + user + "&password=" + pass);
		return getBooleanResponse(response);
	}

	public boolean AdminLogin(String user, String pass) {

		Response response = get("http://localhost:8080/GamesWS/webapi/users/admin?username=" + user + "&password=" + pass);
		return getBooleanResponse(response);
	}

	public boolean userRegister(String user, String pass) {

		Response response = post("http://localhost:8080/GamesWS/webapi/users/register", user + ":" + pass);
		return getBooleanResponse(response);
	}


}
