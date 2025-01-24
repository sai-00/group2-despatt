<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.keebcove.model.CartItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Checkout</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>KeebCove - Checkout</h1>

        <h2>Cart Items</h2>
        <%
            List<CartItem> cart = (List<CartItem>) request.getAttribute("cart");
            double totalPrice = (double) request.getAttribute("totalPrice");

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
                <% for (CartItem item : cart) { %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td>PHP <%= item.getPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>PHP <%= item.getPrice() * item.getQuantity() %></td>
                </tr>
                <% } %>
            </table>
            <p><strong>Grand Total: PHP <%= totalPrice %></strong></p>
        <%
            }
        %>

        <h2>Billing Information</h2>
        <form action="confirmation" method="post">
            <input type="hidden" name="totalPrice" value="<%= totalPrice %>">

            <label for="name">Name:</label><br>
            <input type="text" id="name" name="name" required><br><br>

            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" required><br><br>

            <label for="phone">Phone:</label><br>
            <input type="text" id="phone" name="phone" required><br><br>

            <label for="address">Address:</label><br>
            <textarea id="address" name="address" required></textarea><br><br>

            <button type="submit">Confirm Purchase</button>
        </form>
        
        <a href="cart.jsp">Back to Cart</a>
    </div>
</body>
</html>
