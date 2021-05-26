package model;

public class Employee {
	private int id;
	private int positionId;
	private String name;
	private String status;
	private int salary;
	private String username;
	private String password;
	
	public Employee(int id, int positionId, String name, String status, int salary, String username, String password) {
		super();
		this.id = id;
		this.positionId = positionId;
		this.name = name;
		this.status = status;
		this.salary = salary;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public int getPositionId() {
		return positionId;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public int getSalary() {
		return salary;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
