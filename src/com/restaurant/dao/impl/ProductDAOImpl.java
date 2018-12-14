package com.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.restaurant.dao.ProductDAO;
import com.restaurant.model.Product;
import com.restaurant.model.StatusMessage;
import com.restaurant.util.Database;


public class ProductDAOImpl implements ProductDAO {
	private DataSource datasource = Database.getDataSource();
	private Logger logger = Logger.getLogger(ProductDAOImpl.class);
	
	@Override
	public Response getProduct(int id) {
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
		
    Product product = null;
		String sql = "select id, name, description, price, category, creationdate from PRODUCT where id = ?";
		
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
      rs = ps.executeQuery();
      
      if (rs.next()) {
      	product = new Product();
      	
      	product.setId(rs.getInt("id"));
      	product.setName(rs.getString("name"));
      	product.setDescription(rs.getString("description"));
      	product.setPrice(rs.getLong("price"));
      	product.setCategory(rs.getString("category"));
      	product.setCreationdate(rs.getTimestamp("creationdate"));
      	
      } else {
  			logger.error(String.format("Product with ID of %d is not found.", id));
  			StatusMessage statusMessage = new StatusMessage();
  			statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
  			statusMessage.setMessage(String.format("Product with ID of %d is not found.", id));
  			return Response.status(404).entity(statusMessage).build();
      }
		} catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error("Error closing resultset: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error("Error closing PreparedStatement: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Error closing connection: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return Response.status(200).entity(product).build();
	}

	@Override
	public Response createProduct(Product product) {
		Connection conn = null;
    PreparedStatement ps = null;
    Statement stmt = null;
    ResultSet rs = null;
    StatusMessage statusMessage = null;
    int autoID = -1;
		
		String sql = "insert into PRODUCT (name, description, price, category, creationdate) values (?,?,?,?,?)";
		
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setLong(3, product.getPrice());
			ps.setString(4, product.getCategory());
			ps.setTimestamp(5, product.getCreationdate());
			
			
			
      int rows = ps.executeUpdate();
      
      if (rows == 0) {
  			logger.error("Unable to create product...");
  			statusMessage = new StatusMessage();
  			statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
  			statusMessage.setMessage("Unable to create product...");
  			return Response.status(404).entity(statusMessage).build();
      }
      
      stmt = conn.createStatement();
      rs = stmt.executeQuery("select LAST_INSERT_ID()");

      if (rs.next()) {
          autoID = rs.getInt(1);
          product.setId(autoID);
      }
       
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error("Error closing resultset: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error("Error closing PreparedStatement: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Error closing connection: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return Response.status(200).entity(product).build();
	}

	@Override
	public Response updateProduct(Product product) {
		Connection conn = null;
    PreparedStatement ps = null;
		
		String sql = "update PRODUCT set name=?, description=?, "
				+ "price=?, category=?, creationdate=?"
				+ "where id = ?";
		
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setLong(3, product.getPrice());
			ps.setString(4, product.getCategory());
			ps.setTimestamp(5, product.getCreationdate());
			ps.setInt(5, product.getId());
			
      int rows = ps.executeUpdate();
      
      if (rows == 0) {
  			logger.error("Unable to update product...");
  			StatusMessage statusMessage = new StatusMessage();
  			statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
  			statusMessage.setMessage("Unable to update product...");
  			return Response.status(404).entity(statusMessage).build();
      }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error("Error closing PreparedStatement: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Error closing connection: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return Response.status(200).entity(product).build();
	}

	@Override
	public Response deleteProduct(int id) {
		Connection conn = null;
    PreparedStatement ps = null;
    StatusMessage statusMessage = null;
		
		String sql = "delete from PRODUCT where id = ?";
		
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
      int rows = ps.executeUpdate();
      
      if (rows == 0) {
  			logger.error(String.format("Unable to DELETE product with ID of %d...", id));
  			statusMessage = new StatusMessage();
  			statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
  			statusMessage.setMessage(String.format("Unable to DELETE product with ID of %d...", id));
  			return Response.status(404).entity(statusMessage).build();
      }
		} catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error("Error closing PreparedStatement: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Error closing connection: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		statusMessage = new StatusMessage();
		statusMessage.setStatus(Status.OK.getStatusCode());
		statusMessage.setMessage(String.format("Successfully deleted product with ID of %d...", id));
		return Response.status(200).entity(statusMessage).build();
	}

	@Override
	public Response getAllProducts() {
		Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
		List<Product> allProducts = new ArrayList<Product>();
		String sql = "select id, name, description, price, category, creationdate from PRODUCT";
		
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      
      while (rs.next()) {
      	Product product = new Product();
      	product.setId(rs.getInt("id"));
      	product.setName(rs.getString("name"));
      	product.setDescription(rs.getString("description"));
      	product.setPrice(rs.getLong("price"));
      	product.setCategory(rs.getString("category"));
      	product.setCreationdate(rs.getTimestamp("creationdate"));
      	allProducts.add(product);
      }
      
      if (allProducts.isEmpty()) {
      	logger.error("No Products Exists...");
  			StatusMessage statusMessage = new StatusMessage();
  			statusMessage.setStatus(Status.NOT_FOUND.getStatusCode());
  			statusMessage.setMessage("No Products Exists...");
  			return Response.status(404).entity(statusMessage).build();
      }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error("Error closing resultset: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error("Error closing PreparedStatement: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Error closing connection: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return Response.status(200).entity(allProducts).build();
	}
}
