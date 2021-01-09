package client.UI;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SigninController extends Controller {
	private Dispatcher dispatcher;
	
	private Text actionText;
	private Scene scene;
	
	public SigninController(Dispatcher dispatcher, Text actionText, Scene scene){
		this.dispatcher=dispatcher;
		this.actionText=actionText;
		this.scene=scene;
	}
	
	@Override
	public void update(String message) {
		actionText.setText(message);
		
	}

	@Override
	public void display(Stage stage) {
		stage.setScene(scene);
		
	}
	
	public void handleSignin(String email, String pseudo, String password) {
		dispatcher.handleSignin(email,pseudo,password);
	}
	
	
}
