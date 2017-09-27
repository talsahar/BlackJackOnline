package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			
			FXMLLoader fxl = new FXMLLoader();
			BorderPane root = (BorderPane) fxl.load(getClass().getResource("MainWindow.fxml").openStream());
			Scene scene = new Scene(root, 470, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

			primaryStage.setTitle("BlackJack Server");

		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	
		launch(args);
		
		
	}
}
