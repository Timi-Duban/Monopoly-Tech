import java.sql.*;

public class ConnectionMySQL {
	
	private static final String URL_BD = "jdbc:mysql://i......";

	private static final String LOGIN = "root";
 
	private static final String MOT_DE_PASSE = "";
	
	private static Connection connexion = null;

    /**
     * Méthode qui va nous retourner notre instance
     * et la créer si elle n'existe pas...
     * @return
     */
	public static Connection getInstance(){
        if(connexion == null){
            try {
                connexion = DriverManager.getConnection(URL_BD, LOGIN, MOT_DE_PASSE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }        
        return connexion;
    } 
}
