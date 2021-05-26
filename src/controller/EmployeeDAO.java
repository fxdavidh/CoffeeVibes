package controller;

import java.util.Vector;

import model.Employee;
import model.EmployeeModel;

public class EmployeeDAO {
	EmployeeModel em = new EmployeeModel();
	
	public Vector<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		Vector<Employee> employees = new Vector<Employee>();
		employees = em.getAllEmployee();
		return employees;
	}
	
	public void insertEmployee(String positionId, String name, String status, String salary, String username, String password) {
		// TODO Auto-generated method stub
		int tempPosition = Integer.parseInt(positionId);
		int tempSalary = Integer.parseInt(salary);
		em.insertEmployee(tempPosition, name, status, tempSalary, username, password);
	}
	
	public void updateEmployee(String id, String positionId, String name, String status, String salary, String username, String password) {
		// TODO Auto-generated method stub
		int tempId = Integer.parseInt(id);
		int tempPosition = Integer.parseInt(positionId);
		int tempSalary = Integer.parseInt(salary);
		em.updateEmployee(tempId, tempPosition, name, status, tempSalary, username, password);
	}
	
	public void deleteEmployee(String id) {
		// TODO Auto-generated method stub
		int tempId = Integer.parseInt(id);
		em.deleteEmployee(tempId);
	}
}
