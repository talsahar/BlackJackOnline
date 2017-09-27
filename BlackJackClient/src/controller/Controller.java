package controller;

import java.util.Observer;

import model.Model;
import view.View;

public interface Controller extends Observer {
	void setView(View view);

	void setModel(Model model);

	void stop();

}
