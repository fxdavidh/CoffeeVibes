package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import connection.Connect;

public class ProductModel {
	private Connect con = new Connect();
	
	public Vector<Product> getAllProduct(){
		Vector<Product> products = new Vector<>();
		String query = "SELECT * FROM `products`";
		
		ResultSet result = con.executeQuery(query);
		Integer ID,Price,Stock;
		String Name,Description;
		try {
			while(result.next()) {
				ID = result.getInt("id");
				Name = result.getString("name");
				Description = result.getString("description");
				Price = result.getInt("price");
				Stock = result.getInt("stock");
				
				products.add(new Product(ID,Name,Description,Price,Stock));
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertProduct(String name, String description, int price, int stock) {
		// TODO Auto-generated method stub
		try {
			String query = "INSERT INTO products VALUES ('"+getLatestId()+"', "
					+ "'"+name+"', "
					+ "'"+description+"', "
					+ "'"+price+"', "
					+ "'"+stock+"')";
			con.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateProduct(int id, String name, String description, int price, int stock) {
		// TODO Auto-generated method stub
		try {
			String query = "UPDATE products "
					+ "SET name='"+name+"',"
					+ "description='"+description+"',"
					+ "price='"+price+"',"
					+ "stock='"+stock+"'"
					+ "WHERE id='"+id+"'";
			con.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		try {
			String query = "DELETE FROM products WHERE id='"+id+"'";
			con.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int getLatestId() {
		int id = 0;
		try {
			String query = "select MAX(id) from products";
			ResultSet result = con.executeQuery(query);
			while (result.next()) {
				id = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id+1;
	}
	
	public Product getProduct(int index) {
		String query = "SELECT * FROM `products` WHERE id =" + index;
		ResultSet result = con.executeQuery(query);
		int ID,price,stock;
		String name,desc;
		try {
			if(result.next()) {
				ID = result.getInt("id");
				name = result.getString("name");
				desc = result.getString("description");
				price = result.getInt("price");
				stock = result.getInt("stock");
				Product product = new Product(ID,name,desc,price,stock);
				return product;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void reduceStock(int index,int quantity) {
		Product product = this.getProduct(index);
		String query = "UPDATE `products` SET `stock`='"+ (product.getStock() - quantity) +"' WHERE id = " + index;
		con.executeUpdate(query);
	}
}
