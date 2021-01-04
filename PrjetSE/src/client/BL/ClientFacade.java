package client.BL;

import generalClasses.PasswordUtils;
import generalClasses.User;
import generalClasses.exceptions.AlreadyUsedMailException;
import generalClasses.exceptions.ConnectionServerException;
import generalClasses.exceptions.InvalidIdException;
import generalClasses.exceptions.LoginException;
import generalClasses.exceptions.SendMessageException;
import generalClasses.exceptions.WrongIdException;

import com.lloseng.ocsf.client.*;

import client.UI.ClientController;

import java.io.IOException;
import java.util.*;

//To do : add a timeout in handleLogin
@SuppressWarnings("deprecation")
public class ClientFacade implements Observer {
	private final int PORT = 5556;
	private final String HOST = "localhost";
	
	private final String CONNECTION_ERROR="Connection server error";
	private final String SENDING_MESSAGE_ERROR="Sending message error";
	private final String USER_SENDING_ERROR="The server is busy, please try again later.";
	
	private final String LOGIN_CHECK="#login";
	private final String CORRECT="correct";
	private final String SIGN_IN_CHECK="#signin";
	
	//Constant string related to the "Play" use case
	private final String CREATE_GAME="#createGame";
	private final String JOIN_PRIVATE="#joinPrivateGame";
	private final String JOIN_PUBLIC="#joinPublicGame";
	private final String NEW_PLAYER="#newPlayer";
	private final String PLAYER_QUIT="#playerQuit";
	private final String QUIT_GAME="#quitGame";
	private final String GAME_START="#gameStart";
	private final String GAME_JOINED="#gameJoined";
	private final String GAME_NOT_FOUND="#gameNotFound";
	private final String GAME_ALREADY_STARTED="#gameAlreadyStarted";
	private final String GAME_FULL="#gameFull";
	
	
	private final int WAITING_TIME=200;
	private final int SALT_LENGTH=30;
	private final int PASSWORD_MINIMAL_LENGTH=7;
	private final int PSEUDO_MINIMAL_LENGTH=2;
	private final int PSEUDO_MAXIMAL_LENGTH=20;
	
	
	private User currentUser=null;
	private ObservableClient clientCL;
	private ClientController controller;
	
	private boolean successfulResponsefromServer=false;
	private boolean successfulAction=false;

	/**
     * Default constructor
     */
    public ClientFacade() {
	    clientCL= new ObservableClient(HOST,PORT);
	    controller=ClientController.getInstance();
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
    		System.out.println(mes);
	    	if(mes.equals(ObservableClient.CONNECTION_ESTABLISHED)) {
	    		//Handle successful connection to the server
	    		successfulResponsefromServer=true;
	    	}else if(mes.equals(ObservableClient.CONNECTION_CLOSED)) {
	    		//happens if the connection is properly closed
				  
				  
	    	}else if(mes.split(" ")[0].equals(LOGIN_CHECK)) {
	    		//Id validation from server
	    		if(mes.split(" ")[1].equals(CORRECT)) {
	    			successfulAction=true;
	    		}
	    		successfulResponsefromServer=true;
	    	} else if(mes.split(" ")[0].equals(SIGN_IN_CHECK)){
	    		//sign in validation from server
	    		if(mes.split(" ")[1].equals(CORRECT)) {
	    			successfulAction=true;
	    		}
	    		successfulResponsefromServer=true;
	    	}
    	}else if(arg instanceof User) {
    		setCurrentUser((User) arg);
    		successfulResponsefromServer=true;
    	}else if(arg instanceof Exception) {
    		//Handle a sudden disconnection from the server
		  
    	}
    }
    


    /**
     * @param mail 
     * @param password
     * send the login and the password to the server. Throws any exception related to the connection with the server.
     * If the id are not correct, throws a WrondIdException.
     * Also ask for the server to send us the user data.
     */
    public void handleLogin(String email, String password) throws LoginException {
    	if(email.isBlank() || password.isBlank()) {
    		throw new InvalidIdException("Please fill the form.");
    	}
    	try {
        	clientCL.openConnection();
        }catch(IOException e) {
        	throw new ConnectionServerException(CONNECTION_ERROR);
        }
    	waitServerResponse();
    	try {
        	clientCL.sendToServer(LOGIN_CHECK+" "+email+" "+password);
        	waitServerResponse();
        	if(successfulAction) {
        		clientCL.sendToServer("#getCurrentUser");
        		successfulAction=false;
        	}else {
        		clientCL.closeConnection();
        		throw new WrongIdException();
        		
        	}
        }catch(IOException e) {
        	try {
        		clientCL.closeConnection();
        	}catch(IOException ex) {}
        	throw new SendMessageException(SENDING_MESSAGE_ERROR);
        }
    	
    	//Waiting for the server to send us the User
    	waitServerResponse();
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
    public void handleSignIn(String email, String pseudo, String password) throws LoginException {
    	checkValidity(email,pseudo,password);
    	try {
        	clientCL.openConnection();
        }catch(IOException e) {
        	throw new ConnectionServerException(CONNECTION_ERROR);
        }
    	waitServerResponse();
    	String salt=PasswordUtils.getSalt(SALT_LENGTH);
    	String SecurePassword = PasswordUtils.generateSecurePassword(password, salt);
    	try {
    		clientCL.sendToServer(SIGN_IN_CHECK+" "+email+" "+pseudo+" "+SecurePassword+" "+salt);   		
    		waitServerResponse();
    		if(successfulAction) {
    			clientCL.sendToServer("#getCurrentUser");
    			successfulAction=false;
    		}else {
    			clientCL.closeConnection();
    			throw new AlreadyUsedMailException();
    		}
    	}catch(IOException e) {
    		throw new SendMessageException(SENDING_MESSAGE_ERROR);
    	}
    	//Waiting for the server to send us the User
    	waitServerResponse();

    }
    
    /**
     * 
     * @param email
     * @param pseudo
     * @param password
     * @throws InvalidIdException if the email is invalid, the pseudo is too short or too long, or if the password is too short.
     */
    private void checkValidity(String email, String pseudo, String password) throws InvalidIdException {
    	String[] mail=email.split("@");
    	if(mail.length!=2 || mail[0].isEmpty() || mail[1].isEmpty()) {
    		throw new InvalidIdException("Your email is incorrect.");
    	}
    	
    	if(pseudo.length()<PSEUDO_MINIMAL_LENGTH) {
    		throw new InvalidIdException("Your pseudonym is too short.");
    	}
    	
    	if(pseudo.length()>PSEUDO_MAXIMAL_LENGTH) {
    		throw new InvalidIdException("Your pseudonym is too long.");
    	}
    	
    	if(password.length()<PASSWORD_MINIMAL_LENGTH) {
    		throw new InvalidIdException("Your password is too short");
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
	 * Send to the server the intention of the client to join a public game.
	 */
	public void joinPublicGame() {
		try {
			throw new IOException();
			//clientCL.sendToServer(JOIN_PUBLIC);
		}catch(IOException e) {
			controller.showNotification(USER_SENDING_ERROR);
		}
	}
	
	/**
	 * Send to the server the intention of the client to join a private game.
	 * @param code : the code of the game
	 */
	public void joinPrivateGame(int code) {
		
	}
	
	/**
	 * Send to the server the intention of the client to create a private game.
	 */
	public void createGame() {
		
	}

}