package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.services.CarService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CarServlet extends HttpServlet {

    private CarService carService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection(); // Get DB connection
            carService = new CarService(connection); // Inject into service
        } catch (Exception e) {
            throw new ServletException("Error initializing CarService", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String carId = request.getParameter("id"); // Debugging
        System.out.println("Received car_id: " + carId); // Debugging

        try {
            if ("create".equals(action)) {
                Car car = carService.CarFromRequest(request);
                carService.saveCar(car);
                response.sendRedirect("manage_cars?success=CarCreated");
            } else if ("update".equals(action)) {
                if (carId == null || carId.isEmpty()) {
                    response.sendRedirect("manage_cars?error=MissingCarId");
                    return;
                }
                Car updatedCar = carService.CarFromRequest(request);
                boolean success = carService.updateCar(updatedCar);
                response.sendRedirect(success ? "manage_cars?success=CarUpdated" : "manage_cars?error=UpdateFailed");
            } else {
                response.sendRedirect("manage_cars?error=InvalidAction");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage_cars?error=ServerError");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int carId = Integer.parseInt(request.getParameter("id"));
            boolean deleted = carService.deleteCar(carId, getServletContext().getRealPath(""));
            response.getWriter().write(deleted ? "success" : "failure");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String carIdParam = request.getParameter("id");
            if (carIdParam != null) {
                int carId = Integer.parseInt(carIdParam);
                Car car = carService.getCarById(carId);
                if (car != null) {
                    request.setAttribute("car", car);
                    request.getRequestDispatcher("/WEB-INF/views/admin/edit_car.jsp").forward(request, response);
                } else {
                    response.sendRedirect("manage_cars?error=CarNotFound");
                }
            } else {
                List<Car> carList = carService.getAllCars();
                request.setAttribute("carList", carList);
                request.getRequestDispatcher("/WEB-INF/views/admin/manage_cars.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("manage_cars?error=InvalidID");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage_cars?error=ServerError");
        }
    }
}
