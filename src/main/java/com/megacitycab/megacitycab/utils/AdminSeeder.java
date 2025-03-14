package com.megacitycab.megacitycab.utils;

import com.megacitycab.megacitycab.dao.UserDAO;
import com.megacitycab.megacitycab.factories.UserFactory;
import com.megacitycab.megacitycab.models.User;
import com.megacitycab.megacitycab.observers.AdminCreationObserver;

public class AdminSeeder {
    public static void seedAdmin() {
        UserDAO userDAO = new UserDAO(DatabaseConnection.getInstance().getConnection());

        if (!userDAO.isAdminExists()) {
            User admin = UserFactory.createUser(1, "admin", "admin123",
                    "Admin Name", "Admin Address", "123456789V",
                    "0771234567", "admin@megacitycab.com", "Admin");
            userDAO.createUser(admin);
            System.out.println("Default admin created with hashed password.");

            // Notify observers about the new admin
            AdminCreationObserver.notifyAdminCreated(admin.getUsername(), admin.getEmail());
        }

        // Verify password after seeding
        User user = userDAO.authenticateUser("admin", "admin123");
        boolean isMatch = (user != null);
        System.out.println("Password verification after seeding: " + isMatch);
    }
}


