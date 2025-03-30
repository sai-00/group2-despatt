package com.keebcove.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keebcove.model.CartItem;

/**
 * Servlet implementation class ConfirmationServlet
 */
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    
	    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
	    String name = (String) session.getAttribute("name");
	    String email = (String) session.getAttribute("email");
	    String phone = (String) session.getAttribute("phone");
	    String address = (String) session.getAttribute("address");
	    double totalPrice = session.getAttribute("totalPrice") != null ? (double) session.getAttribute("totalPrice") : 0.0;

	    // Pass attributes to JSP
	    request.setAttribute("cart", cart);
	    request.setAttribute("name", name);
	    request.setAttribute("email", email);
	    request.setAttribute("phone", phone);
	    request.setAttribute("address", address);
	    request.setAttribute("totalPrice", totalPrice);

	    // Forward to JSP
	    request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
	}

}
