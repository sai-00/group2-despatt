<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Home</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Welcome to Keeb Cove</h1>
        <a href="cart.jsp">View Cart</a>
        <div class="product">
            <img src="akko_fairy.jpg" alt="Akko Fairy Silent" width="200">
            <form action="product" method="get">
                <input type="hidden" name="product" value="Akko Fairy Silent">
                <button type="submit">View Product</button>
            </form>
        </div>
    </div>
</body>
</html>