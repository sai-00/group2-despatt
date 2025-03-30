<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.keebcove.model.CartItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Checkout</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/checkout.css">
</head>
<body>
	<div class="topnav">
		<div class="logo"><a href="index.jsp"><img src="imgs/keeb_cove_logo_tr.png" width="120"></a></div>
	  	<div class="cart"><a href="cart.jsp"><img src="imgs/cart.png" width="40"></a></div>
	</div>
	
    <div class="container">
        <h1>Checkout</h1>

        <h2>Cart Items</h2>
        <%
            List<CartItem> cart = (List<CartItem>) request.getAttribute("cart");
            Double totalPriceObj = (Double) request.getAttribute("totalPrice"); 
            double totalPrice = (totalPriceObj != null) ? totalPriceObj : 0.0;
        %>

        <% if (cart == null || cart.isEmpty()) { %>
            <p>Your cart is empty.</p>
        <% } else { %>
            <table>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                <% for (CartItem item : cart) { %>
                <tr>
                    <td><%= item.getProductName() %></td>
                    <td>PHP <%= item.getPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>PHP <%= item.getPrice() * item.getQuantity() %></td>
                </tr>
                <% } %>
            </table>
            <p><strong>Grand Total: PHP <%= totalPrice %></strong></p>
        <% } %>

        <h2>Billing Information</h2>
        <form action="checkout" method="post">
            <input type="hidden" name="totalPrice" value="<%= totalPrice %>">

            <label for="name">Name:</label><br>
            <input type="text" id="name" name="name" required><br><br>

            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" required><br><br>

            <label for="phone">Phone:</label><br>
            <input type="text" id="phone" name="phone" required><br><br>

            <label for="address">Address:</label><br>
            <textarea id="address" name="address" required></textarea><br><br>

            <label for="card_number">Card Number:</label><br>
            <input type="text" id="card_number" name="card_number" required><br><br>

            <button type="submit">Confirm Purchase</button>
        </form>
        
        <a href="cart.jsp">Back to Cart</a>
    </div>
</body>
</html>
