package com.restaurant.dao;

import javax.ws.rs.core.Response;

import com.restaurant.model.Product;

public interface ProductDAO {
	
	public Response getProduct(int id);
	public Response createProduct(Product customer);
	public Response updateProduct(Product customer);
	public Response deleteProduct(int id);
	public Response getAllProducts();
	
}
