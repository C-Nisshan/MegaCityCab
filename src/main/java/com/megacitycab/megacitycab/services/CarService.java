package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.CarDAO;
import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.models.Driver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;

public class CarService {
    private final CarDAO carDAO;

    public CarService(Connection connection) {
        this.carDAO = new CarDAO(connection);
    }

    public List<Car> getAllCars() throws Exception {
        return carDAO.getAllCars();
    }

    public void saveCar(Car car) throws Exception {
        carDAO.saveCar(car);
    }

    public boolean deleteCar(int carId, String serverPath) throws Exception {
        Car car = carDAO.getCarById(carId);
        if (car != null && car.getImageUrl() != null) {
            File imageFile = new File(serverPath + car.getImageUrl());
            if (imageFile.exists()) {
                imageFile.delete();
            }
        }
        return carDAO.deleteCar(carId);
    }

    public Car getCarById(int carId) {
        return carDAO.getCarById(carId);
    }

    public Car CarFromRequest(HttpServletRequest request) throws Exception {
        String registrationNumber = request.getParameter("registrationNumber");
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String status = request.getParameter("status").trim();
        String carType = request.getParameter("carType").trim();
        float ratePerKm = Float.parseFloat(request.getParameter("ratePerKm"));
        String[] isActiveParams = request.getParameterValues("isActive");
        boolean isActive = isActiveParams != null && isActiveParams.length > 0 && "true".equals(isActiveParams[isActiveParams.length - 1]);

        System.out.println("isActive: " + isActive);

        // Handle file upload
        Part filePart = request.getPart("image");

        String imageUrl;
        if (filePart != null && filePart.getSize() > 0) {
            // New image uploaded
            String fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Store image in 'uploads' directory inside webapp
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);

            // Store only the relative path in the database
            imageUrl = "/uploads/" + fileName;
        } else {
            // No new image uploaded, use the existing one
            imageUrl = request.getParameter("existingImage");
        }


        Car car = new Car();
        String carId = request.getParameter("id");
        if(carId != null) {
            car.setCarId(Integer.parseInt(carId));
        }
        car.setRegistrationNumber(registrationNumber);
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setCapacity(capacity);
        car.setStatus(status);
        car.setCarType(carType);
        car.setRatePerKm(ratePerKm);
        car.setIsActive(isActive);
        car.setImageUrl(imageUrl);

        return car;
    }

    public boolean updateCar(Car car) {
        return carDAO.updateCar(car);
    }

    public boolean changeCarStatus(int carId, boolean isActive) {
        return carDAO.updateCarStatus(carId, isActive);
    }

    public boolean changeAssignedCarStatus(int carId, String status) {
        return carDAO.updateAssignedCarStatus(carId, status);
    }

    public List<Car> getAllCarsWithAssignedDriver() throws Exception {
        return carDAO.getAllCarsWithAssignedDriver();
    }

    public List<Integer> getAssignedDriverIds() {
        return carDAO.getAssignedDriverIds();
    }
}
