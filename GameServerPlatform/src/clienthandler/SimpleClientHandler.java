package clienthandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SimpleClientHandler implements ClientHandler {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	public SimpleClientHandler(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void send(String message) {
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String receive() {
		try {
			return in.readUTF();
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String fromAddr() {
		return socket.getInetAddress().toString();
	}

}
