package server.broker;

import java.util.Observer;

public interface Broker extends Observer{
void startGame();
void gameOver();
}
