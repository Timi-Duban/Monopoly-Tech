
import java.util.*;

/**
 * 
 */
public abstract class AbstractDAOFactory {

    /**
     * Default constructor
     */


    public abstract DAO getUtilisateurMySQLDAO();


    /**
     * Méthode nous permettant de récupérer une factory de DAO
     * @param type
     * @return AbstractDAOFactory
     */
    public static AbstractDAOFactory getFactory(FactoryType type){
   
        if(type.equals(type.MYSQL_DAO_FACTORY)) 
            return new MySQLDAOFactory();
        
        if(type.equals(type.POSTGRESQL_DAO_FACTORY))
            return new PostgreSQLDAOFactory();
        
        return null;
    }
    
}