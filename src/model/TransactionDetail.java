package model;

public class TransactionDetail {
	private int id;
	private int productId;
	private int qty;

	public int getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}

	public int getQty() {
		return qty;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public TransactionDetail(int id, int productId, int qty) {
		super();
		this.id = id;
		this.productId = productId;
		this.qty = qty;
	}
}
