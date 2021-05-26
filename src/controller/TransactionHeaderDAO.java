package controller;

import java.util.ArrayList;

import model.TransactionHeader;
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
	
	public ArrayList<TransactionHeader> getTransactionHeaders(){
		ArrayList<TransactionHeader> transactions = thd.getTransactionHeaders();
		return transactions;
	}
}
