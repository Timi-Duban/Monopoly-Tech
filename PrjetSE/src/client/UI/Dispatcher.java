package client.UI;

import java.util.ArrayList;

import client.BL.ClientFacade;
import generalClasses.Item;
import javafx.stage.Stage;

public class Dispatcher {
	private Stage stage;
	private Controller currentController;
	private ClientFacade facade;
	
	public Dispatcher(Stage stage) {
		this.stage=stage;
		this.facade=new ClientFacade(this);
	}
	
	public void displayLogin() {
		LoginView view=new LoginView(this);
		setCurrentController(view.getController());
		this.currentController.display(this.stage);
		
	}
	
	public void displaySignin() {
		SigninView view=new SigninView(this);
		setCurrentController(view.getController());
		this.currentController.display(this.stage);
	}
	
	public void displayMainHub() {
		MainHubView view=new MainHubView(this);
		setCurrentController(view.getController());
		this.currentController.display(this.stage);
	}
	
	public void update(String message) {
		currentController.update(message);
	}
	
	public void setCurrentController(Controller controller) {
		this.currentController=controller;
	}
	
	public void handleLogin(String email,String password) {
		facade.handleLogin(email, password);
	}
	
	public void handleSignin(String email, String pseudo, String password) {
		facade.handleSignIn(email, pseudo, password);
	}
	
	public ArrayList<Item> getNotBoughtItems(){
		return facade.getNotBoughtItems();
	}

}