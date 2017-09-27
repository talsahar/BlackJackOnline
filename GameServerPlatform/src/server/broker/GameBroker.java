package server.broker;

import java.util.Observable;
import java.util.function.Consumer;

import clienthandler.GameClientHandler;

public abstract class GameBroker extends Observable implements Broker {

	protected GameClientHandler cli1;
	protected GameClientHandler cli2;
	protected Consumer<GameClientHandler> onGameOver;

	public GameBroker(GameClientHandler c1, GameClientHandler c2, Consumer<GameClientHandler> onGameOver) {
		this.cli1 = c1;
		this.cli2 = c2;
		this.onGameOver = onGameOver;
	}

	@Override
	public void gameOver() {
		onGameOver.accept(cli1);
		onGameOver.accept(cli2);
	
	}

}
