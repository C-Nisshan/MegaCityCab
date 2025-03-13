
package com.megacitycab.megacitycab.listeners;

import com.megacitycab.megacitycab.utils.AdminSeeder;

import com.megacitycab.megacitycab.utils.CustomerSeeder;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import com.megacitycab.megacitycab.utils.DatabaseInitializer;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;

public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application is starting up...");
        AdminSeeder.seedAdmin();
        System.out.println("Admin user seeding completed.");

        System.out.println("Database initialization is starting up...");
        Connection connection = DatabaseConnection.getInstance().getConnection();
        DatabaseInitializer.initializeDatabase(connection); // Create tables
        AdminSeeder.seedAdmin(); // Seed admin user
        CustomerSeeder.seedCustomer(); // Seed customer
        System.out.println("Database initialization and admin user seeding completed.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application is shutting down...");
    }
}

