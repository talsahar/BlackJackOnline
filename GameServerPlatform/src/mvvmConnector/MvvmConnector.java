package mvvmConnector;

import model.MyModel;
import view.MainWindowView;
import viewmodel.MyViewModel;
import viewmodel.ViewModel;

public class MvvmConnector {

	
	
	public void connect(MainWindowView view,MyViewModel viewModel,MyModel model) {
		viewModel.setModel(model);
		model.addObserver(viewModel);
		bindViewToViewModel(viewModel, view);
	}
	
	public void bindViewToViewModel(ViewModel viewModel,MainWindowView view) {
		view.setViewModel(viewModel);
		viewModel.getIdField().bind(view.getUserField().textProperty());
		viewModel.getPassField().bind(view.getPassField().textProperty());
		view.getLabel().textProperty().bind(viewModel.getLabelField());
		viewModel.getListViewA().addObserver(view.getListViewA());
		viewModel.getListViewB().addObserver(view.getListViewB());
		viewModel.getListViewC().addObserver(view.getListViewC());
		viewModel.getTextField().bind(view.getCommandLine().textProperty());
	}
	

}
