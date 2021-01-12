package client.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SettingUserView {

	private VBox root;
	private Button buttonBack;
	private TextField txtFieldPseudo;
	private TextField txtFieldEmail;
	private Button buttonEdit;
	private Button buttonChangePassword;
	private Text notification;
	
	Scene scene;
	
	SettingUserHubController controller;
	
	public SettingUserView(Dispatcher dispatcher) {
		root = new VBox(10);
		root.setPadding(new Insets(25));
		root.setAlignment(Pos.CENTER);
		
		HBox quitBox = new HBox(10);
		buttonBack =new Button("Back");
		quitBox.getChildren().add(buttonBack);
		
		Label settingLabel = new Label("Setting");
		
		HBox pseudoBox = new HBox(10);
		txtFieldPseudo = new TextField();
		Label lblPseudo = new Label("Pseudo");
		pseudoBox.getChildren().addAll(lblPseudo, txtFieldPseudo);
		
		HBox emailBox = new HBox(10);
		txtFieldEmail = new TextField();
		Label lblEmail = new Label("Email");
		pseudoBox.getChildren().addAll(lblEmail, txtFieldEmail);
		
		VBox boxPseudoEmail = new VBox(10);
		boxPseudoEmail.getChildren().addAll(pseudoBox, emailBox);
		
		buttonEdit = new Button("Edit");
		
		notification = new Text("...");
		
		buttonChangePassword = new Button("Change Password");
		
		buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.goBack();				
			}
		});
		buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String email = txtFieldEmail.getText();
            	String pseudo = txtFieldPseudo.getText();
            	//notification.setFill(Color.GREY);
            	notification.setText("edit...");
                controller.handleEdit(email,pseudo);
            }
        });
		
		root.getChildren().addAll(quitBox, settingLabel, boxPseudoEmail, buttonEdit, notification, buttonChangePassword);
		scene = new Scene(root);
		controller= new SettingUserHubController(notification, txtFieldPseudo, txtFieldEmail, scene,dispatcher);

	}
	
	public SettingUserHubController getController() {
		return this.controller;
	}
}
