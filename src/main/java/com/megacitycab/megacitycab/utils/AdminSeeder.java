package com.megacitycab.megacitycab.utils;

import com.megacitycab.megacitycab.dao.UserDAO;
import com.megacitycab.megacitycab.models.User;

public class AdminSeeder {
    public static void seedAdmin() {
        UserDAO userDAO = new UserDAO(DatabaseConnection.getInstance().getConnection());

        if (!userDAO.isAdminExists()) {
            User admin = new User(1,"admin", "admin123", "Admin Name", "Admin Address", "123456789V", "0771234567", "admin@megacitycab.com", "Admin");
            userDAO.createUser(admin);
            System.out.println("Default admin created with hashed password.");
        }

        // Verify password after seeding
        boolean isMatch = userDAO.authenticateUser("admin", "admin123");
        System.out.println("Password verification after seeding: " + isMatch);
    }
}
