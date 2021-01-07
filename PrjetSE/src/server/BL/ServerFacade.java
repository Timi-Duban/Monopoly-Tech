package server.BL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import com.lloseng.ocsf.server.ConnectionToClient;
import com.lloseng.ocsf.server.ObservableOriginatorServer;
import com.lloseng.ocsf.server.OriginatorMessage;

import generalClasses.User;
import generalClasses.CommunicationCommands;
import generalClasses.PasswordUtils;
import server.PL.AbstractFactoryDAO;
import server.PL.DAO;
import server.UI.ServerController;

/**
 * 
 */
@SuppressWarnings("deprecation")
public class ServerFacade implements Observer {
	
	final public static int DEFAULT_PORT = 5556;
	
	private ObservableOriginatorServer serverCL;
	private ServerController controller;
	
	
   
	/**
     * Default constructor
     */
    public ServerFacade() {
        this.serverCL=new ObservableOriginatorServer(DEFAULT_PORT);
        this.serverCL.addObserver(this);
        this.controller=ServerController.getInstance();
    }



   /**
    * @param message : the message received from the client
    * @param originator : the client
    */
    private void handleMessageFromClient(String message,ConnectionToClient originator) throws IOException {
        String[] mes=message.split(" ");
        switch(mes[0]) {
        case CommunicationCommands.GAME:
        	handleGameCommand(message, originator);
        	break;
        case CommunicationCommands.STARTING:
        	handleStartingCommand(message,originator);
        	break;
        
        
        
        
       
        case "#closeConnection":
        	originator.close();
        	break;
        }
        		
    }

    /**
     * Handle command related to signin/login
     * @param mesReceived
     * @param originator
     * @throws IOException
     */
    private void handleStartingCommand(String mesReceived, ConnectionToClient originator) throws IOException {
    String[] mes=mesReceived.split(" ");
    String command=mes[1];
    switch(command) {
    case CommunicationCommands.LOGIN_CHECK:
    	//test if the id (mes[1] and mes [2]) are correct. Store User (setInfo("user",User))
    	if(mes.length>=3) {
    		try {
    			AbstractFactoryDAO.openConnectionDatabase();
    			DAO<User> userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
    			User user=userDAO.findByMail(mes[2]);
    			AbstractFactoryDAO.closeConnectionDatabase();
    			if(user!=null && PasswordUtils.verifyUserPassword(mes[3], user.getPassword(), user.getSalt())) {
    				originator.setInfo("user", user);
    				originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.LOGIN_CHECK+" "+CommunicationCommands.S_CORRECT);
    			}else {
    				originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.LOGIN_CHECK+" "+CommunicationCommands.S_INCORRECT);
    				originator.close();
    			}
    		}catch(SQLException e) {
    			originator.sendToClient("#dbUnaivailable");
    			originator.close();
    		}
    	}else {
    		originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.LOGIN_CHECK+" "+CommunicationCommands.S_INCORRECT);
    		originator.close();
    	}
    	break;
    case CommunicationCommands.SIGN_IN_CHECK:
    	if(mes.length>=6) {
    		try {
    			AbstractFactoryDAO.openConnectionDatabase();
    			DAO<User> userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
    			User user=userDAO.findByMail(mes[2]);
    			if(user!=null) {
    				originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.SIGN_IN_CHECK+" "+CommunicationCommands.S_INCORRECT);
    				originator.close();
				}else{
					String email=mes[2];
					String pseudo=mes[3];
					String password=mes[4];
					String salt=mes[5];
					user=new User(pseudo,email,password,salt);
					userDAO.create(user);
					user=userDAO.findByMail(email);
					originator.setInfo("user", user);
					originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.SIGN_IN_CHECK+" "+CommunicationCommands.S_CORRECT);
				}
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    		finally {
    			try{
    				AbstractFactoryDAO.closeConnectionDatabase();
    			}catch(SQLException e) {}
    		}
    	}else {
    		originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.SIGN_IN_CHECK+" "+CommunicationCommands.S_INCORRECT);
    		originator.close();
    	}
    	break;
    case CommunicationCommands.C_GET_USER:
    	originator.sendToClient((User)(originator.getInfo("user")));
    	break;
    }
    }
    
    /**
     * Handle command related to "Play" use case
     * @param mesReceived
     * @param client
     */
    private void handleGameCommand(String mesReceived,ConnectionToClient client) {
    	
    }

    /**
     * @return
     */
    public User[] findAllUsers() {
        // TODO implement here
        return null;
    }

    /**
     * @param message
     */
    public void sendToAllUsers(String message) {
        // TODO implement here
    }



	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof OriginatorMessage) {
			OriginatorMessage mesStruct=(OriginatorMessage) arg;
			ConnectionToClient originator=mesStruct.getOriginator();
			String message=(String)mesStruct.getMessage();
			System.out.println(message);
			
			if(message.equals(ObservableOriginatorServer.SERVER_STARTED)) {
				
			}else if(message.equals(ObservableOriginatorServer.SERVER_STOPPED)) {
				
			}else if(message.equals(ObservableOriginatorServer.SERVER_CLOSED)) {
				
			}else if(message.equals(ObservableOriginatorServer.CLIENT_CONNECTED)) {
				
			}else if(message.contains(ObservableOriginatorServer.CLIENT_DISCONNECTED)) {
				
			}else if(message.contains(ObservableOriginatorServer.CLIENT_EXCEPTION)) {
				 
			}else if(message.contains(ObservableOriginatorServer.LISTENING_EXCEPTION)) {
				
			}else{
				try {
					handleMessageFromClient(message,originator);
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		  }
		
	}
	
	/**
	 * Start the server
	 * @throws IOException
	 */
	public void startServer() throws IOException {
		this.serverCL.listen();
	}

}