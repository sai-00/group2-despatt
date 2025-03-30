package com.keebcove.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keebcove.facade.CartManagementFacade;
import com.keebcove.model.Product;
import com.keebcove.utility.ProductCache;

public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

        System.out.println("Received request to add item to cart...");

        String productIdStr = request.getParameter("product_id");
        String productName = request.getParameter("productName");
        String priceStr = request.getParameter("productPrice");
        String quantityStr = request.getParameter("quantity");

        if (productIdStr == null || productName == null || priceStr == null || quantityStr == null ||
            productIdStr.isEmpty() || productName.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            System.err.println("Error: Missing input values!");
            response.sendRedirect("cart.jsp");
            return;
        }

        int productId = Integer.parseInt(productIdStr);
        double price = Double.parseDouble(priceStr);
        int requestedQuantity = Integer.parseInt(quantityStr);

        System.out.println("Adding product to cart: " + productName + " (ID: " + productId + ")");

        Product product = ProductCache.getProduct(productId);
        if (product == null) {
            System.err.println("Error: Product not found!");
            request.setAttribute("errorMessage", "Product not found.");
            request.getRequestDispatcher("product.jsp").forward(request, response);
            return;
        }

        int availableQuantity = product.getQuantity();

        // Validate if the requested quantity is available
        if (requestedQuantity > availableQuantity) {
            System.err.println("Error: Not enough stock available!");
            request.setAttribute("errorMessage", "Only " + availableQuantity + " left in stock.");
            request.getRequestDispatcher("product.jsp").forward(request, response);
            return;
        }

        System.out.println("Adding product to cart: " + productName + " (ID: " + productId + ")");

        // Proceed with adding to cart
        CartManagementFacade cartFacade = new CartManagementFacade(session, productId, productName, price, requestedQuantity);
        cartFacade.process();

        response.sendRedirect("cart.jsp");
	}

}
