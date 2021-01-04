package generalClasses;

import java.io.Serializable;

/**
 * 
 */
@SuppressWarnings("serial")
public class User implements Serializable {
	
    private int id;
    private String pseudo;
    private String email;
    private String password;
    private String salt;
    private int bestScore = 0;
    private int userMoney = 0;

    
    /**
     * Called whenever we retrieved a user from the database.
     * @param id
     * @param pseudo
     * @param email
     * @param password
     * @param salt
     * @param bestScore
     * @param userMoney
     */
	public User(int id, String pseudo, String email, String password,String salt, int bestScore, int userMoney) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.salt=salt;
		this.bestScore = bestScore;
		this.userMoney = userMoney;
	}
	
	/**
	 * Called whenever we create a new user in the database
	 * @param pseudo
	 * @param email
	 * @param password
	 * @param salt
	 */
	public User (String pseudo, String email, String password,String salt) {
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.salt=salt;
		
		this.id=0;
		this.bestScore=0;
		this.userMoney=0;
	}

	/**
	 * @return the iD
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}


	/**
	 * @return the bestScore
	 */
	public int getBestScore() {
		return bestScore;
	}

	/**
	 * @return the userMoney
	 */
	public int getUserMoney() {
		return userMoney;
	}

    /**
     * @param friend
     */
 
	public String toString() {
		return pseudo+"\n"+email+"\n"+bestScore+"\n"+userMoney;
	}
}