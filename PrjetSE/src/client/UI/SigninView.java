package client.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SigninView {
	private GridPane grid=new GridPane();
	private Text sceneTitle = new Text("Monopoly'Tech");
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
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		hbBtnS.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtnS.getChildren().add(buttonSignIn);
		
		grid.add(sceneTitle,1,0);
		grid.add(emailLabelS, 0, 1);
		grid.add(pseudoLabelS, 0, 2);
		grid.add(passwordLabelS, 0, 3);
		grid.add(emailTextFieldS, 1, 1);
		grid.add(pseudoFieldS, 1, 2);
		grid.add(passwordFieldS, 1, 3);
		grid.add(hbBtnS, 2, 4);
		grid.add(actionText, 0, 5);
		
		buttonSignIn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	String email = emailTextFieldS.getText();
	            	String pseudo = pseudoFieldS.getText();
	            	String password = passwordFieldS.getText();
	            	actionText.setFill(Color.GREY);
	            	actionText.setText("Connecting...");
	                controller.handleSignin(email,pseudo,password);

	            }
	        });
		scene=new Scene(grid);
		
		controller=new SigninController(dispatcher,actionText,scene);
	}
	
	public SigninController getController() {
		return this.controller;
	}
	
}
