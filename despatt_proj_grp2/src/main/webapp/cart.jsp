<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.keebcove.model.CartItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Cart</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/cart.css">
</head>
<body>
	<div class="topnav">
		<div class="logo"><a href="index.jsp"><img src="imgs/keeb_cove_logo_tr.png" width=120"></a></div>
	  	<div class="cart"><a href="cart.jsp"><img src="imgs/cart.png" width=40"></a></div>
	</div>
	
    <div class="container">
        <h1>Your Cart</h1>
        <%
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            double total = 0;

            if (cart == null || cart.isEmpty()) {
        %>
            <p>Your cart is empty.</p>
            <button><a href="keebcove">Continue Shopping</a></button>
        <%
            } else {
        %>
            <table>
			    <tr>
			        <th>Product</th>
			        <th>Price</th>
			        <th>Quantity</th>
			        <th>Total</th>
			        <th>Actions</th>
			    </tr>
			    <% for (CartItem item : cart) { 
			        double itemTotal = item.getPrice() * item.getQuantity();
			        total += itemTotal;
			    %>
			    <tr>
			        <td><%= item.getProductName() %></td>
			        <td>PHP <%= item.getPrice() %></td>
			        <td>
			            <form action="cart" method="post">
			                <input type="hidden" name="action" value="edit">
			                <input type="hidden" name="productId" value="<%= item.getProductId() %>">
			                <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1">
			                <button type="submit">Update</button>
			            </form>
			        </td>
			        <td>PHP <%= itemTotal %></td>
			        <td>
			            <form action="cart" method="post">
			                <input type="hidden" name="action" value="delete">
			                <input type="hidden" name="productId" value="<%= item.getProductId() %>">
			                <button type="submit">Remove</button>
			            </form>
			        </td>
			    </tr>
			    <% } %>
			</table>
            <p><strong>Grand Total: PHP <%= total %></strong></p>

            <form action="checkout" method="get">
                <input type="hidden" name="cartTotal" value="<%= total %>">
                <button type="submit">Proceed to Checkout</button>
                <br>
                or
                <br>
                <button><a href="keebcove">Continue Shopping</a></button>
            </form>
        <%
            }
        %>
    </div>
</body>
</html>
