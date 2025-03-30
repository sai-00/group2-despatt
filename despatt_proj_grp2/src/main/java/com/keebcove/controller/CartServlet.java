package com.keebcove.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keebcove.model.CartItem;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
	    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

	    if (cart == null) {
	        cart = new ArrayList<>();
	        session.setAttribute("cart", cart);
	    }

	    String action = request.getParameter("action");

	    if ("edit".equals(action)) {
	        int productId = Integer.parseInt(request.getParameter("productId"));
	        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

	        for (CartItem item : cart) {
	            if (item.getProductId() == productId) {
	                item.setQuantity(newQuantity);
	                break;
	            }
	        }
	    } else if ("delete".equals(action)) {
	        int productId = Integer.parseInt(request.getParameter("productId"));

	        cart.removeIf(item -> item.getProductId() == productId);
	    }

	    session.setAttribute("cart", cart);
	    response.sendRedirect("cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
