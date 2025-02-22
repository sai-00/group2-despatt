package com.keebcove.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keebcove.utility.AbstractFactory;
import com.keebcove.utility.ChocFactory;
import com.keebcove.utility.KbPartFactory;
import com.keebcove.utility.SingletonDB;
import com.keebcove.model.ChocParts;
import com.keebcove.model.KbPart;
import com.keebcove.model.Product;
/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	public void init() throws ServletException {
	    SingletonDB.initialize(getServletContext()); // Load DB config from web.xml
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("product"); 
	    
	    if (productName == null || productName.trim().isEmpty()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product name is required.");
	        return;
	    }

	    Connection conn = SingletonDB.getConnection(); 

	    if (conn == null) {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database connection is unavailable.");
	        return;
	    }

	    Product product = null;
	    String sql = "SELECT category, name, price, description FROM keebproducts WHERE name = ?";

	    try (PreparedStatement prep = conn.prepareStatement(sql)) {
	        prep.setString(1, productName);
	        ResultSet rs = prep.executeQuery();

	        if (rs.next()) {
	            String category = rs.getString("category");
	            String name = rs.getString("name");
	            double price = rs.getDouble("price");
	            String description = rs.getString("description");

	            AbstractFactory factory = category.equalsIgnoreCase("Choc") ? new ChocFactory() : new KbPartFactory();
	            product = factory.getProduct(name);

	        }
	    } catch (SQLException e) {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching product.");
	        return;
	    }

	    if (product == null) {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found.");
	        return;
	    }

	    request.setAttribute("product", product);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
	    dispatcher.forward(request, response);
    }
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
