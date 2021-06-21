package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connection.Connect;

public class TransactionDetailModel {
	private Connect con = new Connect();
	
	public void insert(int ID,int productID,int quantity) {;  
		String query = "INSERT INTO `transaction_detail` VALUES('"+ ID +"','"+ productID+"','"+quantity+"')";
		con.executeUpdate(query);
	}
	
	public int getTotalPrice(int TransactionID) {
		String query = "SELECT SUM(pr.price*td.qty) AS total_price FROM `transaction_detail` td JOIN `products` pr WHERE td.productId = pr.id AND td.id like '"+ TransactionID +"'";
		ResultSet result = con.executeQuery(query);
		try {
			if(result.next()) {
				int totalPrice = result.getInt("total_price");
				return totalPrice;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<TransactionDetail> getTransactionDetail(int index){
		ArrayList<TransactionDetail> transactions = new ArrayList<TransactionDetail>();
		String query = "SELECT * FROM `transaction_detail` WHERE id =" + index;
		ResultSet result = con.executeQuery(query);
		int ID,ProductID,quantity;
		try {
			while(result.next()) {
				ID = result.getInt("id");
				ProductID = result.getInt("productId");
				quantity = result.getInt("qty");
				transactions.add(new TransactionDetail(ID,ProductID,quantity));
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
