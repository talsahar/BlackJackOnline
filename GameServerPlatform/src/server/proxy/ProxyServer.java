package server.proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

import src.ConfigServer;

public class ProxyServer {
	private ServerSocket socket;
	private Thread thread;
	private boolean isRunning;
	private Consumer<Socket> onNewConnection;

	public ProxyServer(Consumer<Socket> onNewConnection) {
		this.onNewConnection = onNewConnection;

		
	}

	private void initialThread() {
		thread = new Thread(() -> {
			try {
				isRunning = true;
				socket = new ServerSocket(ConfigServer.serverPORT);
				while (isRunning) {

					Socket connectedSocket = socket.accept();

					if (connectedSocket != null) {
						onNewConnection.accept(connectedSocket);
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}

		});
	}

	public void start() {
		initialThread();
		thread.start();

	}

	public void stop() {

		try {
			if (isRunning) {
				isRunning = false;
				socket.close();
				thread.join();

			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
