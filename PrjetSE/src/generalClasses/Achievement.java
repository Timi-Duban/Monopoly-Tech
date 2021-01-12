package generalClasses;

import java.io.Serializable;


public class Achievement implements Serializable {
	private int idAchievement;
	private int amountAchievement;
	private int nbToReach;
	private int idUser;
	private int idAchievementUser;
	private String achievementType;
	private String achievementDescription;
	private boolean isAchieved;
	
	
	public Achievement(int idAchievementUser, int idAchievement, int idUser, String achievementDescription, String achievementType, int nbToReach, int amountAchievement, boolean isAchieved) {
		super();
		this.idAchievementUser = idAchievementUser;
		this.idAchievement = idAchievement;
		this.idUser = idUser;
		this.amountAchievement = amountAchievement;
		this.nbToReach = nbToReach;
		this.achievementType = achievementType;
		this.achievementDescription = achievementDescription;
		this.isAchieved = isAchieved;
	}
	
	public Achievement(String achievementDescription, int amountAchievement, boolean isAchieved) {
		super();
		this.idAchievementUser = 1;
		this.idAchievement = 1;
		this.idUser = 1;
		this.amountAchievement = amountAchievement;
		this.nbToReach = 1;
		this.achievementType = "game";
		this.achievementDescription = achievementDescription;
		this.isAchieved = isAchieved;
	}
	
	/**
	 * @return the idItem
	 */
	public int getIdAchievement() {
		return idAchievement;
	}
	
	/**
	 * @return the idItem
	 */
	public int getIdAchievementUser() {
		return idAchievementUser;
	}
	
	/**
	 * @return the idItem
	 */
	public int getIdUser() {
		return idUser;
	}
	
	/**
	 * @return the itemName
	 */
	public String getAchievementDescription() {
		return achievementDescription;
	}
	/**
	 * @return the price
	 */
	public int getNbToReachAchievement() {
		return nbToReach;
	}
	/**
	 * @return the urlImage
	 */
	public String getAchievementType() {
		return achievementType;
	}
	/**
	 * @return the itemDescription
	 */
	public int getAchievementAmount() {
		return amountAchievement;
	}
	public boolean isAchieved() {
		return isAchieved;
	}
	
	public void setIsAchieved(boolean b) {
		this.isAchieved = b;
	}
	
	
}
