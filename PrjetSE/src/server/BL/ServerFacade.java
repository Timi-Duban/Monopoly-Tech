package server.BL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import com.lloseng.ocsf.server.ConnectionToClient;
import com.lloseng.ocsf.server.ObservableOriginatorServer;
import com.lloseng.ocsf.server.OriginatorMessage;

import generalClasses.User;
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
        System.out.println(message);
        switch(mes[0]) {
        	case "#login":
        		//test if the id (mes[1] and mes [2]) are correct. Store User (setInfo("user",User))
        		if(mes.length>=3) {
        			try {
        				AbstractFactoryDAO.openConnectionDatabase();
        				DAO<User> userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        				User user=userDAO.findByMail(mes[1]);
        				AbstractFactoryDAO.closeConnectionDatabase();
        				if(user!=null && PasswordUtils.verifyUserPassword(mes[2], user.getPassword(), user.getSalt())) {
        					originator.setInfo("user", user);
        					originator.sendToClient("#login correct");
        				}else {
        					
        					originator.sendToClient("#login incorrect");
        					originator.close();
        				}
        			}catch(SQLException e) {
        				originator.sendToClient("#dbUnaivailable");
        				originator.close();
        			}
        		}else {
        			System.out.println("bbb");
        			originator.sendToClient("#login incorrect");
        			originator.close();
        		}
        		break;
        	case "#signin":
        		if(mes.length>=5) {
        			try {
        				AbstractFactoryDAO.openConnectionDatabase();
        				DAO<User> userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        				User user=userDAO.findByMail("aaa");
        				if(user!=null) {
        					originator.sendToClient("#signin incorrect");
        					originator.close();
        				}else{
        					String email=mes[1];
        					String pseudo=mes[2];
        					String password=mes[3];
        					String salt=mes[4];
        					user=new User(pseudo,email,password,salt);
        					userDAO.create(user);
        					user=userDAO.findByMail(email);
        					originator.setInfo("user", user);
        					originator.sendToClient("#signin correct");
        				}
        				AbstractFactoryDAO.closeConnectionDatabase();
        			}catch(SQLException e) {
        				e.printStackTrace();
        			}
        		}else {
        			originator.sendToClient("#signin incorrect");
        			originator.close();
        		}
        		break;
        	case "#getCurrentUser":
        		originator.sendToClient((User)(originator.getInfo("user")));
        		break;
        	case "#closeConnection":
        		originator.close();
        		break;
        }
        		
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