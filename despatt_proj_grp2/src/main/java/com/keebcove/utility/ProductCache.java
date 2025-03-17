package com.keebcove.utility;

import com.keebcove.model.Product;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProductCache {
    private static final Map<Integer, Product> productMap = new HashMap<>();

    private static String jdbcUrl;
    private static String jdbcDriver;
    private static String dbUserName;
    private static String dbPassword;

    public static void initialize(String driver, String url, String user, String password) {
        jdbcDriver = driver;
        jdbcUrl = url;
        dbUserName = user;
        dbPassword = password;
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(jdbcDriver);
        return DriverManager.getConnection(jdbcUrl, dbUserName, dbPassword);
    }

    private static Product loadProductFromDB(int productId) {
        String sql = "SELECT * FROM keebproducts WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("category"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("description")
                );

                productMap.put(productId, product);
                return product;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error fetching product from database: " + e.getMessage());
        }
        return null;
    }

    public static Product getClonedProduct(int productId) {
        Product cachedProduct = productMap.get(productId);

        if (cachedProduct == null) {
            cachedProduct = loadProductFromDB(productId); 
        }

        if (cachedProduct != null) {
            return cachedProduct.clone(); 
        }

        System.err.println("Product with ID " + productId + " not found.");
        return null;
    }
}
