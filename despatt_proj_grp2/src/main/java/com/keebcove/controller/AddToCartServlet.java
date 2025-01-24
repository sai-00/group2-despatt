package com.keebcove.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keebcove.model.CartItem;

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
		String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String quantityParam = request.getParameter("quantity");

        // Validate that the form data is being retrieved correctly
        if (productName == null || productPrice == null || quantityParam == null) {
            response.sendRedirect("product.jsp"); // Redirect if something is missing
            return;
        }

        int quantity = Integer.parseInt(quantityParam);
        double price = Double.parseDouble(productPrice);

        CartItem item = new CartItem(productName, price, quantity);

        List<CartItem> cart = (List<CartItem>) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        cart.add(item);

        request.getSession().setAttribute("cart", cart);

        response.sendRedirect("cart");
	}

}
