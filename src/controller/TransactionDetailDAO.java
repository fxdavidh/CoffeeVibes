package controller;

import model.TransactionDetailModel;

public class TransactionDetailDAO {
	
	private TransactionDetailModel td = new TransactionDetailModel();
	
	public void insertDetail(int ID, int productID, int quantity) {
		td.insert(ID, productID, quantity);
	}
}
