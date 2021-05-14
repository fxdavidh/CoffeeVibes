package model;

import java.util.ArrayList;

public class TransactionHeader {
	private int id;
	private String purchaseDate;
	private int voucherId;
	private int totalPrice;
	private ArrayList<String> itemList = new ArrayList<String>();

	public int getId() {
		return id;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public int getVoucherId() {
		return voucherId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public ArrayList<String> getItemList() {
		return itemList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setItemList(ArrayList<String> itemList) {
		this.itemList = itemList;
	}

}
