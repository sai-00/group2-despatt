<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.keebcove.model.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

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
		<div class="logo"><a href="index.jsp"><img src="imgs/keeb_cove_logo_tr.png" width="120"></a></div>
	  	<div class="cart"><a href="cart.jsp"><img src="imgs/cart.png" width="40"></a></div>
	</div>
	
    <div class="container">
    
	    <% 
	        // Retrieve error message if checkout failed
	        String errorMessage = (String) session.getAttribute("errorMessage");
	        Boolean orderSuccess = (Boolean) session.getAttribute("orderSuccess");
	        if (errorMessage != null) { 
	    %>
	        <p style="color: red; font-weight: bold;"><%= errorMessage %></p>
	    <%
	        session.removeAttribute("errorMessage"); // Clear message after showing
	        }
	    %>

	    <% if (orderSuccess != null && orderSuccess) { %>  
	        <h1>Order Confirmation</h1>
	        <h2>Thank you for your purchase, <%= session.getAttribute("name") %>!</h2>

	        <h3>Order Summary</h3>

	    <%
		    // Retrieve the cart from session
		    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		
		    if (cart == null || cart.isEmpty()) {
		%>
		    <p style="color: red;">Your cart is empty or was not retrieved.</p>
		<%
		    } else {
		        // Retrieve total price from session
		        Double totalPriceObj = (Double) session.getAttribute("totalPrice");
		        double totalPrice = (totalPriceObj != null) ? totalPriceObj : 0.0;
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
	                <td><%= item.getProductName() %></td>
	                <td>PHP <%= item.getPrice() %></td>
	                <td><%= item.getQuantity() %></td>
	                <td>PHP <%= item.getPrice() * item.getQuantity() %></td>
	            </tr>
	            <% } %>
	        </table>
	        <p><strong>Grand Total: PHP <%= totalPrice %></strong></p>

	        <h3>Delivery Details</h3>
	        <p><strong>Name:</strong> <%= session.getAttribute("name") %></p>
			<p><strong>Email:</strong> <%= session.getAttribute("email") %></p>
			<p><strong>Phone:</strong> <%= session.getAttribute("phone") %></p>
			<p><strong>Address:</strong> <%= session.getAttribute("address") %></p>

	        <% 
	            // Clear cart from session after successful display
	            session.removeAttribute("cart");
	            session.removeAttribute("totalPrice");
	        %>

	        <% } %>

	    <% } else { %>
	        <p style="color: red; font-weight: bold;">Order failed. Please try again.</p>
	    <% } %>

        <a href="index.jsp">Back to Home</a>
    </div>
</body>
</html>
