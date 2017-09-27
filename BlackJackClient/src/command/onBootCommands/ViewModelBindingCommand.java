package command.onBootCommands;

import command.AbstractCommand;
import model.Model;
import model.MyModel;
import model.Properties;
import src.Config;
import view.View;

public class ViewModelBindingCommand extends AbstractCommand {

	public ViewModelBindingCommand(View view, Model model) {
		super(Config.bindCommand, view, model);
	}

	@Override
	public void execute() {
		myView.getOnlineTable().setItems(myModel.getOnlineUsers().getList());
		myView.getChatView().setItems(myModel.getChatMessages().getList());
		myView.getCurrentBetText().textProperty().bind(myModel.getProperties().getCurrentBet());
		myView.getMyCashText().textProperty().bind(myModel.getProperties().getMyCash());
		myView.getMyNameText().textProperty().bind(myModel.getProperties().getMyName());
		myView.getRivalCashText().textProperty().bind(myModel.getProperties().getRivalCash());
		myView.getRivalNameText().textProperty().bind(myModel.getProperties().getRivalName());
		myView.getBigGameMessage().textProperty().bind(myModel.getProperties().getBigMessage());
		myView.getLabel().textProperty().bind(myModel.getProperties().getLabel());

	}

}
