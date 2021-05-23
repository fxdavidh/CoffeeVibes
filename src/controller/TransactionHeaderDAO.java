package controller;

import model.TransactionHeaderModel;

public class TransactionHeaderDAO {
	
	private TransactionHeaderModel thd = new TransactionHeaderModel();
	
	public int getNextAI() {
		int index = thd.getNextAI();
		return index;
	}
	
	public void insertHeader(int voucherID) {
		thd.insert(voucherID);
	}
}
