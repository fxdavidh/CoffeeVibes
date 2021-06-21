package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import connection.Connect;

public class VoucherModel {
	private Connect con = new Connect();
	
	public Vector<Voucher> getAllVoucher() {
		Vector<Voucher> vouchers = new Vector<>();
		String query = "SELECT * FROM `vouchers`";
		
		ResultSet result = con.executeQuery(query);
		Integer Discount, ID;
		String Status;
		try {
			while(result.next()) {
				ID = result.getInt("id");
				Discount = result.getInt("Discount");
				Status = result.getString("status");
				
				if(Status.equals("A")) Status = "Active";
				else Status = "Non-Active";
				
				vouchers.add(new Voucher(ID, Discount, Status));
			}
			return vouchers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertVoucher(int discount, String status) {
		// TODO Auto-generated method stub
		try {
			String query = "INSERT INTO vouchers VALUES ('"+getLatestId()+"', "
					+ "'"+discount+"', "
					+ "'"+status+"')";
			con.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteVoucher(int id) {
		// TODO Auto-generated method stub
		try {
			String query = "DELETE FROM vouchers WHERE id='"+id+"'";
			con.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
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
	
	public int getLatestId() {
		int index = -1;
		String query = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'ooad' AND TABLE_NAME   = 'vouchers'";
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
}
