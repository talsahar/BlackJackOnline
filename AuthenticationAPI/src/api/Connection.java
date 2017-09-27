package api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Connection {

	private Invocation.Builder requestPage(String url) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		return webTarget.request();

	}

	protected boolean getBooleanResponse(Response response) {
		if (response.getStatus() == 200)
			return (response.readEntity(String.class).equals("true")) ? true : false;

		System.out.println("error reading response: " + response.getHeaderString("errorResponse"));
		return false;

	}

	protected int getIntegerResponse(Response response) {
		if (response.getStatus() == 200)
			return Integer.parseInt(response.readEntity(String.class));
		System.out.println("error reading response: " + response.getHeaderString("errorResponse"));
		return -1;

	}

	protected double getDoubleResponse(Response response) {
		if (response.getStatus() == 200)
			return Double.parseDouble(response.readEntity(String.class));
		System.out.println("error reading response: " + response.getHeaderString("errorResponse"));
		return -1;

	}

	
	protected Response get(String url) {
		Invocation.Builder invocationBuilder = requestPage(url);
		Response response = invocationBuilder.accept(MediaType.TEXT_PLAIN).get();
		return response;
	}

	protected Response post(String url, String data) {
		Invocation.Builder invocationBuilder = requestPage(url);
		Response response = invocationBuilder.post(Entity.entity(data, MediaType.TEXT_PLAIN));
		return response;
	}

}
