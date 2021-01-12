package client.UI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import generalClasses.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ShopView {
	private final class BuyingHandler implements EventHandler<ActionEvent> {
		private final int itemId;
		public BuyingHandler(int itemId) {
			super();
			this.itemId = itemId;
		}
		@Override
		public void handle(ActionEvent event) {
			controller.handleShopBuying(itemId);
		}
	}

	private final int NUMBEROFITEMS = 4;
	private final String ROOT_URL = "E:\\Work\\Projet_OOSE\\Monopoly-Tech\\PrjetSE\\";
	private GridPane grid;
	private Button returnHub;
	
	private String[] listUrl = new String[NUMBEROFITEMS] ;
	private ImageView[] listImageView = new ImageView[NUMBEROFITEMS];
	private Label[] listItemPrices = new Label[NUMBEROFITEMS];
	
	private Scene scene;
	private Text actionTarget;
	ShopController controller;
	


	public ShopView(Dispatcher dispatcher) {
		final int USERMONEY = dispatcher.getFacade().getUserMoney();
		this.actionTarget = new Text();
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(actionTarget, 1, 0);
		
		this.controller = new ShopController(actionTarget, scene, dispatcher);
		ArrayList<Item> listNotBoughtItems = controller.getNotBoughtItems();
		returnHub = new Button("return to hub");
		Button buttonItem1 = new Button("Buy");
		Button buttonItem2 = new Button("Buy");
		Button buttonItem3 = new Button("Buy");
		Button buttonItem4 = new Button("Buy");
		Button[] listButton = {buttonItem1, buttonItem2, buttonItem3, buttonItem4};
		Label labelItem1 = new Label("Item1");
		Label labelItem2 = new Label("Item2");
		Label labelItem3 = new Label("Item3");
		Label labelItem4 = new Label("Item4");
		Label[] listLabel = {labelItem1, labelItem2, labelItem3, labelItem4};
		

		
		Item currentItem ;
		Image currentImage ;
		for (int indice = 0 ; indice < listNotBoughtItems.size() ; indice++) {
			currentItem = listNotBoughtItems.get(indice);
			grid.add(listLabel[currentItem.getIdItem()-1], 0, indice+1);
			listUrl[indice] = currentItem.getUrlImage();
			try {
				currentImage = new Image(new FileInputStream(ROOT_URL + currentItem.getUrlImage()));
				listImageView[indice] = new ImageView(currentImage);
				listImageView[indice].setPreserveRatio(true);
				listImageView[indice].setFitHeight(50);
				grid.add(listImageView[indice], 1, indice+1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			grid.add(new Label(Integer.toString(currentItem.getPrice())), 2, indice+1);
			if (USERMONEY >= currentItem.getPrice())
				grid.add(listButton[currentItem.getIdItem()-1], 3, indice+1);
		}
		
		grid.add(new Label("Money : "+Integer.toString(USERMONEY)), 2, 0);
		grid.add(returnHub, 0, 0);		
		returnHub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	controller.returnHub();
            }
		});
		buttonItem1.setOnAction(new BuyingHandler(1));
		buttonItem2.setOnAction(new BuyingHandler(2));
		buttonItem3.setOnAction(new BuyingHandler(3));
		buttonItem4.setOnAction(new BuyingHandler(4));

		
		this.scene=new Scene(grid);
		controller.setScene(scene);
	}


	 public ShopController getController() {
		 return this.controller;
	 }
}
