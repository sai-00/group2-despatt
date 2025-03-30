package com.keebcove.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.keebcove.model.CartItem;
import com.keebcove.utility.Facade;

public class ConfirmationFacade implements Facade {
	private HttpServletRequest request;

    public ConfirmationFacade(HttpServletRequest request) {
        this.request = request;
    }
    
    private void prepareConfirmationDetails() {
        List<CartItem> cart = (List<CartItem>) request.getSession().getAttribute("cart");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

        request.getSession().removeAttribute("cart");

        request.setAttribute("cart", cart);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("totalPrice", totalPrice);
    }

	@Override
	public void process() {
		this.prepareConfirmationDetails();
	}

}
