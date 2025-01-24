<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
		<div class="logo"><a href="index.jsp"><img src="imgs/keeb_cove_logo_tr.png" width=120"></a></div>
	  	<div class="cart"><a href="cart.jsp"><img src="imgs/cart.png" width=40"></a></div>
	</div>
	
    <div class="container">
        <h1>Product Details</h1>
		
		<button><a href="keebcove">Back to Home</a></button>
		
		<br><br>
		<div class="product-img"><img src="imgs/akkofairy.png" width="250"></div>
		
        <div class="product-info">
        	<p><strong>Product Name:</strong> ${product.name}</p>
	        <p><strong>Price:</strong> PHP ${product.price}</p>
	        <p><strong>Description:</strong> ${product.desc}</p>
        </div>

        <form action="addToCart" method="post">
            <input type="hidden" name="productName" value="${product.name}">
            <input type="hidden" name="productPrice" value="${product.price}">
            <label for="quantity">Quantity: </label>
            <input type="number" id="quantity" name="quantity" value="1" min="1" required class="quan-box">
            <button type="submit">Add to Cart</button>
        </form>

    </div>
</body>
</html>
