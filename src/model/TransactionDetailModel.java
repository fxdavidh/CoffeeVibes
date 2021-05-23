package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import connection.Connect;

public class TransactionDetailModel {
	private Connect con = new Connect();
	
	public void insert(int ID,int productID,int quantity) {;  
		String query = "INSERT INTO `transaction_detail` VALUES('"+ ID +"','"+ productID+"','"+quantity+"')";
		con.executeUpdate(query);
	}
}
