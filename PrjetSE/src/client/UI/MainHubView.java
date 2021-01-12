package client.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainHubView {
	private GridPane grid;
	private Text pseudo;
	private Text notification;
	private Button buttonDisconnect;
	private Button buttonPlay;
	private Button buttonShop;
	private Button buttonAchievement;
	
	private VBox hbBtn;
	
	Scene scene;
	
	MainHubController controller;
	public MainHubView(Dispatcher dispatcher) {
		grid = new GridPane();
		buttonDisconnect = new Button("Disconnect");
		buttonPlay=new Button("Play");
		buttonShop=new Button("Shop");
		buttonAchievement=new Button("Achievement");
		buttonSetting =new Button("Setting");
		hbBtn=new VBox();
		hbBtn.getChildren().addAll(buttonDisconnect,buttonPlay,buttonShop,buttonAchievement,buttonSetting);
		pseudo=new Text();
		notification=new Text();
		
		grid.add(pseudo, 0, 0, 2, 1);
		grid.add(notification,0, 1);
		grid.add(hbBtn, 0, 2);
		
		scene=new Scene(grid,300,275);
		 //handle the buttons action.
		buttonDisconnect.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                controller.disconnect();

	            }
	        });
		
		buttonSetting.setOnAction(new EventHandler<ActionEvent>() {
            		@Override
           		 public void handle(ActionEvent event) {
            		controller.displaySettingUser();

           		}
        	});
	      
		buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	controller.displayCreationGame();

	            }
	        });
		buttonAchievement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
                controller.displayAchievement();

            }
        });
      
		buttonShop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	controller.displayShop();

            }
        });
		controller=new MainHubController(pseudo,notification,scene,dispatcher);
		
		
	}
	
	public MainHubController getController() {
		return this.controller;
	}
}
