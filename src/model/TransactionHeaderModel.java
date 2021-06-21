package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connection.Connect;

public class TransactionHeaderModel {
	
	private Connect con = new Connect();
	
	public int getNextAI() {
		int index = -1;
		String query = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'ooad' AND TABLE_NAME   = 'transaction_header'";
		ResultSet result = con.executeQuery(query);
		
		try {
			if(result.next()) {
				
				index = result.getInt("AUTO_INCREMENT");
				
				return index;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void insert(int voucherID) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String query = "INSERT INTO `transaction_header`(`purchaseDate`, `voucherId`) VALUES('"+ dtf.format(now)+"','"+voucherID +"')";
		con.executeUpdate(query);
	}
	
	public ArrayList<TransactionHeader> getTransactionHeaders() {
		String query = "SELECT * FROM `transaction_header`";
		ArrayList<TransactionHeader> transactions = new ArrayList<>();
		ResultSet result = con.executeQuery(query);
		int id,voucherID;
		Date date;
		try {
			while(result.next()) {
				id = result.getInt("id");
				date = result.getDate("purchaseDate");
				voucherID = result.getInt("voucherId");
				transactions.add(new TransactionHeader(id,date,voucherID));
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
