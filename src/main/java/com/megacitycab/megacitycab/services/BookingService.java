package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.*;
import com.megacitycab.megacitycab.models.Booking;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO;
    private CustomerDAO customerDAO;
    private DriverAssignmentDAO driverAssignmentDAO;
    private PricingDAO pricingDAO;
    private CarDAO carDAO;
    private CustomerService customerService;

    public BookingService(Connection connection) {
        this.bookingDAO = new BookingDAO(connection);
        this.customerDAO = new CustomerDAO(connection);
        this.driverAssignmentDAO = new DriverAssignmentDAO(connection);
        this.pricingDAO = new PricingDAO(connection);
        this.carDAO = new CarDAO(connection);
        this.customerService = new CustomerService(connection);
    }

    // Create a new booking
    public boolean createBooking(Booking booking) {
        return bookingDAO.insertBooking(booking);
    }

    public Integer findDriverAssignmentIdByCarId(int carId) throws SQLException {
        return driverAssignmentDAO.getDriverAssignmentIdByCarId(carId);
    }

    public BigDecimal calculateTotalCost(HttpServletRequest request) throws Exception {
        int pickupLocation = Integer.parseInt(request.getParameter("pickupLocation"));
        int dropoffLocation = Integer.parseInt(request.getParameter("dropoffLocation"));
        int carId = Integer.parseInt(request.getParameter("carId"));

        BigDecimal distancePrice = pricingDAO.getPrice(pickupLocation, dropoffLocation);
        Float ratePerKm = carDAO.getRatePerKm(carId);

        if (distancePrice == null || ratePerKm == null) {
            throw new Exception("Invalid pricing or rate per km data");
        }

        BigDecimal ratePerKmDecimal = BigDecimal.valueOf(ratePerKm);
        return distancePrice.add(ratePerKmDecimal);
    }

    public List<Booking> getCustomerBookings(HttpServletRequest request) throws Exception {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            throw new Exception("User not logged in");
        }

        int customerId = customerService.getCustomerIdByUserId(userId);
        if (customerId == -1) {
            throw new Exception("Customer not found");
        }

        return bookingDAO.getBookingsByCustomerId(customerId);
    }


}
