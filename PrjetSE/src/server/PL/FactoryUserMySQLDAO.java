package server.PL;


import generalClasses.Achievement;
import generalClasses.Item;
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

	@Override
	public DAO<Item> createItemDAO() {
		return new ItemMySQLDAO();
	}
	
	@Override
	public DAO<Achievement> createAchievementDAO() {
		return new AchievementMySQLDAO();
	}
	

}