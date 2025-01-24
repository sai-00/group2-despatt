<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.keebcove.model.CartItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Purchase Confirmation</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/confirmation.css">
</head>
<body>
	<div class="topnav">
		<div class="logo"><a href="index.jsp"><img src="imgs/keeb_cove_logo_tr.png" width=120"></a></div>
	  	<div class="cart"><a href="cart.jsp"><img src="imgs/cart.png" width=40"></a></div>
	</div>
	
    <div class="container">
        <h1>Order Confirmation</h1>

        <h2>Thank you for your purchase, <%= request.getAttribute("name") %>!</h2>

        <h3>Order Summary</h3>
        <%
            List<CartItem> cart = (List<CartItem>) request.getAttribute("cart");
            double totalPrice = (double) request.getAttribute("totalPrice");
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

        <h3>Delivery Details</h3>
        <p><strong>Name:</strong> <%= request.getAttribute("name") %></p>
        <p><strong>Email:</strong> <%= request.getAttribute("email") %></p>
        <p><strong>Phone:</strong> <%= request.getAttribute("phone") %></p>
        <p><strong>Address:</strong> <%= request.getAttribute("address") %></p>

        <a href="index.jsp">Back to Home</a>
    </div>
</body>
</html>
