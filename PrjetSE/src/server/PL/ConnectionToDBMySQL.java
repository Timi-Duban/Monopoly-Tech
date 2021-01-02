package server.PL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 */
public class ConnectionToDBMySQL {


    
    
    private static final String URL_BD = "jdbc:mysql://localhost/monopoly?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private static final String LOGIN = "root";
 
	private static final String MOT_DE_PASSE = "azerty";
	
	private static Connection connexion = null;

    /**
     * Méthode qui va nous retourner notre instance
     * et la créer si elle n'existe pas...
     * @return
     */
	public static Connection getInstance() throws SQLException{
        if(connexion == null){
        	connexion = DriverManager.getConnection(URL_BD, LOGIN, MOT_DE_PASSE);
        }        
        return connexion;
    } 
}