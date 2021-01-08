package client.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SigninView {
	private GridPane grid;
	private Text scenetitle = new Text("Monopoly'Tech");
	private Label emailLabelS = new Label("Email:");
	private Label pseudoLabelS = new Label("Pseudo:");
	private Label passwordLabelS = new Label("Password:");
	private TextField emailTextFieldS = new TextField();
	private TextField pseudoFieldS = new TextField();
	private PasswordField passwordFieldS = new PasswordField();
	private Button buttonSignIn = new Button("Sign in");
	private HBox hbBtnS = new HBox(10);
	
	private Text actionText=new Text();
	
	private Scene scene;
	
	private SigninController controller;
	
	public SigninView(Dispatcher dispatcher) {
		
		
		controller=new SigninController(dispatcher,actionText,scene);
	}
	
	public SigninController getController() {
		return this.controller;
	}
	
}
