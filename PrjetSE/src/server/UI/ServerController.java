package server.UI;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.BL.ServerFacade;

public class ServerController extends Application {
    

	private Stage currentStage;
	private static ServerController controllerSingleton;
	private ServerFacade facade;
	/**
     * Default constructor. Must be public or else java fx application cannot be intantiated.
     * However the contructor must not be called. See getInstance instead.
     */
    public ServerController() {
    }

    /**
     * Entry point for the javaFX application. Shows the start server view.
     */
    @Override
	public void start(Stage primaryStage) throws Exception {
    	primaryStage.setTitle("Server");
    	ServerController controller=ServerController.getInstance();
    	controller.setCurrentStage(primaryStage);
    	controller.displayStart();
    	primaryStage.setMaximized(true);
		primaryStage.show();
    }
    
    /**
     * 
     * @param stage : the window of the javafx application
     */
    public void setCurrentStage(Stage stage) {
    	this.currentStage=stage;
    }
    
    /**
     * Display the start server view.
     */
    public void displayStart() {
    	
		//Setup the grid
		GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25, 25, 25, 25));
	    
	    //Setup title
        Text scenetitle = new Text("Monopoly'Tech Server");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        grid.add(scenetitle, 0, 1, 2, 1);
        
        //Setup the "start server" button
        Button buttonStart = new Button("Start server");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(buttonStart);
        grid.add(hbBtn, 1, 5);
        
        //Setup the text area to inform the user.
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 7);
        
        
        //handle the button action.
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	startServer();
                	
                } catch(IOException e) {
                	e.printStackTrace();
                	actiontarget.setText("Unable to start the server.");
                	
                }
            }
        });

        Scene sceneStartServer = new Scene(grid, 300, 275);
        this.currentStage.setScene(sceneStartServer);
    }

    /**
     * display the main hub view
     */
    public void displayHub() {
		//Setup the grid
        GridPane gridHub = new GridPane();
        gridHub.setAlignment(Pos.CENTER);
        gridHub.setHgap(10);
        gridHub.setVgap(10);
        gridHub.setPadding(new Insets(25, 25, 25, 25));
      	    
        //Setup title
        Text scenetitle = new Text("Monopoly'Tech Server");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        gridHub.add(scenetitle, 0, 1, 2, 1);
              
        //Setup the buttons
        Button buttonUser = new Button("Access an user setting");
        Button buttonMessage = new Button("Send a message to every users");
        Button buttonStop = new Button("Stop server");
        
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn2.getChildren().add(buttonUser);
        hbBtn2.getChildren().add(buttonMessage);
        hbBtn2.getChildren().add(buttonStop);
        gridHub.add(hbBtn2, 1, 5);

        //Setup the text area to inform the user.
        final Text actiontarget = new Text();
        gridHub.add(actiontarget, 1, 7);
        
        Scene sceneHub = new Scene(gridHub, 300, 275);
        
        //handle the button action.
        buttonUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	ServerController.getInstance().startServer();
                } catch(IOException e) {
                	actiontarget.setText("Unable to start the server.");
                	
                }
            }
        });
        
        //handle the button action.
        buttonMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	ServerController.getInstance().startServer();
                } catch(IOException e) {
                	actiontarget.setText("Unable to start the server.");
                	
                }
            }
        });
        
        //handle the button action.
        buttonStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	ServerController.getInstance().startServer();
                } catch(IOException e) {
                	actiontarget.setText("Unable to start the server.");
                	
                }
            }
        });
    	currentStage.setScene(sceneHub);
    }
    
    public static ServerController getInstance() {
    	if(controllerSingleton==null) {
    		controllerSingleton=new ServerController();
    	}
    	return controllerSingleton;
    }
    
    
    /**
     * Delegate the method to the facade
     */
    public void startServer() throws IOException {
    	if(facade==null) {
    		facade=new ServerFacade();
    	}
    	facade.startServer();
    	displayHub();
    }
}
