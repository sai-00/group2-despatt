package com.keebcove.facade;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.keebcove.model.CartItem;
import com.keebcove.utility.Facade;

public class CartManagementFacade implements Facade {
    private HttpSession session;
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private List<CartItem> cart;

    public CartManagementFacade(HttpSession session, int productId, String productName, double price, int quantity) {
        this.session = session;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    @SuppressWarnings("unchecked")
    private void retrieveCart() {
        System.out.println("Retrieving cart from session...");

        cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            System.out.println("Cart is null. Creating a new cart.");
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        } else {
            System.out.println("Cart retrieved successfully. Current size: " + cart.size());
        }
    }

    private void addItemToCart() {
        System.out.println("Adding item to cart: " + productName);

        for (CartItem item : cart) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Updated quantity for " + productName + ": " + item.getQuantity());
                session.setAttribute("cart", cart);
                return;
            }
        }

        cart.add(new CartItem(productId, productName, price, quantity));
        System.out.println("New item added to cart: " + productName);
        session.setAttribute("cart", cart);
    }

    @Override
    public void process() {
        retrieveCart();
        addItemToCart();
        System.out.println("Cart size after addition: " + cart.size());
    }
}
