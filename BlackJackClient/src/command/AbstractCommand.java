package command;

import java.util.List;
import java.util.Observable;

import command.poolThread.CommandPool;
import command.poolThread.CommandPoolThread;
import controller.Controller;
import controller.MyController;
import model.Model;
import view.View;

public abstract class AbstractCommand extends Observable implements Command {

	protected View myView;
	protected Model myModel;
	protected List<String> params;
	private String commandkey;
	
	public String getKey() {
		return commandkey;
	}

	
	public AbstractCommand(String key,View myView,Model myModel) {
		this.commandkey=key;
		this.myView=myView;
		this.myModel=myModel;
	}
	

	@Override
	public void setParams(List<String> list) {
		params = list;
	}

}
