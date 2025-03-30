package com.keebcove.facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.keebcove.model.CartItem;
import com.keebcove.utility.CardNumberValidator;
import com.keebcove.utility.DatabaseConnection;
import com.keebcove.utility.Facade;
import com.keebcove.utility.ProductCache;

public class CheckoutProcessingFacade implements Facade {
    private List<CartItem> cartItems;
    private String cardNumber;
    private boolean isValidCard;
    private boolean isOrderSaved;
    private boolean isStockUpdated;
    private String customerName;
    private String email;
    private String phone;
    private String address;

    public CheckoutProcessingFacade(List<CartItem> cartItems, String cardNumber, String customerName, String email, String phone, String address) {
        this.cartItems = cartItems;
        this.cardNumber = cardNumber;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    private void validateCard() {
        System.out.println("Card number before validation: " + this.cardNumber);
        this.isValidCard = CardNumberValidator.luhnTest(this.cardNumber);
        System.out.println("Card Validation Passed: " + this.isValidCard);
    }


    private void saveOrder(Connection conn) throws SQLException {
        if (this.cartItems.isEmpty()) {
            System.out.println("Cart is empty, skipping order save.");
            return;
        }

        String sql = "INSERT INTO transactions (product_id, product_name, quantity, price, total_item_price, total_order_price, card_number, customer_name, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            double totalOrderPrice = cartItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
            
            for (CartItem item : this.cartItems) {
                double totalItemPrice = item.getPrice() * item.getQuantity();
                System.out.println("Saving order for Product ID: " + item.getProductId() + ", Quantity: " + item.getQuantity());

                stmt.setInt(1, item.getProductId());
                stmt.setString(2, item.getProductName());
                stmt.setInt(3, item.getQuantity());
                stmt.setDouble(4, item.getPrice());
                stmt.setDouble(5, totalItemPrice);
                stmt.setDouble(6, totalOrderPrice);
                stmt.setString(7, this.cardNumber);
                stmt.setString(8, this.customerName);
                stmt.setString(9, this.email);
                stmt.setString(10, this.phone);
                stmt.setString(11, this.address);
                stmt.addBatch();
            }

            int[] results = stmt.executeBatch();
            this.isOrderSaved = results.length > 0;
            System.out.println("Order saved status: " + this.isOrderSaved);
        } catch (SQLException e) {
            System.err.println("Error while saving order: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void updateStock(Connection conn) throws SQLException {
        String sql = "UPDATE keebproducts SET quantity = quantity - ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (CartItem item : this.cartItems) {
                System.out.println("Updating stock for Product ID: " + item.getProductId() + ", Reducing by: " + item.getQuantity());

                stmt.setInt(1, item.getQuantity());
                stmt.setInt(2, item.getProductId());
                stmt.addBatch();
                
                ProductCache.invalidate(item.getProductId());
            }

            int[] results = stmt.executeBatch();
            this.isStockUpdated = results.length > 0;
            System.out.println("Stock updated status: " + this.isStockUpdated);
        } catch (SQLException e) {
            System.err.println("Error while updating stock: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public boolean isOrderSuccessful() {
        System.out.println("Final Order Status - Card Valid: " + this.isValidCard + ", Order Saved: " + this.isOrderSaved + ", Stock Updated: " + this.isStockUpdated);
        return this.isValidCard && this.isOrderSaved && this.isStockUpdated;
    }

    @Override
    public void process() {
        this.validateCard();
        if (this.isValidCard) {
            try (Connection conn = new DatabaseConnection().clone().getConnection()) {
                if (conn == null) {
                    System.err.println("Database connection failed!");
                    return;
                }

                conn.setAutoCommit(false);

                this.saveOrder(conn);
                if (this.isOrderSaved) {
                    this.updateStock(conn);
                }

                conn.commit();
                System.out.println("Transaction committed successfully.");
            } catch (SQLException e) {
                System.err.println("Transaction failed: " + e.getMessage());
                e.printStackTrace();
                this.isOrderSaved = false;
                this.isStockUpdated = false;
            }
        }
    }

}
