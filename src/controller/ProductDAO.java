package controller;

import java.util.Vector;

import model.Product;
import model.ProductModel;

public class ProductDAO {
	ProductModel pm = new ProductModel();
	
	public Vector<Product> getAllProduct(){
		Vector<Product> products = new Vector<>();
		products = pm.getAllProduct();
		return products;
	}
	
	public void insertProduct(String name, String desc, String price, String stock) {
		int tempPrice = Integer.parseInt(price);
		int tempStock = Integer.parseInt(stock);
		pm.insertProduct(name, desc, tempPrice, tempStock);
	}
	
	public void updateProduct(String id, String name, String desc, String price, String stock) {
		int tempId = Integer.parseInt(id);
		int tempPrice = Integer.parseInt(price);
		int tempStock = Integer.parseInt(stock);
		pm.updateProduct(tempId, name, desc, tempPrice, tempStock);
	}
	
	public void deleteProduct(String id) {
		int tempId = Integer.parseInt(id);
		pm.deleteProduct(tempId);
	}
}
