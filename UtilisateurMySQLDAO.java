
import java.util.*;
import java.sql.*;
/**
 * 
 */
public class UtilisateurMySQLDAO extends DAO<User> {



	@Override
	public User find(long id) {
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
	public User create(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}

}