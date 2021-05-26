package controller;

import java.util.ArrayList;

import model.TransactionDetail;
import model.TransactionDetailModel;

public class TransactionDetailDAO {
	
	private TransactionDetailModel td = new TransactionDetailModel();
	
	public void insertDetail(int ID, int productID, int quantity) {
		td.insert(ID, productID, quantity);
	}
	
	public int getTotalPrice(int TransactionID) {
		int totalPrice = td.getTotalPrice(TransactionID);
		return totalPrice;
	}
	
	public ArrayList<TransactionDetail> getTransactionDetail(int index){
		ArrayList<TransactionDetail> transactions = new ArrayList<TransactionDetail>();
		transactions = td.getTransactionDetail(index);
		return transactions;
	}
}
