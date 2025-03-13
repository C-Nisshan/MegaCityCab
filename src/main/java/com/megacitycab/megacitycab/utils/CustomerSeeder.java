package com.megacitycab.megacitycab.utils;

import com.megacitycab.megacitycab.dao.UserDAO;
import com.megacitycab.megacitycab.dao.CustomerDAO;
import com.megacitycab.megacitycab.models.User;
import com.megacitycab.megacitycab.models.Customer;

import java.util.Date;

public class CustomerSeeder {
    public static void seedCustomer() {
        UserDAO userDAO = new UserDAO(DatabaseConnection.getInstance().getConnection());
        CustomerDAO customerDAO = new CustomerDAO(DatabaseConnection.getInstance().getConnection());

        if (!userDAO.isCustomerExists()) { // Implement this method in UserDAO to check if a customer exists
            // Create a default customer in the users table
            User customerUser = new User(2, "customer", "customer123", "Customer Name", "Customer Address", "987654321V", "0779876543", "customer@megacitycab.com", "Customer");
            userDAO.createUser(customerUser);
            System.out.println("Default customer created with hashed password.");

            // Retrieve the newly created user's ID (if your DB auto-generates it)
            User savedUser = userDAO.getUserByUsername("customer");

            if (savedUser != null) {
                // Create a corresponding customer entry (assuming customerId is auto-generated)
                Customer customer = new Customer(0, savedUser, new Date(), new Date());
                customerDAO.createCustomer(customer);
                System.out.println("Customer details inserted into customer table.");
            } else {
                System.out.println("Error: Failed to retrieve the created customer user.");
            }
        }

        // Verify password after seeding
        User user = userDAO.authenticateUser("customer", "customer123");
        boolean isMatch = false;
        if(user!=null) {
            isMatch = true;
        }
        System.out.println("Password verification after seeding: " + isMatch);
    }
}
