package client.BL;

import generalClasses.User;
import java.util.*;

/**
 * 
 */
public class ClientFacade implements Observer {

	
	public User currentUser=null;
    /**
     * Default constructor
     */
    public ClientFacade() {
    }



    /**
     * @param obs  
     * @param arg
     */
    public void update(Observable obs , Object arg ) {
        // TODO implement here
    }

    /**
     * @param mail 
     * @param password
     */
    public void handleLogin(String mail, String password) {
        // TODO implement here
    }

    /**
     * @param userConnected
     */
    public void handleConnectionFromServer(User userConnected) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getCurrentUser() {
        // TODO implement here
    }

}