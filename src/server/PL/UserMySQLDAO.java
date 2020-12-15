package server.PL;

import generalClasses.User;
import java.util.*;

/**
 * 
 */
public class UserMySQLDAO extends DAO<User> {

    /**
     * Default constructor
     */
    public UserMySQLDAO() {
    }
    
    
    
    @Override
	public User find(int id) {
		// TODO Auto-generated method stub
	    User user = null;
	    try {
	    	ResultSet result = this .connect
	                                    .createStatement(
	                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM utilisateur WHERE ID_Utilisateur = " + id
	                                             );
	            if(result.first()) {
	                    user = new User(
	                                        id, result.getString("pseudo"), 
	                                        result.getString("email"),
	                                        result.getString("password")
	                                    );
	            }
	            } catch (SQLException e) {
	                    e.printStackTrace();
	            }
	           return user;
	}

	@Override
	public void create(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}
    /**
     * @param email 
     * @return
     */
    public User findByMail(String email) {
        // TODO implement here
        return null;
    }

    /**
     * @param pseudo 
     * @return
     */
    public User findByPseudo(String pseudo) {
        // TODO implement here
        return null;
    }

}