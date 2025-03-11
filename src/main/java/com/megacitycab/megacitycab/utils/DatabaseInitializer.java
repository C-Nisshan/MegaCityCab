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

            // Create Car table
            String createCarTable =
                    "CREATE TABLE IF NOT EXISTS Car (" +
                            "car_id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "registration_number VARCHAR(20) NOT NULL UNIQUE, " +
                            "make VARCHAR(50) NOT NULL, " +
                            "model VARCHAR(50) NOT NULL, " +
                            "year INT NOT NULL, " +
                            "capacity INT NOT NULL, " +
                            "status ENUM('Available', 'Unavailable', 'In Service') NOT NULL DEFAULT 'Available', " +
                            "car_type ENUM('Mini', 'Sedan', 'SUV', 'Luxary Sedan', 'MPV', 'Van', 'EV') NOT NULL," +
                            "rate_per_km DECIMAL(10,2) NOT NULL, " +
                            "is_active BOOLEAN NOT NULL DEFAULT TRUE, " +
                            "image_url VARCHAR(255) DEFAULT NULL, " +
                            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                            "updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                            ");";
            stmt.execute(createCarTable);

            // Create Driver table
            String createDriverTable =
                    "CREATE TABLE IF NOT EXISTS Driver (" +
                            "    driver_id INT AUTO_INCREMENT PRIMARY KEY," +
                            "    user_id INT UNIQUE NOT NULL," +
                            "    license_number VARCHAR(50) UNIQUE NOT NULL," +
                            "    status ENUM('Available', 'Unavailable', 'On Duty') NOT NULL DEFAULT 'Available'," +
                            "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                            "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                            "    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE" +
                            ");";
            stmt.execute(createDriverTable);

            // Create Customer table
            String createCustomerTable =
                    "CREATE TABLE IF NOT EXISTS Customer (" +
                            "    customer_id INT AUTO_INCREMENT PRIMARY KEY," +
                            "    user_id INT UNIQUE NOT NULL," +
                            "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                            "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                            "    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE" +
                            ");";
            stmt.execute(createCustomerTable);

            // Create Driver Assignment table
            String createDriverAssignmentTable =
                    "CREATE TABLE IF NOT EXISTS Driver_Assignment (" +
                            "    driver_assignment_id INT AUTO_INCREMENT PRIMARY KEY," +
                            "    car_id INT UNIQUE NOT NULL," +
                            "    driver_id INT UNIQUE NOT NULL," +
                            "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                            "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                            "    FOREIGN KEY (car_id) REFERENCES Car(car_id) ON DELETE CASCADE," +
                            "    FOREIGN KEY (driver_id) REFERENCES Driver(driver_id) ON DELETE CASCADE" +
                            ");";
            stmt.execute(createDriverAssignmentTable);

            System.out.println("All tables checked/created successfully.");

        } catch (Exception e) {
            System.err.println("Error during database initialization: " + e.getMessage());
        }
    }
}
