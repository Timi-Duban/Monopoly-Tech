package client.UI;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class LoginInterface extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Auto-generated
			//BorderPane root = new BorderPane();
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
	        primaryStage.setTitle("Login");
	        
	        //Setup the grid
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        
	        //Setup Logo
	        FileInputStream input = new FileInputStream("C:\\Users\\timi3\\Desktop\\Bordel perso\\Images poulpe\\Poulpe.jpg");
	        Image logo = new Image(input);
	        ImageView logoView = new ImageView(logo);
	        logoView.setFitHeight(222); 
	        logoView.setFitWidth(228); 
	        HBox logoComponent = new HBox(logoView);
	        grid.add(logoComponent, 0, 0);
	        
	        //Setup title
	        Text scenetitle = new Text("Monopoly'Tech");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
	        grid.add(scenetitle, 0, 1, 2, 1);

	        //Setup the email text and form 
	        Label emailLabel = new Label("Email:");
	        grid.add(emailLabel, 0, 2);
	        TextField emailTextField = new TextField();
	        grid.add(emailTextField, 1, 2);

	        //Setup the password text and form 
	        Label passwordLabel = new Label("Password:");
	        grid.add(passwordLabel, 0, 3);
	        PasswordField passwordField = new PasswordField();
	        grid.add(passwordField, 1, 3);
	        
	        //Setup the button
	        Button buttonSignIn = new Button("Sign in");
	        HBox hbBtn = new HBox(10);
	        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	        hbBtn.getChildren().add(buttonSignIn);
	        grid.add(hbBtn, 1, 5);
	        
	        //Setup the text area to inform the user.
	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 7);

	        //handle the button action.
	        buttonSignIn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	email = emailTextField.getText();
	            	password = passwordField.getText();
	                actiontarget.setFill(Color.GREY);
	                actiontarget.setText("Please wait");
	                emailTextField.setText("");
	                passwordField.setText("");
	                // ------------------------------------------------------------------------------------- DEV : appeler la fonction ici
	            }
	        });

	        //Display the whole page (currently on grid)
	        Scene scene = new Scene(grid, 300, 275);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
