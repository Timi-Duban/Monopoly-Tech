
import java.util.*;

/**
 * 
 */
public class PostgreSQLDAOFactory extends AbstractDAOFactory {

    /**
     * Default constructor
     */
	@Override
	public DAO<User> getUtilisateurMySQLDAO() {
		// TODO Auto-generated method stub
		return new UtilisateurPostgreSQLDAO();
	}

}