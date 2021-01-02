package server.PL;

import java.sql.SQLException;

/**
 * 
 */
public abstract class DAO<T> {

    /**
     * Default constructor
     */
    public DAO() {
    }

    /**
     * @param id 
     * @return
     */
    public abstract T find(int id) throws SQLException;

    public abstract T findByMail(String email) throws SQLException;

    
    /**
     * @param obj
     */
    public abstract void create(T obj) throws SQLException;
    /**
     * @param obj
     */
    public abstract void update(T obj) throws SQLException;
    
    public abstract void delete(T obj) throws SQLException;

    


    /**
     * @return
     */
    public T findAll() throws SQLException {
        // TODO implement here
        return null;
    }

}