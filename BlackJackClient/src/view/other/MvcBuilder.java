package view.other;

import java.util.Observable;

import controller.Controller;
import controller.MyController;
import model.Model;
import model.MyModel;
import view.MainWindowController;
import view.View;

public class MvcBuilder {

	public void build(View view,Controller controller, Model model) {
		controller.setModel(model);
		controller.setView(view);
		((MyController) controller).initCommandMap();
		((MyModel) model).addObserver(controller);
		((MainWindowController) view).addObserver(controller);
	}
	
}
