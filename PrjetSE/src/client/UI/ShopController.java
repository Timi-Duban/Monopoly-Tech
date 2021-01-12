package client.UI;

import java.util.ArrayList;
import generalClasses.Item;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShopController extends Controller {
	private Dispatcher dispatcher;
	private Text actionTargetView;
	private Scene scene;
	
	@Override
	public void update(String message) {
		actionTargetView.setText(message);		
	}
	@Override
	public void display(Stage stage) {
		stage.setScene(this.scene);
		System.out.println("ShopController : setScene");
	}
	
	public ShopController(Text actionTarget, Scene scene, Dispatcher dispatcher) {
		 this.dispatcher=dispatcher;
		 this.actionTargetView=actionTarget;
		 this.scene=scene;
	 }
	
	public ArrayList<Item> getNotBoughtItems(){
		return dispatcher.getNotBoughtItems();
	}
	/**
	 * @param scene the scene to set
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public void returnHub() {
		dispatcher.displayMainHub();
	}
	public void handleShopBuying(int itemId) {
		dispatcher.handleShopBuying(Integer.toString(itemId));
	}

	
}
