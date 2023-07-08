package com.ecommerce;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("config.properties");
                props.load(fis);
                fis.close();

                String url = props.getProperty("db.url");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                connection = DriverManager.getConnection(url, username, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
