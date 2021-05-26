package model;

public class Voucher {
	private int id;
	private int discount;
	private String status;

	public int getId() {
		return id;
	}

	public int getDiscount() {
		return discount;
	}

	public String getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Voucher(int id, int discount, String status) {
		super();
		this.id = id;
		this.discount = discount;
		this.status = status;
	}
	
	
}
