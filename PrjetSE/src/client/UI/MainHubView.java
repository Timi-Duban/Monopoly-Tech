package client.UI;

import javafx.scene.layout.GridPane;

public class MainHubView {
	GridPane grid = new GridPane();
	
	MainHubController controller;
	public MainHubView(Dispatcher dispatcher) {
		
	}
	
	public MainHubController getController() {
		return this.controller;
	}
}
