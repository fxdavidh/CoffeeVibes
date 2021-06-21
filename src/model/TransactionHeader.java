package model;

import java.sql.Date;
import java.util.ArrayList;

public class TransactionHeader {
	private int id;
	private Date purchaseDate;
	private int voucherId;
	
	public int getId() {
		return id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public int getVoucherId() {
		return voucherId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

	public TransactionHeader(int id, Date purchaseDate, int voucherId) {
		super();
		this.id = id;
		this.purchaseDate = purchaseDate;
		this.voucherId = voucherId;
	}
	
}
