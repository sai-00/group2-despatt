package com.keebcove.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements Cloneable {
    private static String jdbcUrl;
    private static String jdbcDriver;
    private static String dbUserName;
    private static String dbPassword;

    private Connection connection;

    public static void initialize(String driver, String url, String user, String password) {
        jdbcDriver = driver;
        jdbcUrl = url;
        dbUserName = user;
        dbPassword = password;
    }

    public void createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(jdbcDriver);
        this.connection = DriverManager.getConnection(jdbcUrl, dbUserName, dbPassword);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public DatabaseConnection clone() {
        try {
            DatabaseConnection cloned = (DatabaseConnection) super.clone();
            cloned.createConnection();  
            return cloned;
        } catch (CloneNotSupportedException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error cloning Database Connection!", e);
        }
    }
}
