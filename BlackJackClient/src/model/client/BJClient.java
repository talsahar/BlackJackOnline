package model.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

import src.Config;

public class BJClient extends Observable implements MyClient {

	private Socket cliSocket;
	private DataInputStream socketReader;
	private DataOutputStream socketWriter;
	private boolean isRunning;
	private Thread thread;

	public BJClient(String ip, int port) {
		try {
			cliSocket = new Socket(ip, port);
			socketReader = new DataInputStream(cliSocket.getInputStream());
			socketWriter = new DataOutputStream(cliSocket.getOutputStream());

			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		isRunning = true;
		thread = new Thread(() -> {
			while (isRunning) {
				try {
					String message = socketReader.readUTF();
					if (message != null) {
						setChanged();
						notifyObservers(message);
					}
				} catch (IOException e) {
					System.out.println(e);
					setChanged();
					notifyObservers(Config.RexitAccept);
				}
			}
		});
		thread.start();
	}

	@Override
	public void stop() {
		try {
			isRunning = false;
			cliSocket.close();
			thread.join();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void send(String message) {
		try {
			socketWriter.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
