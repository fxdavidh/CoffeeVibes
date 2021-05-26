package controller;
import java.util.ArrayList;
import java.util.Vector;

import model.Employee;
import model.EmployeeModel;
import model.Position;
import model.PositionModel;

public class EmployeeDAO {
	EmployeeModel em = new EmployeeModel();
	PositionModel pm = new PositionModel();
	
	public Vector<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		Vector<Employee> employees = new Vector<Employee>();
		employees = em.getAllEmployee();
		return employees;
	}
	
	public void insertEmployee(String position, String name, String status, String salary, String username, String password) {
		// TODO Auto-generated method stub
		int tempPosition=0;
		Vector<Position> positions = new Vector<Position>();
		positions = pm.getAllPosition();
		for(int i=0; i<positions.size(); i++) {
			if (position.equalsIgnoreCase(positions.get(i).getName())) {
				tempPosition = positions.get(i).getId();
			}
		}
		int tempSalary = Integer.parseInt(salary);
		em.insertEmployee(tempPosition, name, status, tempSalary, username, password);
	}
	
	public void updateEmployee(String id, String position, String name, String status, String salary, String username, String password) {
		// TODO Auto-generated method stub
		int tempPosition=0;
		Vector<Position> positions = new Vector<Position>();
		positions = pm.getAllPosition();
		for(int i=0; i<positions.size(); i++) {
			if (position.equalsIgnoreCase(positions.get(i).getName())) {
				tempPosition = positions.get(i).getId();
			}
		}
		int tempId = Integer.parseInt(id);
		
		if(status.equalsIgnoreCase("active")) status = "A";
		else status = "F";
		
		int tempSalary = Integer.parseInt(salary);
		em.updateEmployee(tempId, tempPosition, name, status, tempSalary, username, password);
	}
	
	public void deleteEmployee(String id) {
		// TODO Auto-generated method stub
		int tempId = Integer.parseInt(id);
		em.deleteEmployee(tempId);
	}
	
	public boolean validateLogin(int positionID, String username, String password) {
		return em.validateLogin(positionID, username, password);
	}
}
