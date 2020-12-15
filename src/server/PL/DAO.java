package server.PL;

import java.util.*;

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
    public abstract T find(int id);

    /**
     * @param obj
     */
    public abstract void create(T obj);

    /**
     * @param obj
     */
    public abstract void update(T obj);
    
    public abstract void delete(T obj);



    /**
     * @return
     */
    public T findAll() {
        // TODO implement here
        return null;
    }

}