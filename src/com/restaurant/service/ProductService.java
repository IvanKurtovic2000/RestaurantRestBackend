package com.restaurant.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.restaurant.dao.ProductDAO;
import com.restaurant.dao.impl.ProductDAOImpl;
import com.restaurant.model.Product;

@Path("product")
public class ProductService {
	private Logger logger = Logger.getLogger(ProductService.class);

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTest() {
		return "Hallo das ist ein Test";
	}

	@Path("status")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getStatus() {
		logger.info("Inside getStatus()...");
		return "RestaurantRestBackend Status is OK...";
	}

	@GET
	@Path("getproduct")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@DefaultValue("1") @QueryParam("id") int id) {

		ProductDAO daoImpl = new ProductDAOImpl();
		logger.info("Inside getProduct...");

		Response resp = daoImpl.getProduct(id);
		return resp;
	}

	@POST
	@Path("addproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(Product product) {

		ProductDAO daoImpl = new ProductDAOImpl();
		logger.info("Inside createProduct...");

		Response resp = daoImpl.createProduct(product);
		return resp;
	}

	@PUT
	@Path("updateproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(Product product) {

		ProductDAO daoImpl = new ProductDAOImpl();
		logger.info("Inside createProduct...");

		Response resp = daoImpl.updateProduct(product);
		return resp;
	}

	@DELETE
	@Path("deleteproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@DefaultValue("0") @QueryParam("id") int id) {

		ProductDAO daoImpl = new ProductDAOImpl();
		logger.info("Inside deleteProduct...");

		Response resp = daoImpl.deleteProduct(id);
		return resp;
	}

	@GET
	@Path("showallproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllProducts() throws JsonGenerationException, JsonMappingException, IOException {

		ProductDAO daoImpl = new ProductDAOImpl();
		logger.info("Inside showAllProducts...");
		Response resp = daoImpl.getAllProducts();

		return resp;
	}

}
