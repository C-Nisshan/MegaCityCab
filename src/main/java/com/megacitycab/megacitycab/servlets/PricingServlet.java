package com.megacitycab.megacitycab.servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megacitycab.megacitycab.dao.PricingDAO;
import com.megacitycab.megacitycab.models.Location;
import com.megacitycab.megacitycab.models.Pricing;

import com.megacitycab.megacitycab.services.LocationService;
import com.megacitycab.megacitycab.services.PricingService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;


public class PricingServlet extends HttpServlet {

    private PricingService pricingService;
    private LocationService locationService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection(); // Get DB connection
            pricingService = new PricingService(connection);
            locationService = new LocationService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing LocationService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pricing> pricings = null;
        List<Location> locations = null;
        try {
            pricings = pricingService.getAllPricings();
            locations = locationService.getAllLocations();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("pricings", pricings);
        request.setAttribute("locations", locations);
        request.getRequestDispatcher("/WEB-INF/views/admin/manage_pricing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try{
            if ("create".equals(action)) {
                boolean isPricingCreated = pricingService.savePricing(request);
                if (isPricingCreated) {
                    response.sendRedirect("manage_pricing?success=PricingCreated");
                }
            } else if ("update".equals(action)) {
                boolean isPricingUpdated = pricingService.editPricing(request);
                if (isPricingUpdated) {
                    response.sendRedirect("manage_pricing?success=PricingUpdated");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage_pricing?error=ServerError");
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
            int pricingId = jsonObject.get("id").getAsInt();

            // Call service methods to delete driver and user
            boolean isPricingDeleted = pricingService.removePricingById(pricingId);

            // Send appropriate response
            if (isPricingDeleted) {
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
