
import java.util.*;
import java.sql.*;
/**
 * 
 */
public class UtilisateurMySQLDAO extends DAO<Utilisateur> {

	@Override
	public Utilisateur find(String email, String motdepasse) {
		// TODO Auto-generated method stub
		Utilisateur user = null;
    	try {    			
	    	ResultSet result = this .connect
                    .createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_UPDATABLE
                             ).executeQuery(
                            		 "Select * From users where email = "+"'"+email+"'"+" and motdepasse = "+"'"+motdepasse+"'"
                             );
			
			
			if(result.next()) {
					user = new Utilisateur(
                            result.getInt("id"), result.getString("pseudo"), 
                            email,
                            motdepasse
                        );
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	  	
    	return user;
	}

	@Override
	public Utilisateur find(int id) {
		// TODO Auto-generated method stub
	    Utilisateur user = null;
    	try {    			
	    	ResultSet result = this .connect
                    .createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_UPDATABLE
                             ).executeQuery(
                            		 "Select * From users where id ="+id
                             );
			
			
			if(result.next()) {
					user = new Utilisateur(
                            id, result.getString("pseudo"), 
                            result.getString("email"),
                            result.getString("motdepasse")
                        );
			}
		} catch (Exception e1) {
			e1.printStackTrace();
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