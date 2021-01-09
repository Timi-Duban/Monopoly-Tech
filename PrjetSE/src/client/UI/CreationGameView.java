package client.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CreationGameView {
	private GridPane grid;
	private Text actionText;
	private Label codeL;
	private TextField code;
	private Button joinPublic;
	private Button joinPrivate;
	private Button createGame;
	private Button back;
	private VBox box;
	
	private Scene scene;
	
	private CreationGameController controller;

	public CreationGameView(Dispatcher dispatcher) {
		grid=new GridPane();
		actionText=new Text();
		codeL=new Label("Code");
		code=new TextField();
		joinPublic=new Button("Join a public game");
		joinPrivate=new Button("Join a private game");
		createGame=new Button("Create a private game");
		back=new Button("Back");
		box=new VBox();
		
		box.getChildren().addAll(createGame,joinPublic,joinPrivate,back);
		
		grid.add(actionText, 0, 0);
		grid.add(codeL, 0, 3);
		grid.add(code, 1, 3);
		grid.add(box, 0, 1);
		
		scene=new Scene(grid, 275,300);
		
		//handle the buttons action.
		joinPublic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.joinPublic();
				
			}
		});
		
		joinPrivate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String codeS = code.getText();
				controller.joinPrivate(codeS);
				
			}
		});
		
		createGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.createGame();				
			}
		});
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.goBack();				
			}
		});
		
		controller=new CreationGameController(actionText,scene,dispatcher);
		
	}

	public CreationGameController getController() {
		return controller;
	}
	
	
}
