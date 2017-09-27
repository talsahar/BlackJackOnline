package com.talsahar.BlackJackWS;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.talsahar.db.BlackJackScoreManager;
import com.talsahar.db.UsersManager;


@Path("blackjack")
public class BlackJackScoreService {

	private BlackJackScoreManager manager;

	public BlackJackScoreService() {
		manager = new BlackJackScoreManager();
	}

	@GET
	@Path("/load")
	@Consumes(MediaType.TEXT_PLAIN)
	public String load(@DefaultValue("") @QueryParam("username") String username) {
		return manager.load(username)+"";
	}


	@POST
	@Path("/save")
	@Consumes(MediaType.TEXT_PLAIN)
	public String save(String data) {
		String[] args = data.split(":");
		return (manager.save(args[0], Double.parseDouble(args[1])) == true) ? "true" : "false";
	}

}
