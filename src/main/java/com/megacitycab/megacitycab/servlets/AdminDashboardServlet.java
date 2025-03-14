package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.models.Booking;
import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.services.BookingService;
import com.megacitycab.megacitycab.services.CarService;
import com.megacitycab.megacitycab.services.CustomerService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class AdminDashboardServlet extends HttpServlet {

    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            bookingService = new BookingService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing services", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        System.out.println("Username in AdminDashboardServlet: " + username);

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to login if session expired
            return;
        }

        // Fetch booking details
        List<Booking> bookings = null;
        try {
            bookings = bookingService.getAllBookings(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("bookings", bookings);

        try {
            // Forward the request to the dashboard JSP
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching car data");
        }
    }
}
