package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.services.CarService;
import com.megacitycab.megacitycab.services.DriverAssignmentService;
import com.megacitycab.megacitycab.services.DriverService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class DriverAssignmentServlet extends HttpServlet {
    private DriverService driverService;
    private DriverAssignmentService driverAssignmentService;
    private CarService carService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection(); // Get DB connection
            driverService = new DriverService(connection);
            driverAssignmentService = new DriverAssignmentService(connection);
            carService = new CarService(connection);
        } catch (Exception e) {
            throw new ServletException("Error initializing services", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("Fetching assigned driver IDs...");
            List<Integer> assignedDriverIds = carService.getAssignedDriverIds();
            request.setAttribute("assignedDriverIds", assignedDriverIds);

            System.out.println("Fetching cars with assigned drivers...");
            List<Car> carList = carService.getAllCarsWithAssignedDriver();
            request.setAttribute("carList", carList);

            System.out.println("Fetching available drivers...");
            List<Driver> drivers = driverAssignmentService.getAllAvailableDrivers();
            request.setAttribute("drivers", drivers);

            System.out.println("Forwarding to JSP...");
            request.getRequestDispatcher("/WEB-INF/views/admin/driver_assignment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Print the actual exception
            request.setAttribute("error", "Error retrieving drivers and cars.");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("remove".equals(action)) {
            doDelete(request, response);
            return;
        }

        String carIdStr = request.getParameter("carId");
        String driverIdStr = request.getParameter("driverId");

        if (carIdStr == null || driverIdStr == null || carIdStr.isEmpty() || driverIdStr.isEmpty()) {
            response.sendRedirect("driver_assignment?error=InvalidInput");
            return;
        }

        int carId = Integer.parseInt(request.getParameter("carId"));
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        Car car = new Car(carId);
        Driver driver = new Driver(driverId);
        boolean success;
        try {
            success = driverAssignmentService.assignDriverToCar(car, driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (success) {
            response.sendRedirect("driver_assignment?success=1");
        } else {
            response.sendRedirect("driver_assignment?error=1");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("carId"));
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        Car car = new Car(carId);
        Driver driver = new Driver(driverId);

        boolean isRemoved;
        try {
            isRemoved = driverAssignmentService.removeDriverFromCar(car, driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (isRemoved) {
            response.sendRedirect("driver_assignment?success=removed");
        } else {
            response.sendRedirect("driver_assignment?error=remove_failed");
        }
    }
}
