package client.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController extends Controller {
	private Dispatcher dispatcher;
	
	private Text actionTargetView;
	private Scene scene;
	 
	 public LoginController(Text actionTarget, Scene scene, Dispatcher dispatcher) {
		 this.dispatcher=dispatcher;
		 this.actionTargetView=actionTarget;
		 this.scene=scene;
	 }
	 
	 public void update(String mes) {
		 actionTargetView.setText(mes);
	 }
	 
	 public void display(Stage stage) {
		 stage.setScene(this.scene);
	 }
	 
	 public void handleLogin(String email, String password) {
		 dispatcher.handleLogin(email, password);
	 }
	 
	 public void displaySignin() {
		 dispatcher.displaySignin();
	 }
	 
	 
}
