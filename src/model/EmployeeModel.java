package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connection.Connect;

public class EmployeeModel {
	private Connect con = new Connect();

	public Vector<Employee> getAllEmployee() {
		Vector<Employee> employees = new Vector<>();
		String query = "SELECT * FROM `employees`";
		
		ResultSet result = con.executeQuery(query);
		Integer ID, PositionId, Salary;
		String Name, Status, Username, Password;
		try {
			while(result.next()) {
				ID = result.getInt("id");
				PositionId = result.getInt("positionId");
				Name = result.getString("name");
				Status = result.getString("status");
				Salary = result.getInt("salary");
				Username = result.getString("username");
				Password = result.getString("password");
				
				employees.add(new Employee(ID, PositionId, Name, Status, Salary, Username, Password));
			}
			return employees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertEmployee(int positionId, String name, String status, int salary, String username, String password) {
		// TODO Auto-generated method stub
		try {
			String query = "INSERT INTO employees VALUES ('"+getLatestId()+"', "
					+ "'"+positionId+"', "
					+ "'"+name+"', "
					+ "'"+status+"', "
					+ "'"+salary+"', "
					+ "'"+username+"', "
					+ "'"+password+"')";
			con.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmployee(int id, int positionId, String name, String status, int salary, String username, String password) {
		// TODO Auto-generated method stub
		try {
			String query = "UPDATE employees "
					+ "SET positionId='"+positionId+"', "
					+ "name='"+name+"', "
					+ "status='"+status+"', "
					+ "salary='"+salary+"', "
					+ "username='"+username+"', "
					+ "password='"+password+"'"
					+ "WHERE id='"+id+"'";
			con.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		try {
			String query = "DELETE FROM employees WHERE id='"+id+"'";
			con.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int getLatestId() {
		int id = 0;
		try {
			String query = "select MAX(id) from employees";
			ResultSet result = con.executeQuery(query);
			while (result.next()) {
				id = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id+1;
	}
	
	public boolean validateLogin(int positionID, String username, String password) {
		String query = "SELECT * from `employees` WHERE positionId = " + positionID + " AND username like '" + username +"' AND password like '" + password +"'";
		ResultSet result = con.executeQuery(query);
		try {
			if(result.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
