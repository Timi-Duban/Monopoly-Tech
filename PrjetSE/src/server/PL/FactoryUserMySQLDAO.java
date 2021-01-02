package server.PL;


import generalClasses.User;

/**
 * 
 */
public class FactoryUserMySQLDAO extends AbstractFactoryDAO {

    /**
     * Default constructor
     */
    public FactoryUserMySQLDAO() {}

	@Override
	public DAO<User> createUserDAO() {
		return new UserMySQLDAO();
	}

}