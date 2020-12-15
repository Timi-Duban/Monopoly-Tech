package generalClasses;

import java.util.*;

/**
 * 
 */
public class User {

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * 
     */
    public int ID;

    /**
     * 
     */
    public String pseudo;

    /**
     * 
     */
    public String email;

    /**
     * 
     */
    public String password;

    /**
     * 
     */
    public int bestScore = 0;

    /**
     * 
     */
    public boolean admin;

    /**
     * 
     */
    public int userMoney = 0;

    /**
     * @param friend
     */
    public void addFriend(User friend) {
        // TODO implement here
    }

    /**
     * @param friend
     */
    public void deleteFriend(User friend) {
        // TODO implement here
    }

    /**
     * @return
     */
    public User getFriends() {
        // TODO implement here
        return null;
    }

    /**
     * @param code
     */
    public void joinGame(String code) {
        // TODO implement here
    }

    /**
     * @param code
     */
    public void createGame(String code) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getBestScoresStatic() {
        // TODO implement here
    }

    /**
     * 
     */
    public void showAchievements() {
        // TODO implement here
    }

    /**
     * @param code
     */
    public void spectatePlay(String code) {
        // TODO implement here
    }

    /**
     * @param message
     */
    public void sendToAllUsers(String message) {
        // TODO implement here
    }

    /**
     * @param user
     */
    public void banUser(User user) {
        // TODO implement here
    }

}