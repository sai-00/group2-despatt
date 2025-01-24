<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KeebCove - Product Details</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Product Details</h1>

        <p><strong>Product Name:</strong> ${product.name}</p>
        <p><strong>Price:</strong> PHP ${product.price}</p>
        <p><strong>Description:</strong> ${product.desc}</p>

        <form action="checkout" method="post">
            <input type="hidden" name="productName" value="${product.name}">
            <input type="hidden" name="productPrice" value="${product.price}">
            <label for="quantity">Quantity: </label>
            <input type="number" id="quantity" name="quantity" value="1" min="1" required>
            <button type="submit">Add to Cart</button>
        </form>

        <a href="keebcove">Back to Home</a>
    </div>
</body>
</html>
