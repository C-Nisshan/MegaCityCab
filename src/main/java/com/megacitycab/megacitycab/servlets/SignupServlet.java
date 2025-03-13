package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.models.Customer;
import com.megacitycab.megacitycab.models.User;
import com.megacitycab.megacitycab.services.CustomerService;
import com.megacitycab.megacitycab.services.UserService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

public class SignupServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to about.jsp
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("signup".equals(action)) {
                // Step 1: Create User
                User user = userService.userFromRequest(request);
                int userId = userService.saveUser(user); // Save user and get user_id

                // Check if user was successfully created
                if (userId <= 0) {
                    System.out.println("Error: User creation failed. Cannot proceed with customer creation.");
                    response.sendRedirect("signup?error=UserCreationFailed");
                    return;
                }

                System.out.println("User successfully created with ID: " + userId);

                // Step 2: Create Customer
                Customer customer = customerService.customerFromRequest(request, userId);
                customer.getUser().setId(userId);
                customerService.registerCustomer(customer);

                // Redirect to the BookingServlet after signup
                response.sendRedirect("booking");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signup?error=ServerError");
        }
    }
}
