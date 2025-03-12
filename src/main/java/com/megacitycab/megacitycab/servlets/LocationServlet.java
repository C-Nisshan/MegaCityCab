package com.megacitycab.megacitycab.servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.models.Location;
import com.megacitycab.megacitycab.models.User;
import com.megacitycab.megacitycab.services.DriverService;
import com.megacitycab.megacitycab.services.LocationService;
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

public class LocationServlet extends HttpServlet {
    private LocationService locationService;
    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection(); // Get DB connection
            locationService = new LocationService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing LocationService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Location> locations = locationService.getAllLocations();
            request.setAttribute("locations", locations);
            request.getRequestDispatcher("/WEB-INF/views/admin/manage_locations.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving drivers", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("create".equals(action)) {
                // Create Location
                boolean isLocationCreated = locationService.saveLocation(request);

                if(isLocationCreated == true){
                    response.sendRedirect("manage_locations?success=LocationCreated");
                }
            }  else if ("update".equals(action)){
                // Update Location
                boolean isLocationUpdated = locationService.editLocation(request);

                if(isLocationUpdated == true){
                    response.sendRedirect("manage_locations?success=LocationUpdated");
                }
            } else {
                response.sendRedirect("manage_locations?error=InvalidAction");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage_locations?error=ServerError");
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
            int locationId = jsonObject.get("id").getAsInt();

            // Call service methods to delete driver and user
            boolean locationDeleted = locationService.removeLocation(locationId);

            // Send appropriate response
            if (locationDeleted) {
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
