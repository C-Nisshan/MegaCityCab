package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.models.Booking;
import com.megacitycab.megacitycab.services.BookingService;
import com.megacitycab.megacitycab.services.CarService;
import com.megacitycab.megacitycab.services.CustomerService;
import com.megacitycab.megacitycab.services.LocationService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class CustomerDashboardServlet extends HttpServlet {

    private BookingService bookingService;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            bookingService = new BookingService(connection);
            customerService = new CustomerService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing services", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Validate session and role
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role") == null || !"Customer".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            int customerId = customerService.getCustomerIdByUserId(userId);
            if (customerId == -1) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // Fetch booking details
            List<Booking> bookings = bookingService.getCustomerBookings(request);
            request.setAttribute("bookings", bookings);

            // Forward to JSP
            request.getRequestDispatcher("/WEB-INF/views/customer/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading customer dashboard");
        }
    }


}
