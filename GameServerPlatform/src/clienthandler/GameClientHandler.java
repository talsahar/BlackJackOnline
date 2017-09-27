package clienthandler;

import java.net.Socket;
import java.util.Observable;

import src.Config;

public class GameClientHandler extends Observable {
	private ClientHandler client;
	private Thread thread;
	private boolean isConnected;

	private String clientName;
	private double score;
	private int highestScore;

	public GameClientHandler(Socket socket) {
		client = new SimpleClientHandler(socket);
		thread = new Thread(() -> {

			String inMessage = "";
			while (isConnected) {
				if ((inMessage = client.receive()) != null) {
					setChanged();
					notifyObservers(inMessage);
				}
			}

		});
	}

	public void start() {
		isConnected = true;
		thread.start();
	}

	public void send(String message) {
		client.send(message);
	}

	public void close() {
		new Thread(()-> {
		send(Config.RexitAccept);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
			try {
				if (isConnected) {
					isConnected = false;
					client.close();
					thread.join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}).start();
		
	}

	public String fromAddr() {
		return client.fromAddr();
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double d) {
		this.score = d;
	}

	public int getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}

	public boolean isConnected() {
		return isConnected;
	}

}
