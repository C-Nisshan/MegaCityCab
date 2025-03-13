package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.services.CarService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        System.out.println("Username in AdminDashboardServlet: " + username);

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to login if session expired
            return;
        }

        try {
            // Forward the request to the dashboard JSP
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching car data");
        }
    }
}
