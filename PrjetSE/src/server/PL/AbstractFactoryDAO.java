package server.PL;

import java.sql.Connection;
import java.sql.SQLException;

import generalClasses.User;

/**
 * 
 */
public abstract class AbstractFactoryDAO {

    private static AbstractFactoryDAO currentAF;
    private static Connection connect;

    /**
     * @return
     */
    public abstract DAO<User> createUserDAO();
    	
    /**
     * 
     */
    public static void openConnectionDatabase() throws SQLException {
        connect=ConnectionToDBMySQL.getInstance();
    }
    
    /**
     * Close the connection to the database. If the connection is not opened, do nothing.
     * @throws SQLException
     */
    public static void closeConnectionDatabase() throws SQLException{
    	if(connect!=null) {
    		connect.close();
    	}
    }
    
    /**
     * 
     * @return the opened connection to the database. OpenConnectionDatabase must be called before, otherwise throw a SQLException.
     */
    public static Connection getConnection() throws SQLException {
    	if(connect==null) {
    		throw new SQLException("Connection to the database must be opened first.");
    	}
    	return connect;
    }
  
    
    /**
     * @return 
     */
    public static AbstractFactoryDAO getInstance() {
        // TODO implement here
    	if(currentAF==null) {
    		currentAF=new FactoryUserMySQLDAO();
    	}
    	return currentAF;
    }

}