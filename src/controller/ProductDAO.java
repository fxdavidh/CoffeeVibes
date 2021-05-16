package controller;

import java.util.Vector;

import model.Product;
import model.ProductModel;

public class ProductDAO {
	
	public Vector<Product> getAllProduct(){
		Vector<Product> products = new Vector<>();
		ProductModel pm = new ProductModel();
		products = pm.getAllProduct();
		return products;
	}
}
