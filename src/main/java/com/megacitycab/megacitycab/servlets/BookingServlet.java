package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.models.*;
import com.megacitycab.megacitycab.services.BookingService;
import com.megacitycab.megacitycab.services.CarService;
import com.megacitycab.megacitycab.services.CustomerService;
import com.megacitycab.megacitycab.services.LocationService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class BookingServlet extends HttpServlet {
    private CarService carService;
    private BookingService bookingService;
    private LocationService locationService;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            carService = new CarService(connection);
            bookingService = new BookingService(connection);
            locationService = new LocationService(connection);
            customerService = new CustomerService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing services", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Booking Servlet is working!");
        try {

            List<Location> locations = locationService.getAllLocations();
            request.setAttribute("locations", locations);

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside doPost method");
        String action = request.getParameter("action");

        if("create".equals(action)){
            // Check if user is logged in
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            if (userId == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            try {
                // Retrieve customer_id using userId
                int customerId = customerService.getCustomerIdByUserId(userId);
                System.out.println("Customer id : "+ customerId);
                if (customerId == -1) {
                    return;
                }

                // Retrieve booking details from request parameters
                int carId = Integer.parseInt(request.getParameter("carId"));
                int pickupLocationId = Integer.parseInt(request.getParameter("pickupLocation"));
                int dropoffLocationId = Integer.parseInt(request.getParameter("dropoffLocation"));


                BigDecimal totalAmount = bookingService.calculateTotalCost(request);

                // Create Booking object
                Booking booking = new Booking();

                Customer customer = new Customer();
                customer.setCustomerId(customerId);

                Location pickupLocation = new Location();
                pickupLocation.setLocationId(pickupLocationId);

                Location dropoffLocation = new Location();
                dropoffLocation.setLocationId(dropoffLocationId);

                Integer assignment_id = bookingService.findDriverAssignmentIdByCarId(carId);

                Car car = new Car();
                car.setCarId(carId);

                carService.changeAssignedCarStatus(car.getCarId(),"Unavailable");
                System.out.println("Car id : " + carId);
                System.out.println("Assignment id : "+ assignment_id);
                if (assignment_id == null) {
                    response.sendRedirect(request.getContextPath() + "/booking");
                    return;
                }

                DriverAssignment driverAssignment = new DriverAssignment();
                driverAssignment.setAssignmentID(assignment_id);

                booking.setCustomer(customer);
                booking.setAssignment(driverAssignment);
                booking.setPickupLocation(pickupLocation);
                booking.setDropoffLocation(dropoffLocation);
                booking.setBookingDate(LocalDateTime.now());
                booking.setTotalAmount(totalAmount);
                booking.setStatus("Pending");

                Date now = new Date();
                booking.setCreatedAt(now);
                booking.setUpdatedAt(now);

                // Save booking
                boolean isBooked = bookingService.createBooking(booking);
                if (isBooked) {
                    response.sendRedirect(request.getContextPath() + "/booking");
                } else {
                    response.sendRedirect(request.getContextPath() + "/booking");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
