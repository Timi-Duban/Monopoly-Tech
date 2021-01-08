package client.UI;

import generalClasses.exceptions.LoginException;
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

public class LoginView {
	 private GridPane grid;
	 Text scenetitle = new Text("Monopoly'Tech");
	 private TextField mailField= new TextField();
	 private PasswordField passwordField=new PasswordField();
	 private Label mailLabel= new Label("Email:");
	 private Label passwordLabel= new Label("Password:");
	 private Button buttonLogIn = new Button("Log in");
	 private Button buttonSignin =new Button("No account yet ?");
     private HBox hbBtn = new HBox(10);
     
     private Scene scene;
     private Text actionTarget;
     
     LoginController controller;
	 
	 public LoginView(Dispatcher dispatcher) {
		 grid = new GridPane();
		 grid.setAlignment(Pos.CENTER);
		 grid.setHgap(10);
		 grid.setVgap(10);
		 grid.setPadding(new Insets(25, 25, 25, 25));
		 scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		 hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		 hbBtn.getChildren().add(buttonLogIn);
		 hbBtn.getChildren().add(buttonSignin);
		 
		 actionTarget = new Text();
		 grid.add(scenetitle, 0, 0, 2, 1);
		 grid.add(mailLabel, 0, 2);
		 grid.add(passwordLabel, 1, 2);
		 grid.add(mailField, 3, 2);
		 grid.add(passwordField, 3, 3);
	     grid.add(actionTarget, 1, 7);
	     grid.add(hbBtn, 3, 5);
	     
	     //handle the buttons action.
	      buttonLogIn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	String email = mailField.getText();
	            	String password = passwordField.getText();
	            	actionTarget.setFill(Color.GREY);
	            	actionTarget.setText("Connecting...");
	                controller.handleLogin(email,password);

	            }
	        });
	      
	      buttonSignin.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	controller.displaySignin();

	            }
	        });
	     
	     scene = new Scene(grid, 300, 275);
		 this.controller=new LoginController(this.actionTarget,scene , dispatcher);
		 
	 }	
	 
	 public LoginController getController() {
		 return this.controller;
	 }
}
