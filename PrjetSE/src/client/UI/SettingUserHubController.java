package client.UI;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingUserHubController extends Controller{
	private Scene scene;
	private Dispatcher dispatcher;
	private TextField pseudo;
	private TextField email;
	private Text notification;
	
	public SettingUserHubController(Text notification, TextField pseudo, TextField email, Scene scene, Dispatcher dispatcher) {
		this.pseudo = pseudo;
		this.email = email;
		this.scene=scene;
		this.dispatcher=dispatcher;
	}
	
	@Override
	public void update(String message) {
		notification.setText(message);
	}

	@Override
	public void display(Stage stage) {
		stage.setScene(scene);
	}
	
	public void updatePseudoField(String pseudo) {
		this.pseudo.setText(pseudo);
	}
	
	public void updateEmailField(String email) {
		this.email.setText(email);
	}
	
	public void goBack() {
		dispatcher.displayMainHub();
	}
	
	public void handleEdit(String email, String pseudo) {
		dispatcher.handleEdit(email,pseudo);
	}
	
}
