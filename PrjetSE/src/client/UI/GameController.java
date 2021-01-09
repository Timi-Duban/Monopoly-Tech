package client.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController extends Controller {
	private Dispatcher dispatcher;
	private Text actionText;
	private Label[] owners;
	private Button rollDice;
	
	private Stage tradeOffer;
	private Label tradeMessage;
	
	Scene scene;

	
	
	public GameController(Text actionText, Label[] owners, Button rollDice, Stage tradeOffer, Label tradeMessage,
			Scene scene,Dispatcher dispatcher) {
		super();
		this.actionText = actionText;
		this.owners = owners;
		this.rollDice = rollDice;
		this.tradeOffer = tradeOffer;
		this.tradeMessage = tradeMessage;
		this.scene = scene;
		this.dispatcher=dispatcher;
	}

	@Override
	public void update(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(Stage stage) {
		// TODO Auto-generated method stub
		
	}

}
