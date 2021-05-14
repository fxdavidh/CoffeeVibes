package model;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
