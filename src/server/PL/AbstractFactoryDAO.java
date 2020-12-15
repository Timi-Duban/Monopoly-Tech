package server.PL;

import java.util.*;

import generalClasses.User;

/**
 * 
 */
public abstract class AbstractFactoryDAO {

    private static AbstractFactoryDAO currentAF;


    /**
     * @return
     */
    public abstract DAO<User> createUserDAO();

    /**
     * 
     */
    public static void openConnectionDatabase() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static AbstractFactoryDAO getInstance() {
        // TODO implement here
    	if(currentAF==null) {
    		currentAF=new FactoryUserMySQLDAO();
    		return currentAF;
    	}else {
    		return currentAF;
    	}
    }

}