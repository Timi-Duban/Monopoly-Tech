package client;

import client.UI.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainClient extends Application {
	 @Override
	    public void start(Stage primaryStage) {
		 	primaryStage.setTitle("Client");
	        Dispatcher dispatcher = new Dispatcher(primaryStage);
	        dispatcher.displayLogin();
	        primaryStage.show();
	        
	    }
	
	public static void main(String[] args) {
		launch(args);

	}

}
