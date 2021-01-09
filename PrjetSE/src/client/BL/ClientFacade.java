package client.BL;

import generalClasses.PasswordUtils;
import generalClasses.User;
import generalClasses.exceptions.AlreadyUsedMailException;
import generalClasses.exceptions.ConnectionServerException;
import generalClasses.exceptions.InvalidIdException;
import generalClasses.exceptions.LoginException;
import generalClasses.exceptions.SendMessageException;
import generalClasses.exceptions.WrongIdException;
import generalClasses.CommunicationCommands;
import generalClasses.Item;

import com.lloseng.ocsf.client.*;

import client.UI.ClientController;
import client.UI.Dispatcher;

import java.io.IOException;
import java.util.*;

//To do : add a timeout in handleLogin
@SuppressWarnings("deprecation")
public class ClientFacade implements Observer {
	private final int PORT = 6000;
	private final String HOST = "localhost";
	
	private final String CONNECTION_ERROR="Connection server error";
	private final String SENDING_MESSAGE_ERROR="Sending message error";
	private final String USER_SENDING_ERROR="The server is busy, please try again later.";
	
	
	
	private final int WAITING_TIME=200;
	private final int SALT_LENGTH=30;
	private final int PASSWORD_MINIMAL_LENGTH=7;
	private final int PSEUDO_MINIMAL_LENGTH=2;
	private final int PSEUDO_MAXIMAL_LENGTH=20;
	
	
	private User currentUser=null;
	private ObservableClient clientCL;
	private Dispatcher dispatcher;
	private ArrayList<Item> itemList = null;
	
	private boolean successfulResponsefromServer=false;
	private boolean successfulAction=false;

	/**
     * Default constructor
     */
    public ClientFacade(Dispatcher dispatcher) {
	    clientCL= new ObservableClient(HOST,PORT);
	    this.dispatcher=dispatcher;
	    this.clientCL.addObserver(this);

    }



    /**
     * @param obs : the object that sends the message (clientCL in our case)  
     * @param arg : the message sent
     * receive a message from the communication layer and handle it
     */
    public void update(Observable obs , Object arg ) {
    	if(arg instanceof String) {
    		String mes=(String) arg;
	    	if(mes.equals(ObservableClient.CONNECTION_ESTABLISHED)) {
	    		//Handle successful connection to the server
	    		successfulResponsefromServer=true;
	    	}else if(mes.equals(ObservableClient.CONNECTION_CLOSED)) {
	    		//happens if the connection is properly closed	  
	    	}else {
	    		handleMessageFromServer(arg);	    		
	    	}
    	}else if(arg instanceof User) {
    		handleMessageFromServer(arg);
    	}else if(arg instanceof ArrayList<?>) {
    		handleMessageFromServer(arg);
    	}else if(arg instanceof Exception) {
    		//Handle a sudden disconnection from the server
		  
    	}
    }
    
    /**
     * 
     */
    @SuppressWarnings("unchecked") //the only ArrayList used here is of type Item.
	private void handleMessageFromServer(Object mes) {
    	if(mes instanceof String) {
    		String sMes=(String)mes;
    		System.out.println(sMes);
    		String[] mesSplit=(sMes).split(" ");
    		
    		switch(mesSplit[0]) {
    		case CommunicationCommands.GAME:
    			handleGameCommand(sMes);
    			break;
    		case CommunicationCommands.STARTING:
    			handleStartingCommand(sMes);
    			break;
    		}
    	}else if(mes instanceof User) {
    		setCurrentUser((User) mes);
    		successfulResponsefromServer=true;
    	}else if(mes instanceof ArrayList<?>) {
    		setItemList((ArrayList<Item>) mes);
    		successfulResponsefromServer=true;
    	}
    }
    
