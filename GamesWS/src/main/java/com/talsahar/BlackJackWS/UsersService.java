package com.talsahar.BlackJackWS;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.talsahar.db.UsersManager;
/*
 * format http://localhost:8080/BlackJackWS/webapi/users/
 login?username=user&password=pass
 admin?username=user&password=pass
 register params: string(user+":"+pass)
 * 
 */

@Path("users")
public class UsersService {

	private UsersManager manager;
	private final String adminName = "tal";

	public UsersService() {
		manager = new UsersManager();
	}

	@GET
	@Path("/login")
	@Consumes(MediaType.TEXT_PLAIN)
	public String login(@DefaultValue("") @QueryParam("username") String username,
			@DefaultValue("") @QueryParam("password") String password) {
		return (manager.login(username, password) == true) ? "true" : "false";
	}

	@GET
	@Path("/admin")
	@Consumes(MediaType.TEXT_PLAIN)
	public String admin(@DefaultValue("") @QueryParam("username") String username,
			@DefaultValue("") @QueryParam("password") String password) {
			return (username.equals(adminName)&&manager.login(username, password) == true) ? "true" : "false";
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.TEXT_PLAIN)
	public String register(String data) {
		String[] args = data.split(":");
		return (manager.register(args[0], args[1]) == true) ? "true" : "false";
	}

}
