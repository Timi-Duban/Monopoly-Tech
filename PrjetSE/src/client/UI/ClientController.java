package client.UI;

import client.BL.*;
import generalClasses.User;
import generalClasses.exceptions.LoginException;
import javafx.application.Application;
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
import javafx.stage.Stage;

//To do : gérer les exceptions de handleLogin(..) 

public class ClientController extends Application {
	private static ClientController controllerSingleton;
	private Stage currentStage;
	private ClientFacade facade;
    /**
     * Default constructor. Must be public or else java fx application cannot be intantiated.
     * However the contructor must not be called. See getInstance instead.
     */
    public ClientController() {}

    /**
     * Entry point for the javaFX application. Shows the login view.
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
    	primaryStage.setTitle("Client");
    	ClientController controller=ClientController.getInstance();
    	controller.setCurrentStage(primaryStage);
    	controller.displayLogin();
    	primaryStage.setMaximized(true);
		primaryStage.show();
	}


	/**
     * display the login interface
     */
    public void displayLogin() {
        //Setup the grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //Setup Logo
//        FileInputStream input = new FileInputStream("C:\\Users\\timi3\\Desktop\\Bordel perso\\Images poulpe\\Poulpe.jpg");
//        Image logo = new Image(input);
//        ImageView logoView = new ImageView(logo);
//        logoView.setFitHeight(222); 
//        logoView.setFitWidth(228); 
//        HBox logoComponent = new HBox(logoView);
//        grid.add(logoComponent, 0, 0);
        
        //Setup title
        Text scenetitle = new Text("Monopoly'Tech");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        grid.add(scenetitle, 0, 0, 2, 1);

        //////////Sign in
        //Setup the email text and form 
        Label emailLabelS = new Label("Email:");
        grid.add(emailLabelS, 0, 2);
        TextField emailTextFieldS = new TextField();
        grid.add(emailTextFieldS, 1, 2);
        
        //Setup the pseudo text and form 
        Label pseudoLabelS = new Label("Pseudo:");
        grid.add(pseudoLabelS, 0, 3);
        TextField pseudoFieldS = new TextField();
        grid.add(pseudoFieldS, 1, 3);
        
        //Setup the password text and form 
        Label passwordLabelS = new Label("Password:");
        grid.add(passwordLabelS, 0, 4);
        PasswordField passwordFieldS = new PasswordField();
        grid.add(passwordFieldS, 1, 4);
        
        //Setup the button
        Button buttonSignIn = new Button("Sign in");
        HBox hbBtnS = new HBox(10);
        hbBtnS.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnS.getChildren().add(buttonSignIn);
        grid.add(hbBtnS, 1, 5);
        
        //////////Log in
        //Setup the email text and form 
        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 2, 2);
        TextField emailTextField = new TextField();
        grid.add(emailTextField, 3, 2);

        //Setup the password text and form 
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 2, 3);
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 3, 3);
        
        //Setup the button
        Button buttonLogIn = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(buttonLogIn);
        grid.add(hbBtn, 3, 5);
        
        //Setup the text area to inform the user.
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 7);

        //handle the buttons action.
        buttonLogIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String email = emailTextField.getText();
            	String password = passwordField.getText();
                actiontarget.setFill(Color.GREY);
                actiontarget.setText("Connecting...");
                emailTextField.setText("");
                passwordField.setText("");
                try {
                	ClientController.getInstance().handleLogin(email,password);
                } catch(LoginException e) {
                	actiontarget.setText(e.customUserMessage);
                }
            }
        });
        
        buttonSignIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String email = emailTextFieldS.getText();
            	String pseudo= pseudoFieldS.getText();
            	String password = passwordFieldS.getText();
                actiontarget.setFill(Color.GREY);
                actiontarget.setText("Connecting...");

                try {
                	ClientController.getInstance().handleSignIn(email,pseudo,password);
                } catch(LoginException e) {
                	actiontarget.setText(e.customUserMessage);
                    emailTextField.setText("");
                    passwordField.setText("");
                }
            }
        });

        //Display the whole page (currently on grid)
        Scene scene = new Scene(grid, 300, 275);
		
        
        this.currentStage.setScene(scene);;
        
    }


    /**
     * Displays the main hub view.
     */
    public void displayHub() {
    	User user=facade.getCurrentUser();
    	GridPane grid = new GridPane();
    	Text scenetitle = new Text(user.getPseudo());
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
    	grid.add(scenetitle, 0, 0, 2, 1);
    	Scene scene = new Scene(grid, 300, 275);
    	
    	this.currentStage.setScene(scene);
    }

    
    /**
     * 
     * @param email : the email
     * @param password : the password
     * create the facade if it has not been done before, then delegate to the client facade
     * If every step of handleLogin has been successful, then show the main hub
     */
    public void handleLogin(String email, String password) throws LoginException {
    	if(facade==null) {
    		facade=new ClientFacade();
    	}
    	facade.handleLogin(email,password);
    	displayHub();
    }
    
    /**
     * 
     * @param email
     * @param pseudo
     * @param password
     * 
     * create the facade if it has not been done before, then delegate to the client facade
     */
    public void handleSignIn(String email, String pseudo, String password) throws LoginException {
    	if(facade==null) {
    		facade=new ClientFacade();
    	}
    	facade.handleSignIn(email,pseudo,password);
    	displayHub();
    }
    
    
    /**
     * @return the controller for the client
     */
    public static ClientController getInstance() {
    	if(controllerSingleton==null) {
    		controllerSingleton=new ClientController();
    	}
    	return controllerSingleton;
    }




	public void setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
	}

}