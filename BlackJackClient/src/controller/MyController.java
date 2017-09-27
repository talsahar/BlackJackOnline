package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import command.Command;
import model.Model;
import view.View;

public class MyController implements Controller {

	private View view;
	private Model model;
	private CommandQueue queue;

	private CommandMap commandMap;

	public MyController() {
		commandMap = new CommandMap();
		queue = new CommandQueue();
		queue.start();
	}

	public void initCommandMap() {
		commandMap.initialMap(view, model);

	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			List<String> params = msgToLinkString((String) arg);
			String commandName = params.remove(0);
			System.out.println("command name: " + commandName);
			Command command = commandMap.getCommand(commandName);
			command.setParams(params);
			queue.insertCommand(command);

		} catch (Exception e) {
			System.out.println(e);
			System.out.println(arg);
		}

	}

	public List<String> msgToLinkString(String msg) {

		String[] arr = msg.split(" ");
		List<String> params = new LinkedList<String>();

		for (String s : arr) {
			params.add(s);
		}
		return params;

	}

	private static class Holder {
		public static final Controller controller = new MyController();
	}

	public static Controller getInstance() {
		return Holder.controller;
	}

	@Override
	public void setView(View view) {
		this.view = view;

	}

	@Override
	public void setModel(Model model) {
		this.model = model;

	}@Override
	public void stop() {
		queue.stop();
	}

}