    private void handleStartingCommand(String mesReceived) {
    	String[] mes=mesReceived.split(" ");
    	String command=mes[1];
    	switch(command) {
    	case CommunicationCommands.LOGIN_CHECK:
    		//Id validation from server
    		if(mes[2].equals(CommunicationCommands.S_CORRECT)) {
    			successfulAction=true;
    		}
    		successfulResponsefromServer=true;
    		break;
    	case CommunicationCommands.SIGN_IN_CHECK:
    		//sign in validation from server
    		if(mes[2].equals(CommunicationCommands.S_CORRECT)) {
    			successfulAction=true;
    		}
    		successfulResponsefromServer=true;
    		break;
    	}
    }
    
    private void handleGameCommand(String mesReceived) {
    	String[] mes=mesReceived.split(" ");
    	String command=mes[1];
    	switch(command) {
    	case CommunicationCommands.S_NEW_PLAYER:
    		
    	case CommunicationCommands.S_PLAYER_QUIT:
    		
    	case CommunicationCommands.S_GAME_START:
    		
    	case CommunicationCommands.S_GAME_JOINED:
    		dispatcher.displayWaitingRoom(mes[2]);
    		dispatcher.addPlayer(mes[3]);
    	case CommunicationCommands.S_GAME_NOT_FOUND:
    		
    	case CommunicationCommands.S_GAME_ALREADY_STARTED:
    		
    	case CommunicationCommands.S_GAME_FULL:
    		
    	case CommunicationCommands.S_NEW_HOST:
    		
    	case CommunicationCommands.S_IS_WAITING:
    		dispatcher.update("Waiting for players...");
    		
    	default:
    		
    	}
    }

    /**
     * @param mail 
     * @param password
     * send the login and the password to the server. Throws any exception related to the connection with the server.
     * If the id are not correct, throws a WrondIdException.
     * Also ask for the server to send us the user data.
     */
    public void handleLogin(String email, String password) {
    	if(email.isBlank() || password.isBlank()) {
    		dispatcher.update("Please fill the form.");
    	}else {
	    	try {
	        	clientCL.openConnection();
	        	waitServerResponse();
	        	
            	clientCL.sendToServer(CommunicationCommands.STARTING+" "+CommunicationCommands.LOGIN_CHECK+" "+email+" "+password);
            	waitServerResponse();
            	if(successfulAction) {
            		clientCL.sendToServer(CommunicationCommands.STARTING+" "+CommunicationCommands.C_GET_USER);
            		successfulAction=false;
                	//Waiting for the server to send us the User
                	waitServerResponse();
                	dispatcher.displayMainHub();
            	}else {
            		clientCL.closeConnection();
            		dispatcher.update("Wrong ID.");
            		
            	}
	       
	        	
	        }catch(IOException e) {
	        	dispatcher.update("The server is unavailable, please try again later.");
	        	try {
	        		clientCL.closeConnection();
	        	}catch(IOException ex) {}
	        }
    	}
    	
    }
    
    /**
     * 
     * @param email
     * @param pseudo
     * @param password
     * send the email, pseudo and a secured password to the server in order to create a new account. Throws any exceptions related to the connection to the server.
     * If the email is already used, throws a AlreadyUsedMailException.
     * Also ask for the server to send us the user data.
     */
    public void handleSignIn(String email, String pseudo, String password) {
    	checkValidity(email,pseudo,password);
    	try {
        	clientCL.openConnection();
        }catch(IOException e) {
        	dispatcher.update("The server is unavailable, please try again later.");
        }
    	waitServerResponse();
    	String salt=PasswordUtils.getSalt(SALT_LENGTH);
    	String SecurePassword = PasswordUtils.generateSecurePassword(password, salt);
    	try {
    		clientCL.sendToServer(CommunicationCommands.STARTING+" "+CommunicationCommands.SIGN_IN_CHECK+" "+email+" "+pseudo+" "+SecurePassword+" "+salt);   		
    		waitServerResponse();
    		if(successfulAction) {
    			clientCL.sendToServer(CommunicationCommands.STARTING+" "+CommunicationCommands.C_GET_USER);
    			successfulAction=false;
    		}else {
    			clientCL.closeConnection();
    			dispatcher.update("Email already used.");
    		}
    	}catch(IOException e) {
    		try{
    			clientCL.closeConnection();
    		}catch(IOException ex) {}
    		dispatcher.update("The server is busy, please try again later.");
    	}
    	//Waiting for the server to send us the User
    	waitServerResponse();
    	dispatcher.displayMainHub();
    }
    
