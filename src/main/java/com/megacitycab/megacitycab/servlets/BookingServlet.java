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
import java.util.List;

public class BookingServlet extends HttpServlet {
    private CarService carService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            carService = new CarService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing services", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Booking Servlet is working!");
        try {

            System.out.println("Fetching available cars...");
            List<Car> carList = carService.getAllAvailableCars();
            System.out.println("Car list retrieved: " + (carList != null ? carList.size() : "null"));
            request.setAttribute("carList", carList);

            System.out.println("Forwarding to JSP...");
            request.getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Print the actual exception
            request.setAttribute("error", "Error retrieving available cars cars.");
        }
    }
}
