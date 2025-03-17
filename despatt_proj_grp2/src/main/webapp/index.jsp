<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Home</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
	<div class="topnav">
		<div class="logo"><a href="index.jsp"><img src="imgs/keeb_cove_logo_tr.png" width="120"></a></div>
	  	<div class="cart"><a href="cart.jsp"><img src="imgs/cart.png" width="40"></a></div>
	</div>
	
    <div class="container">
        <h1>Welcome to Keeb Cove !</h1>
        <h2>Products</h2>
        
        <h3>MX Keyboard Parts</h3>
        <div class="product-parent">
	        <div class="product">
	            <img src="imgs/akkofairy.png" alt="Akko Fairy Silent" width="250" class="product-img">
	            <form action="product" method="get">
	                <input type="hidden" name="id" value="4">
	                <button type="submit">View Product</button>
	            </form>
	        </div>
	        <div class="product">
	            <img src="imgs/akkokeycaps.jpg" alt="Akko Keycaps" width="250" class="product-img">
	            <form action="product" method="get">
	                <input type="hidden" name="id" value="5">
	                <button type="submit">View Product</button>
	            </form>
	        </div>
	        <div class="product">
	            <img src="imgs/akkotop40.png" alt="Akko Top 40" width="250" class="product-img">
	            <form action="product" method="get">
	                <input type="hidden" name="id" value="6">
	                <button type="submit">View Product</button>
	            </form>
	        </div>
        </div>
        
        <hr>
        
        <h3>Choc Keyboard Parts</h3>
        <div class="product-parent">
	        <div class="product">
	            <img src="imgs/chocambientswitch.png" alt="Ambient Twilight Choc switches" width="250" class="product-img">
	            <form action="product" method="get">
	                <input type="hidden" name="id" value="1">
	                <button type="submit">View Product</button>
	            </form>
	        </div>
	        <div class="product">
	            <img src="imgs/choccorne.png" alt="Choc Corne Barebones" width="250" class="product-img">
	            <form action="product" method="get">
	                <input type="hidden" name="id" value="2">
	                <button type="submit">View Product</button>
	            </form>
	        </div>
	        <div class="product">
	            <img src="imgs/choccaps.png" alt="Choc keycaps" width="250" class="product-img">
	            <form action="product" method="get">
	                <input type="hidden" name="id" value="3">
	                <button type="submit">View Product</button>
	            </form>
	        </div>
        </div>
    </div>
</body>
</html>