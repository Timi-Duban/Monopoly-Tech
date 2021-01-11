package client.UI;

import java.util.ArrayList;

import generalClasses.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ShopView {
	// private final String ROOT_URL = "E:\\Work\\Projet_OOSE\\Monopoly-Tech\\PrjetSE\\";
	private GridPane grid;
	private Button[] listButton ;
	//private Button buttonItem1 = new Button("Buy");
	private Label[] listLabel ;
	//private Label Item1Label= new Label("Item 1:");
	private String[] listUrl ;
	// private Image image = new Image(new FileInputStream(ROOT_URL + "ressource\\");
	   
	private Scene scene;
	private Text actionTarget;
	ShopController controller;
	
	ArrayList<Item> listNotBoughtItems = controller.getNotBoughtItems();

	
	public ShopView(Dispatcher dispatcher) {
		actionTarget = new Text();
		this.scene=new Scene(grid);
		this.controller = new ShopController(this.actionTarget, scene, dispatcher);
	}


	 public ShopController getController() {
		 return this.controller;
	 }
}
