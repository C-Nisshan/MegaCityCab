package com.megacitycab.megacitycab.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class CustomerDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Validate session and role
        HttpSession session = request.getSession(false); // Don't create a new session if none exists
        if (session == null || session.getAttribute("role") == null || !"Customer".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to login if not authenticated
            return;
        }

        String username = (String) session.getAttribute("username");
        System.out.println("Username in CustomerDashboardServlet: " + username);

        try {
            // Forward the request to the dashboard JSP
            request.getRequestDispatcher("/WEB-INF/views/customer/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading customer dashboard");
        }
    }
}
