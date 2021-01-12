package client.UI;

import java.util.ArrayList;

import generalClasses.Achievement;
import generalClasses.Item;
import generalClasses.exceptions.LoginException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.geometry.Insets; 
import javafx.scene.Scene;
import javafx.scene.control.Button; 
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.ColumnConstraints;

public class AchievementView {
		private GridPane grid;
		private Button returnHub;
		
		
		
		private Scene scene;
		private Text actionTarget;
		
		
		 
		AchievementController controller;
		
		public AchievementView(Dispatcher dispatcher) {
			this.actionTarget = new Text();
			grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			grid.add(actionTarget, 1, 0);
			
			this.controller = new AchievementController(actionTarget, scene, dispatcher);
			ArrayList<Achievement> listAchievements = controller.getAchievements();
			returnHub = new Button("return to hub");
			
			
			ColumnConstraints column1 = new ColumnConstraints();
		     column1.setPercentWidth(50);
		     ColumnConstraints column2 = new ColumnConstraints();
		     column2.setPercentWidth(30);
		     ColumnConstraints column3 = new ColumnConstraints();
		     column3.setPercentWidth(20);
		     grid.getColumnConstraints().addAll(column1, column2, column3);
			 
			
			Label description = new Label("Description");
			Label amount = new Label("Pièces");
			Label isAchieved = new Label("Réussi");
			
		    grid.add(description, 0, 0);
		    grid.add(amount, 1, 0);
		    grid.add(isAchieved, 2, 0);
			for (int i=0; i<listAchievements.size();i++) {
				
				description = new Label(listAchievements.get(i).getAchievementDescription());
				amount = new Label(Integer.toString(listAchievements.get(i).getAchievementAmount()));
				isAchieved = new Label(listAchievements.get(i).isAchieved() ? "Oui" : "Non");
				
			    grid.add(description, 0, i+1);
			    grid.add(amount, 1, i+1);
			    grid.add(isAchieved, 2, i+1);
			}
			grid.add(returnHub, 0, 0);		
			returnHub.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	controller.returnHub();
	            }
			});

			this.scene=new Scene(grid);
			controller.setScene(scene);
		}


		 public AchievementController getController() {
			 return this.controller;
		 }
		 
}

