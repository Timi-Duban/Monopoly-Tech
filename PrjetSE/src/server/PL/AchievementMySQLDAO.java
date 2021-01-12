package server.PL;

import generalClasses.Achievement;
import generalClasses.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * 
 */
public class AchievementMySQLDAO extends DAO<Achievement> {

    /**
     * Default constructor
     */
    public AchievementMySQLDAO() {
    }
    
    
    
    @Override
	public Achievement find(int id) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
	    Achievement achievement = null;
	    
	    	ResultSet result = connect
	                                    .createStatement(
	                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Achievement NATURAL JOIN AchievementUser WHERE id = " + id
	                                             );
	            if(result.first()) {
	            	int id2=result.getInt("idAchievement");
	            	String achievementDescription=result.getString("achievementDescription");
	            	String achievementType=result.getString("achievementType");
	           
	            	int nbToReach=result.getInt("nbToReach");
	            	int amountAchievement=result.getInt("amountAchievement");
	            	int idAchievementUser=result.getInt("idAchievementUser");
	        		int idAchievement =result.getInt("idAchievement");
	        		boolean isAchieved = result.getBoolean("isAchieved");
	            	achievement =new Achievement(idAchievementUser,idAchievement,id2,achievementDescription,achievementType,nbToReach,amountAchievement,isAchieved);
	            }
	            result.close();
	            return achievement;
	}

    @Override
	public Achievement findByMail(String type) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
	    Achievement achievement = null;
	    
	    	ResultSet result = connect
	                                    .createStatement(
	                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Achievement WHERE achievementType = " + type
	                                             );
	            if(result.first()) {
	            	int id2=result.getInt("idAchievement");
	            	String achievementDescription=result.getString("achievementDescription");
	            	String achievementType=result.getString("achievementType");
	           
	            	int nbToReach=result.getInt("nbToReach");
	            	int amountAchievement=result.getInt("amountAchievement");
	            	int idAchievementUser=result.getInt("idAchievementUser");
	        		int idAchievement =result.getInt("idAchievement");
	        		boolean isAchieved = result.getBoolean("isAchieved");
	            	achievement =new Achievement(idAchievementUser,idAchievement,id2,achievementDescription,achievementType,nbToReach,amountAchievement,isAchieved);
	            }
	            result.close();
	            return achievement;
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
	public void create(Achievement achievement) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
		int idAchievementUser=achievement.getIdAchievementUser();
		int idAchievement =achievement.getIdAchievement();
		int id =achievement.getIdUser();
		boolean isAchieved = achievement.isAchieved();
		PreparedStatement prepare = connect
                .prepareStatement(
                		 "INSERT INTO AchievementUser (idAchievementUser, idAchievement, id, isAchieved) VALUES ('"+idAchievementUser+"','"+idAchievement+"',"+id+","+isAchieved+")");
		prepare.executeUpdate();
		prepare.close();
	}

	@Override
	public void update(Achievement achievement) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
		int id = achievement.getIdAchievement();
		String description=achievement.getAchievementDescription();
		String type=achievement.getAchievementType();
		int nbToReach =achievement.getNbToReachAchievement();
		int amount =achievement.getAchievementAmount();
		PreparedStatement prepare = connect
                .prepareStatement(
                    "UPDATE Achievement "
                    + "SET "
                    + "    description = " + description
                    + "    type =" + type
                    + "    nbToReach = " + nbToReach
                    + "    amount = " + amount
                    
                    + "WHERE"
                    + "    idAchievement = " + id + ";"
                );
		prepare.executeUpdate();
		prepare.close();
		
		
	}

	@Override
	public void delete(Achievement obj) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Achievement> findAchievementUser(int idUser) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		
		
	    
	    	ResultSet result = connect
	                                    .createStatement(
	                                               // ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                               // ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Achievement NATURAL JOIN AchievementUser WHERE id = " + idUser
	                                             );
	    	while(result.next()){
	    		int id2=result.getInt("idAchievement");
            	String achievementDescription=result.getString("achievementDescription");
            	String achievementType=result.getString("achievementType");
           
            	int nbToReach=result.getInt("nbToReach");
            	int amountAchievement=result.getInt("amountAchievement");
            	int idAchievementUser=result.getInt("idAchievementUser");
        		int idAchievement =result.getInt("idAchievement");
        		boolean isAchieved = result.getBoolean("isAchieved");
            	Achievement achievement =new Achievement(idAchievementUser,idAchievement,id2,achievementDescription,achievementType,nbToReach,amountAchievement,isAchieved);
            	achievements.add(achievement);
            }
	            result.close();
	            return achievements;
	}
	


    @Override
	public Achievement findAll() throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
		Achievement achievement = null;
		
	    
	    
	    	ResultSet result = connect
	                                    .createStatement(
	                                               // ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                               // ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Achievement NATURAL JOIN AchievementUser"
	                                             );
	    	while (result.next()) {
	    		int id2=result.getInt("idAchievement");
            	String achievementDescription=result.getString("achievementDescription");
            	String achievementType=result.getString("achievementType");
           
            	int nbToReach=result.getInt("nbToReach");
            	int amountAchievement=result.getInt("amountAchievement");
            	int idAchievementUser=result.getInt("idAchievementUser");
        		int idAchievement =result.getInt("idAchievement");
        		boolean isAchieved = result.getBoolean("isAchieved");
                
                achievement =new Achievement(idAchievementUser,idAchievement,id2,achievementDescription,achievementType,nbToReach,amountAchievement,isAchieved);
            	
               
            }
	    result.close();
        return achievement;

	}

    public void createAchievementForUser(User user) throws SQLException {
		Connection connect=AbstractFactoryDAO.getConnection();
		
		int id =user.getId();
		boolean isAchieved = false;
		System.out.println("id ="+id);
		PreparedStatement prepare = connect
                .prepareStatement("INSERT INTO AchievementUser (idAchievement, id, isAchieved) VALUES "
                				+ "( 1, "+id+","+ isAchieved + "),"
                                + "( 2, "+id+", "+ isAchieved +"),"
                                + "( 3, "+id+","+ isAchieved + "),"
                                + "( 4, "+id+","+ isAchieved + "),"
                                + "( 5, "+id+","+ isAchieved + "),"
                                + "( 6, "+id+","+ isAchieved + "),"
                                + "( 7, "+id+","+ isAchieved + "),"
                                + "( 8, "+id+","+ isAchieved + "),"
                                + "( 9, "+id+","+ isAchieved + "),"
                                + "( 10, "+id+","+ isAchieved + "),"
                                + "( 11, "+id+","+ isAchieved + "),"
                                + "( 12, "+id+","+ isAchieved + ");"
                               );
		prepare.executeUpdate();
		prepare.close();
	}

}
