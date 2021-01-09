package client.UI;

import javafx.stage.Stage;

public abstract class Controller {
	public abstract void update(String message);
	
	public abstract void display(Stage stage);
}
