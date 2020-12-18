
import java.util.*;

/**
 * 
 */
public class MySQLDAOFactory extends AbstractDAOFactory {

    /**
     * Default constructor
     */


	@Override
	public DAO<Utilisateur> getUtilisateurMySQLDAO() {
		// TODO Auto-generated method stub
		return new UtilisateurMySQLDAO();
	}

}