package com.keebcove.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import com.keebcove.model.Product;

public class SingletonDB {
	
    private static Connection connection;
	
    private static String jdbcUrl;
    private static String jdbcDriver;
    private static String dbUserName;
    private static String dbPassword;
	
    private SingletonDB() {}

    public static void initialize(ServletContext context) {
        jdbcDriver = context.getInitParameter("jdbcDriver");
        jdbcUrl = context.getInitParameter("jdbcUrl");
        dbUserName = context.getInitParameter("dbUserName");
        dbPassword = context.getInitParameter("dbPassword");

        if (jdbcDriver == null || jdbcUrl == null || dbUserName == null || dbPassword == null) {
            throw new RuntimeException("Database configuration parameters are missing in web.xml");
        }
    }

    private static Connection getDBConnection() {
        if (connection == null) { 
            try {
                Class.forName(jdbcDriver);
                connection = DriverManager.getConnection(jdbcUrl, dbUserName, dbPassword);
                System.out.println("Database connection established successfully.");
            } catch (ClassNotFoundException cnfe) {
                System.err.println("JDBC Driver not found: " + cnfe.getMessage());
            } catch (SQLException sqle) {
                System.err.println("SQL Exception: " + sqle.getMessage());
            }
        }
        return connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection = getDBConnection();  
        }
        return connection;
    }

    public static void insertRecord(Product product) {
        String sql = "INSERT INTO keebproducts (category, name, price, description) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); 
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, product.getCategory());
            prep.setString(2, product.getName());
            prep.setDouble(3, product.getPrice());
            prep.setString(4, product.getDescription());

            prep.executeUpdate();
            System.out.println("Product inserted successfully!");

        } catch (SQLException sqle) {
            System.err.println("Error inserting product: " + sqle.getMessage());
        }
    }
}
