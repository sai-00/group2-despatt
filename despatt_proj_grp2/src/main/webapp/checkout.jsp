<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <p><strong>Product Name:</strong> ${productName}</p>
        <p><strong>Quantity:</strong> ${quantity}</p>
        <p><strong>Total:</strong> PHP ${totalPrice}</p>

        <form action="confirmation" method="post">
            <input type="hidden" name="productName" value="${productName}">
            <input type="hidden" name="quantity" value="${quantity}">
            <input type="hidden" name="totalPrice" value="${totalPrice}">
            

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
        
        <a href="index.jsp">Back to Home</a>
    </div>
</body>
</html>
