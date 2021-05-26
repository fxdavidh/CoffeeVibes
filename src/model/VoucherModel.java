package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import connection.Connect;

public class VoucherModel {
	private Connect con = new Connect();
	
	public Voucher getVoucher(int ID){
		String query = "SELECT * FROM `vouchers` WHERE id like '" + ID + "'";
		ResultSet result = con.executeQuery(query);
		int discount;
		String status;
		try {
			if(result.next()) {
					discount = result.getInt("discount");
					status = result.getString("status");
					Voucher voucher = new Voucher(ID,discount,status);
					return voucher;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null; 
	}
	
	public void useVoucher(int ID) {
		String query = "UPDATE `vouchers` SET `status`='N' WHERE id=" + ID;
		con.executeUpdate(query);
	}
	
}
