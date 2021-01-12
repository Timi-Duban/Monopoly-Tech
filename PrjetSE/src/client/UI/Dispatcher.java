package client.UI;

import java.util.ArrayList;

import client.BL.ClientFacade;
import generalClasses.Achievement;
import generalClasses.Item;
import javafx.stage.Stage;

public class Dispatcher {
	private Stage stage;
	private Controller currentController;
	private ClientFacade facade;
	
	/**
	 * @return the facade
	 */
	public ClientFacade getFacade() {
		return facade;
	}

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
		String pseudo=facade.getCurrentUser().getPseudo();
		((MainHubController)this.currentController).updatePseudo(pseudo);
	}
	
	public void displaySettingUser() {
		SettingUserView view = new SettingUserView(this);
		setCurrentController(view.getController());
		this.currentController.display(this.stage);
		String pseudo =facade.getCurrentUser().getPseudo();
		String email=facade.getCurrentUser().getEmail();
		((SettingUserHubController)this.currentController).updatePseudoField(pseudo);
		((SettingUserHubController)this.currentController).updateEmailField(email);
	}
	
	public void displayShop() {
		ShopView view = new ShopView(this);
		setCurrentController(view.getController());
		this.currentController.display(this.stage);
	}
	
	public void displayCreationGame() {
		CreationGameView view=new CreationGameView(this);
		setCurrentController(view.getController());
		this.currentController.display(this.stage);
	}
	
	public void displayWaitingRoom(String code) {
		WaitingRoomView view=new WaitingRoomView(this,code);
		setCurrentController(view.getController());
		this.currentController.display(this.stage);

	}
	
	public void displayGame(){
		
	}
	
	public void displayAchievement() {
		AchievementView view=new AchievementView(this);
		setCurrentController(view.getController());
		this.currentController.display(this.stage);
	}
	
	public void update(String message) {
		currentController.update(message);
	}
	
	public void updateNewHost() {
		((WaitingRoomController)this.currentController).updateNewHost();
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
	
	public void handleShopBuying(String itemId) {
		facade.handleShopBuying(itemId);
	}
	public void handleEdit(String email, String pseudo) {
		facade.handleEdit( email, pseudo);
	}
	public ArrayList<Achievement> getAchievements(){
		return facade.getAchievements();
	}
	
	public void joinPublic() {
		facade.joinPublicGame();
	}
	
	public void joinPrivate(String code) {
		facade.joinPrivateGame(code);
	}
	
	public void createGame() {
		facade.createGame();
	}
	
	public void quitGame() {
		facade.quitGame();
	}
	
	public void startGame() {
		facade.startGame();
	}
	
	public void disconnect() {
		facade.disconnect();
	}
	
	public void addPlayer(String pseudo) {
		((WaitingRoomController)this.currentController).addPlayer(pseudo);
	}
	
	public void removePlayer(String pseudo) {
		((WaitingRoomController)this.currentController).removePlayer(pseudo);
	}
}
