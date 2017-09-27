package view.other;

import java.util.function.Consumer;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.User;

public class OnlineUsersTableBuilder {
	TableView<User> table;

	public OnlineUsersTableBuilder(TableView<User> table) {
		this.table = table;
	}

	public OnlineUsersTableBuilder buildColumns() {
		TableColumn<User, String> nameColumn = new TableColumn<>("User");
		nameColumn.setMinWidth(200);
		nameColumn.setResizable(false);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<User, Long> scoreColumn = new TableColumn<>("Amount");
		scoreColumn.setMinWidth(100);
		scoreColumn.setResizable(false);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		table.getColumns().addAll(nameColumn, scoreColumn);
		nameColumn.setVisible(true);
		scoreColumn.setVisible(true);
		return this;
	}

	public OnlineUsersTableBuilder defineDoubleClickedMethod(Consumer<User> func) {
		table.setRowFactory(tv -> {
			TableRow<User> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					func.accept(row.getItem());
				}
			});
			return row;
		});
		return this;
	}

	public TableView<User> build() {
		return table;

	}

}
