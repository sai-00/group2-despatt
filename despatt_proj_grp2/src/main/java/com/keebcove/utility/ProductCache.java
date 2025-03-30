package com.keebcove.utility;

import com.keebcove.model.Product;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProductCache {
    private static Map<Integer, Product> productMap = new HashMap<>();
    private static final DatabaseConnection dbConnection = new DatabaseConnection();

    static {
        try {
            dbConnection.createConnection();  
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    public static Product getProduct(int productId) {
        if (productMap.containsKey(productId)) {
            return productMap.get(productId).clone();
        }

        String sql = "SELECT * FROM keebproducts WHERE id = ?";  
        try (Connection conn = dbConnection.clone().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("category"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    rs.getInt("quantity")  // ✅ Fetch quantity from DB
                );

                productMap.put(productId, product);
                return product.clone();
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product from database: " + e.getMessage());
        }

        return null;
    }

    public static void updateProductQuantity(int productId, int newQuantity) {
        if (productMap.containsKey(productId)) {
            productMap.get(productId).setQuantity(newQuantity);
        }

        String sql = "UPDATE keebproducts SET quantity = ? WHERE id = ?";
        try (Connection conn = dbConnection.clone().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQuantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating product quantity: " + e.getMessage());
        }
    }
}
