package client.UI;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class WaitingRoomView {
	private GridPane grid;
	
	private Text notification;
	private Label codeL;
	private Text code;
	private VBox box;
	private Button start;
	private Button quit;
	
	private Scene scene;
	
	private WaitingRoomController controller;
	
	public WaitingRoomView(Dispatcher dispatcher,String code) {
		grid=new GridPane();
		notification=new Text();
		codeL=new Label("Code");
		this.code=new Text(code);
		box=new VBox();
		start=new Button("Start");
		start.setVisible(false);
		quit=new Button("Quit");
		
		grid.add(notification, 0, 0);
		grid.add(codeL, 0, 1);
		grid.add(this.code, 1, 1);
		grid.add(box, 2, 0);
		grid.add(start,0,2);
		grid.add(quit, 0, 3);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                controller.startGame();

	            }
	        });
	      
		quit.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	controller.quitGame();

	            }
	        });
		
		scene=new Scene(grid,275,300);
		controller=new WaitingRoomController(notification,box,start,scene,dispatcher);
	}



	public WaitingRoomController getController() {
		return controller;
	}
	

}
