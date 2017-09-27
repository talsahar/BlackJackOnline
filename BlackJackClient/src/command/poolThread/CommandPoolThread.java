package command.poolThread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CommandPoolThread implements CommandPool {
	private Executor ex;

	private CommandPoolThread() {
		ex = Executors.newWorkStealingPool();
	}

	@Override
	public void insert(Runnable r) {
		ex.execute(r);
	}

	// singleton
	private static class holder {
		public static final CommandPoolThread poolThread = new CommandPoolThread();
	}

	public static CommandPoolThread getInstance() {
		return holder.poolThread;
	}
}
