package clienthandler;

public interface ClientHandler {

	void send(String message);
	String receive();
	void close();
	String fromAddr();
	
	
	
}
