<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.keebcove.model.CartItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Cart</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Your Cart</h1>
        <%
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            double total = 0;

            if (cart == null || cart.isEmpty()) {
        %>
            <p>Your cart is empty.</p>
        <%
            } else {
        %>
            <table>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                <% for (CartItem item : cart) { 
                    double itemTotal = item.getPrice() * item.getQuantity();
                    total += itemTotal;
                %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td>PHP <%= item.getPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>PHP <%= itemTotal %></td>
                </tr>
                <% } %>
            </table>
            <p><strong>Grand Total: PHP <%= total %></strong></p>

            <form action="checkout" method="post">
                <input type="hidden" name="cartTotal" value="<%= total %>">
                <button type="submit">Proceed to Checkout</button>
            </form>
        <%
            }
        %>
        <a href="keebcove">Continue Shopping</a>
    </div>
</body>
</html>
