
import java.util.*;
import java.sql.*;
/**
 * 
 */
public class UtilisateurMySQLDAO extends DAO<Utilisateur> {



	@Override
	public Utilisateur find(long id) {
		// TODO Auto-generated method stub
	    Utilisateur user = null;
	    try {
	    	ResultSet result = this .connect
	                                    .createStatement(
	                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM utilisateur WHERE ID_Utilisateur = " + id
	                                             );
	            if(result.first()) {
	                    user = new Utilisateur(
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
	public Utilisateur create(Utilisateur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur update(Utilisateur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Utilisateur obj) {
		// TODO Auto-generated method stub
		
	}

}