package server.BL;

import java.util.*;

/**
 * 
 */
public abstract class ServerFacade implements Observer {

    /**
     * Default constructor
     */
    public ServerFacade() {
    }



    /**
     * @param message
     */
    public void handleConnectionFromCLient(String message) {
        // TODO implement here
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

}