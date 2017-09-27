package server.broker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import blackjack.BlackJackBroker;
import clienthandler.GameClientHandler;

public class BrokerManager {

	Consumer<GameClientHandler> onGameOver;

	public BrokerManager(Consumer<GameClientHandler> onGameOver) {
		this.onGameOver = onGameOver;
	}


	public void newBroker(GameClientHandler c1, GameClientHandler c2) {
		GameBroker broker = new BlackJackBroker(c1, c2,onGameOver);
		broker.startGame();
	}




}
