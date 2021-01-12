package server.PL;

import generalClasses.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public User find(int id) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
	    User user = null;
	    
	    	ResultSet result = connect
	                                    .createStatement(
	                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM User WHERE id = " + id
	                                             );
	            if(result.first()) {
	            	int id2=result.getInt("id");
	            	String pseudo=result.getString("pseudo");
	            	String mail=result.getString("email");
	            	String password=result.getString("password");
	            	String salt=result.getString("salt");
	            	int bestScore=result.getInt("bestScore");
	            	int money=result.getInt("money");
	            	user=new User(id2,pseudo,mail,password,salt,bestScore,money);
	            }
	            result.close();
	            return user;
	}

    
    /**
     * @param email 
     * @return
     */
    public User findByMail(String email) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
	    User user = null;
	    	ResultSet result = connect
	                                    .createStatement(
	                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM User WHERE email = '"+email+"'"
	                                             );
	            if(result.first()) {
	            	int id=result.getInt("id");
	            	String pseudo=result.getString("pseudo");
	            	String mail=result.getString("email");
	            	String password=result.getString("password");
	            	String salt=result.getString("salt");
	            	int bestScore=result.getInt("bestScore");
	            	int money=result.getInt("money");
	            	user=new User(id,pseudo,mail,password,salt,bestScore,money);
	            }
	            result.close();
	            return user;
    }

	/**
	 * Create a user in the database with the given data.
	 * @param email
	 * @param pseudo
	 * @param password
	 * @param salt
	 * @throws SQLException
	 */
    
    @Override
	public void create(User user) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
		String email=user.getEmail();
		String pseudo=user.getPseudo();
		String password=user.getPassword();
		String salt=user.getSalt();
		PreparedStatement prepare = connect
                .prepareStatement(
                    "INSERT INTO User (email, pseudo, password, salt, bestScore, money) VALUES ('"+email+"','"+pseudo+"','"+password+"','"+salt+"', 0, 0)"
                );
		prepare.executeUpdate();
		prepare.close();
	}

	@Override
	public void update(User user) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
		String email=user.getEmail();
		String pseudo=user.getPseudo();
		String password=user.getPassword();
		String salt=user.getSalt();
		int bestScore=user.getBestScore();
		int money=user.getUserMoney();
		int id=user.getId();
		PreparedStatement prepare = connect.prepareStatement(
				"UPDATE User SET pseudo='"+pseudo+"', email='"+email+"', password='"+password+"', salt='"+salt+"', bestScore="+bestScore+", money="+money+" WHERE id="+id
				);
		prepare.executeUpdate();
		prepare.close();
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
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