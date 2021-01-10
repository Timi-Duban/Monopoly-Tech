package client.UI;



import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WaitingRoomController extends Controller {
	private Text notification;
	private VBox box;
	private Button start;
	
	private Scene scene;
	
	private Dispatcher dispatcher;
	
	

	public WaitingRoomController(Text notification, VBox box, Button start, Scene scene,
			Dispatcher dispatcher) {
		super();
		this.notification = notification;
		this.box = box;
		this.start = start;
		this.scene = scene;
		this.dispatcher = dispatcher;
	}

	@Override
	public void update(String message) {
		notification.setText(message);
	}

	@Override
	 public void display(Stage stage) {
		stage.setScene(this.scene);
	 }
	
	public void addPlayer(String pseudo) {
		Text pseudoL=new Text(pseudo);
		box.getChildren().add(pseudoL);
	}
	
	public void removePlayer(String pseudo) {
		Text player;
		String pseudoIterate;
		for(Node i : box.getChildren()){
			player=(Text)i;
			pseudoIterate=player.getText();
			if(pseudo.equals(pseudoIterate)) {
				box.getChildren().remove(player);
				break;
			}
		}
	}
	
	public void updateNewHost() {
		System.out.println("bbb");
		start.setVisible(true);
	}
	
	public void startGame() {
		dispatcher.startGame();
	}
	
	public void quitGame() {
		dispatcher.quitGame();
	}

}
