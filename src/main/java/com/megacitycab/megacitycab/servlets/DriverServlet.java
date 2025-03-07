package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.models.User;
import com.megacitycab.megacitycab.services.UserService;
import com.megacitycab.megacitycab.services.DriverService;

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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;

public class DriverServlet extends HttpServlet {
    private DriverService driverService;
    private UserService userService;
    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection(); // Get DB connection
            driverService = new DriverService(connection);
            userService = new UserService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing CarService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Driver> drivers = driverService.getAllDrivers();
            request.setAttribute("drivers", drivers);
            request.getRequestDispatcher("/WEB-INF/views/admin/manage_drivers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving drivers", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String driverId = request.getParameter("driver_id");
        System.out.println("Received driver_id: " + driverId);

        try {
            if ("create".equals(action)) {
                // Step 1: Create User
                User user = userService.userFromRequest(request);
                int userId = userService.saveUser(user); // Save user and get user_id

                // Check if user was successfully created
                if (userId <= 0) {
                    System.out.println("Error: User creation failed. Cannot proceed with driver creation.");
                    response.sendRedirect("manage_drivers?error=UserCreationFailed");
                    return;
                }

                System.out.println("User successfully created with ID: " + userId);

                // Step 2: Create Driver
                Driver driver = driverService.driverFromRequest(request, userId);
                driver.getUser().setId(userId);
                driverService.registerDriver(driver);

                response.sendRedirect("manage_drivers?success=DriverCreated");
            } else if ("update".equals(action)) {
                User user = userService.updateUserFormRequest(request);
                Driver driver = driverService.updateDriverFormRequest(request);

                // Update User and Driver in Database
                boolean userUpdated = userService.updateUser(user);
                boolean driverUpdated = driverService.updateDriver(driver);

                response.sendRedirect("manage_drivers?success=DriverUpdated");
            } else {
                response.sendRedirect("manage_drivers?error=InvalidAction");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage_drivers?error=ServerError");
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
            int driverId = jsonObject.get("id").getAsInt();
            int userId = jsonObject.get("userId").getAsInt();

            // Call service methods to delete driver and user
            boolean driverDeleted = driverService.removeDriver(driverId);
            boolean userDeleted = userService.removeUser(userId);

            // Send appropriate response
            if (driverDeleted && userDeleted) {
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
