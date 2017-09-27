package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import command.Command;
import command.poolThread.CommandPool;
import command.poolThread.CommandPoolThread;

public class CommandQueue {
	private BlockingQueue<Command> queue;
	private CommandPool commandPool;

	private boolean isRunning;

	public CommandQueue() {
		queue = new ArrayBlockingQueue<>(10);
		commandPool = CommandPoolThread.getInstance();
	}

	public void insertCommand(Command command) {
		try {
			queue.put(command);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		isRunning = true;
		new Thread(() -> {
			while (isRunning) {
				try {
					Command command = queue.poll(1, TimeUnit.SECONDS);
					if (command != null)
						//command.execute();
						commandPool.insert(() -> command.execute());
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		}).start();
	}

	public void stop() {
		isRunning = false;
	}

}
