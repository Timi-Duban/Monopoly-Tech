/**
 * 
 */
public class Utilisateur {

	
    /**
     * Default constructor
     */	
	private long ID_Utilisateur;
	private String pseudo;
	private String email;
	private String password;
	
	
    /**
     * Default constructor
     */	
	public Utilisateur(long ID_Utilisateur, String pseudo, String email, String password) {
		this.ID_Utilisateur = ID_Utilisateur;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
    }

    /**
     * 
     */
    public long getID_Utilisateur() {
    	return ID_Utilisateur;
    }
    public void setID_Utilisateur(int ID_Utilisateur) {
    	this.ID_Utilisateur = ID_Utilisateur;
    }
    
    /**
     * 
     */
    public String getPseudo() {
    	return pseudo;
    }
    public void setPseudo(String pseudo) {
    	this.pseudo = pseudo;
    }

    /**
     * 
     */
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String toString() {
    	return ID_Utilisateur+" - "+pseudo+" - "+email;
    }

}