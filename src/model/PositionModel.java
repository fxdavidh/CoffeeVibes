package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connection.Connect;

public class PositionModel {
	private Connect con = new Connect();
	
	public Vector<Position> getAllPosition() {
		// TODO Auto-generated method stub
		Vector<Position> positions = new Vector<>();
		String query = "SELECT * FROM `positions`";
		
		ResultSet result = con.executeQuery(query);
		Integer ID;
		String Name;
		try {
			while(result.next()) {
				ID = result.getInt("id");
				Name = result.getString("name");
				
				positions.add(new Position(ID,Name));
			}
			return positions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertPosition(String name) {
		// TODO Auto-generated method stub
		try {
			String query = "INSERT INTO positions VALUES ('"+getLatestId()+"', "
					+ "'"+name+"')";
			con.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getLatestId() {
		int id = 0;
		try {
			String query = "select MAX(id) from positions";
			ResultSet result = con.executeQuery(query);
			while (result.next()) {
				id = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id+1;
	}
	
	public int getPositionID(String name) {
		String query = "SELECT * FROM `positions` WHERE name like '" + name + "'";
		ResultSet result = con.executeQuery(query);
		try {
			if(result.next()) {
				int ID = result.getInt("id");
				return ID;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
