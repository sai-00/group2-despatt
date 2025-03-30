<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.keebcove.model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Product Details</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/product.css">
</head>
<body>
	<div class="topnav">
		<div class="logo">
			<a href="index.jsp"><img src="imgs/keeb_cove_logo_tr.png" width="120"></a>
		</div>
	  	<div class="cart">
			<a href="cart.jsp"><img src="imgs/cart.png" width="40"></a>
		</div>
	</div>
	
    <div class="container">
        <h1>Product Details</h1>

		<button><a href="index.jsp">Back to Home</a></button>

        <div class="product-info">
            <% 
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) { 
            %>
                <p style="color: red;"><%= errorMessage %></p>
            <% } else { 
                Product product = (Product) request.getAttribute("product");
                if (product != null) { 
            %>
            
                <h1><%= product.getName() %></h1>
                <p>Category: <%= product.getCategory() %></p>
                <p>Price: PHP <%= product.getPrice() %></p>
                <p>Available Quantity: <%= product.getQuantity() %></p>
                <p>Description: <%= product.getDescription() %></p>
            <% } else { %>
                <p>Product not found.</p>
            <% } } %>
        </div>

        <% if (request.getAttribute("product") != null) { %>
        <form action="addToCart" method="post">
        	<input type="hidden" name="product_id" value="<%= ((Product) request.getAttribute("product")).getId() %>">
            <input type="hidden" name="productName" value="<%= ((Product) request.getAttribute("product")).getName() %>">
            <input type="hidden" name="productPrice" value="<%= ((Product) request.getAttribute("product")).getPrice() %>">
            <input type="hidden" name="productQuantity" value="<%= ((Product) request.getAttribute("product")).getQuantity() %>">
            
            <label for="quantity">Quantity: </label>
            <input type="number" id="quantity" name="quantity" value="1" min="1" 
                   max="<%= ((Product) request.getAttribute("product")).getQuantity() %>" required class="quan-box">
            <button type="submit">Add to Cart</button>
        </form>
        <% } %>

    </div>
</body>
</html>
