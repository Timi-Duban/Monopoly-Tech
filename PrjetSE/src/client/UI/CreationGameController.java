package client.UI;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreationGameController extends Controller {
	private Text actionText;
	private Scene scene;
	private Dispatcher dispatcher;
	
	public CreationGameController(Text action, Scene scene, Dispatcher dispatcher) {
		this.actionText=action;
		this.scene=scene;
		this.dispatcher=dispatcher;
	}
	
	@Override
	public void update(String message) {
		actionText.setText(message);
	}

	@Override
	public void display(Stage stage) {
		stage.setScene(scene);
		
	}
	
	public void joinPublic() {
		dispatcher.joinPublic();
	}
	
	public void joinPrivate(String code) {
		dispatcher.joinPrivate(code);
	}
	
	public void createGame() {
		dispatcher.createGame();
	}
	
	public void goBack() {
		dispatcher.displayMainHub();
	}
}
