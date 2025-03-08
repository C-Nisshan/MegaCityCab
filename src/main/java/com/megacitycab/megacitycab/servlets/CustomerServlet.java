package com.megacitycab.megacitycab.servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megacitycab.megacitycab.models.Customer;
import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.models.User;
import com.megacitycab.megacitycab.services.CustomerService;
import com.megacitycab.megacitycab.services.UserService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;
    private UserService userService;
    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection(); // Get DB connection
            customerService = new CustomerService(connection);
            userService = new UserService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing CarService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/WEB-INF/views/admin/manage_customers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving customers", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String customerId = request.getParameter("customer_id");
        System.out.println("Received customer: " + customerId);

        try {
            if ("create".equals(action)) {
                // Step 1: Create User
                User user = userService.userFromRequest(request);
                int userId = userService.saveUser(user); // Save user and get user_id

                // Check if user was successfully created
                if (userId <= 0) {
                    System.out.println("Error: User creation failed. Cannot proceed with driver creation.");
                    response.sendRedirect("manage_customers?error=UserCreationFailed");
                    return;
                }

                System.out.println("User successfully created with ID: " + userId);

                // Step 2: Create Customer
                Customer customer = customerService.customerFromRequest(request, userId);
                customer.getUser().setId(userId);
                customerService.registerCustomer(customer);

                response.sendRedirect("manage_customers?success=CustomerCreated");
            } else if ("update".equals(action)) {
                User user = userService.updateUserFormRequest(request);

                // Update User and Driver in Database
                boolean userUpdated = userService.updateUser(user);
                response.sendRedirect("manage_customers?success=CustomerUpdated");
            } else {
                response.sendRedirect("manage_customers?error=InvalidAction");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage_customers?error=ServerError");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Read JSON data from request body
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // Parse JSON data
            JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();

            // Extract driverId and userId from JSON
            int customerId = jsonObject.get("id").getAsInt();
            int userId = jsonObject.get("userId").getAsInt();

            // Call service methods to delete driver and user
            boolean customerDeleted = customerService.removeCustomer(customerId);
            boolean userDeleted = userService.removeUser(userId);

            // Send appropriate response
            if (customerDeleted && userDeleted) {
                response.getWriter().write("{\"status\": \"success\"}");
            } else {
                response.getWriter().write("{\"status\": \"failure\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\": \"error\"}");
        }
    }
}
