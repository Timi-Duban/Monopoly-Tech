package generalClasses;

import java.io.Serializable;


public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idItem;
	private String itemName;
	private int price;
	private String urlImage;
	private String itemDescription = "";
	
	
	public Item(int idItem, String itemName, int price, String urlImage, String itemDescription) {
		super();
		this.idItem = idItem;
		this.itemName = itemName;
		this.price = price;
		this.urlImage = urlImage;
		this.itemDescription = itemDescription;
	}
	
	public Item(int idItem, String itemName, int price, String urlImage) {
		super();
		this.idItem = idItem;
		this.itemName = itemName;
		this.price = price;
		this.urlImage = urlImage;
	}
	
	
	
	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", itemName=" + itemName + ", price=" + price + ", urlImage=" + urlImage
				+ ", itemDescription=" + itemDescription + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
		      return false;
		if (getClass() != obj.getClass())
		      return false;
		return this.getIdItem() == ((Item) obj).getIdItem();
	}
	
	@Override
	public int hashCode() {
		return idItem * itemName.length()*price;
	}

	/**
	 * @return the idItem
	 */
	public int getIdItem() {
		return idItem;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @return the urlImage
	 */
	public String getUrlImage() {
		return urlImage;
	}
	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	
}
