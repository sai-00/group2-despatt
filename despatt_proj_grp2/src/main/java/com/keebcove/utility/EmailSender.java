package com.keebcove.utility;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import com.keebcove.model.CartItem;

public class EmailSender {
    private static final String fromEmail = "keebcove@gmail.com"; 
    private static final String password = "mlun ykca ggge xeuy"; 

    public static void sendOrderConfirmation(String recipientEmail, String customerName, List<CartItem> cartItems, double totalPrice, String address) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Authenticator auth = new Authenticator()
        {
        	protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
        	}
        };

        Session session = Session.getDefaultInstance(properties, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Order Confirmation - KeebCove");

            StringBuilder orderSummary = new StringBuilder();
            orderSummary.append("<table border='1' cellpadding='5' cellspacing='0' style='border-collapse: collapse;'>")
                        .append("<tr><th>Product</th><th>Quantity</th><th>Price</th><th>Total</th></tr>");

            for (CartItem item : cartItems) {
                orderSummary.append("<tr>")
                            .append("<td>").append(item.getProductName()).append("</td>")
                            .append("<td>").append(item.getQuantity()).append("</td>")
                            .append("<td>PHP ").append(item.getPrice()).append("</td>")
                            .append("<td>PHP ").append(item.getPrice() * item.getQuantity()).append("</td>")
                            .append("</tr>");
            }
            orderSummary.append("</table>");

            String emailContent = "<h1>Thank you for your order, " + customerName + "!</h1>"
                    + "<p>Your order has been successfully placed.</p>"
                    + "<h3>Order Summary:</h3>"
                    + orderSummary.toString()
                    + "<p><strong>Grand Total:</strong> PHP " + totalPrice + "</p>"
                    + "<h3>Shipping Address:</h3>"
                    + "<p>" + address + "</p>"
                    + "<p>We will notify you once your order is shipped.</p>"
                    + "<p><strong>KeebCove Team</strong></p>";

            message.setContent(emailContent, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Order confirmation email sent to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
