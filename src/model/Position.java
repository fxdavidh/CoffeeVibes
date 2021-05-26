package model;

public class Position {
	private int id;
	private String name;
	
	public Position(int id, String name) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