	public ArrayList<Item> getNotBoughtItems(){
		//Try to connect to the database
		try {
        	clientCL.openConnection();
        }catch(IOException e) {
        	dispatcher.update("The server is unavailable, please try again later.");
        }
    	waitServerResponse();
    	// Try to ask server to see the items
    	try {
    		int idUser = this.getCurrentUser().getId();
    		clientCL.sendToServer(CommunicationCommands.SHOP+" "+CommunicationCommands.GETITEMS+" "+idUser);   		
    		waitServerResponse();
    		return this.itemList;
    	}catch(IOException e) {
    		try{
    			clientCL.closeConnection();
    		}catch(IOException ex) {}
    		dispatcher.update("The server is busy, please try again later.");
    	}
    	// All cases are handled
    	System.out.println("unexpected error");
    	return null;
	}

    
  
    
    /**
     * 
     * @param email
     * @param pseudo
     * @param password
     * @throws InvalidIdException if the email is invalid, the pseudo is too short or too long, or if the password is too short.
     */
    private void checkValidity(String email, String pseudo, String password){
    	String[] mail=email.split("@");
    	if(mail.length!=2 || mail[0].isEmpty() || mail[1].isEmpty()) {
    		dispatcher.update("Your email is incorrect.");
    	}
    	
    	if(pseudo.length()<PSEUDO_MINIMAL_LENGTH) {
    		dispatcher.update("Your pseudonym is too short.");
    	}
    	
    	if(pseudo.length()>PSEUDO_MAXIMAL_LENGTH) {
    		dispatcher.update("Your pseudonym is too long.");
    	}
    	
    	if(password.length()<PASSWORD_MINIMAL_LENGTH) {
    		dispatcher.update("Your password is too short");
    	}

    }

    /**
     * allow the calling thread to wait for a positive response from the server to go on.
     */
    private void waitServerResponse() {
    	while(!successfulResponsefromServer) {
    		//add a timeout variable that closes all connection after a certain amount of time
    		try {
    			Thread.sleep(WAITING_TIME);
    		}catch(InterruptedException e) {}
    	}
		successfulResponsefromServer=false;
    }

    /**
     * 
     */
    public User getCurrentUser() {
        return this.currentUser;
    }



	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	
	/**
	 * @return the itemList
	 */
	public ArrayList<Item> getItemList() {
		return itemList;
	}



	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}



	/**
	 * Send to the server the intention of the client to join a public game.
	 */
	public void joinPublicGame() {
		try {
			clientCL.sendToServer(CommunicationCommands.GAME+" "+CommunicationCommands.C_JOIN_PUBLIC);
		}catch(IOException e) {
			dispatcher.update(USER_SENDING_ERROR);
		}
	}
	
	/**
	 * Send to the server the intention of the client to join a private game.
	 * @param code : the code of the game
	 */
	public void joinPrivateGame(String code) {
		try {
			clientCL.sendToServer(CommunicationCommands.GAME+" "+CommunicationCommands.C_JOIN_PRIVATE+" "+code);
		}catch(IOException e) {
			dispatcher.update(USER_SENDING_ERROR);
		}
	}
	
	/**
	 * Send to the server the intention of the client to create a private game.
	 */
	public void createGame() {
		try {
			clientCL.sendToServer(CommunicationCommands.GAME+" "+CommunicationCommands.C_CREATE_GAME);
		}catch(IOException e) {
			dispatcher.update(USER_SENDING_ERROR);
		}
	}

}