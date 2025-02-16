package com.megacitycab.megacitycab.utils;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            // Create User table if it doesn't exist
            String createUserTable =
                    "CREATE TABLE IF NOT EXISTS User (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "username VARCHAR(50) NOT NULL UNIQUE, " +
                            "password VARCHAR(255) NOT NULL, " +
                            "name VARCHAR(100) NOT NULL, " +
                            "address VARCHAR(255), " +
                            "nic VARCHAR(12) NOT NULL UNIQUE, " +
                            "telephone VARCHAR(15), " +
                            "email VARCHAR(100) NOT NULL UNIQUE, " +
                            "role VARCHAR(20) NOT NULL, " +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                            ");";
            stmt.execute(createUserTable);

            // Create Login table
            String createLoginTable =
                    "CREATE TABLE IF NOT EXISTS Login (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "username VARCHAR(50), " +
                            "login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "ip_address VARCHAR(45), " +
                            "success BOOLEAN, " +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                            ");";
            stmt.execute(createLoginTable);

            // Create Cookies table
            String createCookiesTable =
                    "CREATE TABLE IF NOT EXISTS Cookies (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "username VARCHAR(50), " +
                            "cookie_value VARCHAR(255), " +
                            "expiration TIMESTAMP, " +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                            ");";
            stmt.execute(createCookiesTable);

            // Create Token table
            String createTokenTable =
                    "CREATE TABLE IF NOT EXISTS Token (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "username VARCHAR(50), " +
                            "token VARCHAR(255), " +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "expires_at TIMESTAMP" +
                            ");";
            stmt.execute(createTokenTable);

            // Create Admin table (optional)
            String createAdminTable =
                    "CREATE TABLE IF NOT EXISTS Admin (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "user_id INT, " +
                            "privileges TEXT, " +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                            "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE" +
                            ");";
            stmt.execute(createAdminTable);

            System.out.println("All tables checked/created successfully.");

        } catch (Exception e) {
            System.err.println("Error during database initialization: " + e.getMessage());
        }
    }
}
