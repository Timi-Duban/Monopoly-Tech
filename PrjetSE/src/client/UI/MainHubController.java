package client.UI;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainHubController extends Controller {
	private Text pseudo;
	private Text notification;
	private Scene scene;
	private Dispatcher dispatcher;
	
	
	public MainHubController(Text pseudo,Text notification,Scene scene, Dispatcher dispatcher) {
		this.pseudo=pseudo;
		this.notification=notification;
		this.scene=scene;
		this.dispatcher=dispatcher;
	}

	@Override
	public void update(String message) {
		pseudo.setText(message);
		
	}

	@Override
	public void display(Stage stage) {
		stage.setScene(scene);
		
	}
	
	public void updatePseudo(String pseudo) {
		this.pseudo.setText(pseudo);
	}
	
	public void disconnect() {
		dispatcher.disconnect();
	}
	
	public void displayShop() {
		
	}
	
	public void displayAchievement(){
		
	}
	
	public void displayCreationGame() {
		dispatcher.displayCreationGame();
	}

}
