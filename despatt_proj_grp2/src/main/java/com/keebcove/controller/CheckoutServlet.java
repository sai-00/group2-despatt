package com.keebcove.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keebcove.facade.CheckoutProcessingFacade;
import com.keebcove.model.CartItem;

/**
 * Servlet implementation class CheckoutServlet
 */
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");

	    double totalPrice = 0.0;
	    if (cartItems != null) {
	        for (CartItem item : cartItems) {
	            totalPrice += item.getPrice() * item.getQuantity();
	        }
	    }

	    request.setAttribute("cart", cartItems);
	    request.setAttribute("totalPrice", totalPrice);
	    session.setAttribute("totalPrice", totalPrice);  

	    request.getRequestDispatcher("checkout.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

	    String customerName = request.getParameter("name");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String address = request.getParameter("address");
	    String cardNumber = request.getParameter("card_number");

	    @SuppressWarnings("unchecked")
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
	    double totalPrice = (session.getAttribute("totalPrice") != null) ? (double) session.getAttribute("totalPrice") : 0.0;

	    if (cartItems == null || cartItems.isEmpty()) {
	        session.setAttribute("errorMessage", "Your cart is empty.");
	        response.sendRedirect("checkout.jsp");
	        return;
	    }

	    CheckoutProcessingFacade checkoutFacade = new CheckoutProcessingFacade(cartItems, cardNumber, customerName, email, phone, address);
	    checkoutFacade.process();

	    System.out.println("Checkout Status - Order Successful: " + checkoutFacade.isOrderSuccessful());

	    if (checkoutFacade.isOrderSuccessful()) {
	        session.setAttribute("orderSuccess", true);
	        session.setAttribute("name", customerName);
	        session.setAttribute("email", email);
	        session.setAttribute("phone", phone);
	        session.setAttribute("address", address);
	        session.setAttribute("cart", cartItems);  
	        session.setAttribute("totalPrice", totalPrice); 

	        session.removeAttribute("errorMessage"); 
	        System.out.println("Redirecting to confirmation.jsp");
	        response.sendRedirect("confirmation.jsp");
	    } else {
	        session.setAttribute("orderSuccess", false);
	        session.setAttribute("errorMessage", "Payment failed. Please check your card details.");
	        response.sendRedirect("checkout.jsp");
	    }
	}

}
