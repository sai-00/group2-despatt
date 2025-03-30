package com.keebcove.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keebcove.utility.DatabaseConnection;
import com.keebcove.utility.ProductCache;
import com.keebcove.facade.ProductRetrievalFacade;
import com.keebcove.model.Product;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        DatabaseConnection.initialize(
            getServletContext().getInitParameter("jdbcDriver"),
            getServletContext().getInitParameter("jdbcUrl"),
            getServletContext().getInitParameter("dbUserName"),
            getServletContext().getInitParameter("dbPassword")
        );
    }


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String productIdParam = request.getParameter("id");
        if (productIdParam == null || productIdParam.isEmpty()) {
            request.setAttribute("errorMessage", "Invalid product ID.");
            request.getRequestDispatcher("product.jsp").forward(request, response);
            return;
        }

        try {
            int productId = Integer.parseInt(productIdParam);

            Product product = ProductCache.getProduct(productId);

            if (product == null) {
                request.setAttribute("errorMessage", "Product not found.");
            } else {
                request.setAttribute("product", product);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid product ID format.");
        }

        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
