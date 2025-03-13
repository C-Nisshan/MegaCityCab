package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.CarDAO;
import com.megacitycab.megacitycab.dao.DriverAssignmentDAO;
import com.megacitycab.megacitycab.dao.DriverDAO;
import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.models.DriverAssignment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DriverAssignmentService {
    private DriverDAO driverDAO;
    private CarDAO carDAO;
    private DriverAssignmentDAO driverAssignmentDAO;
    private CarService carService;
    private DriverService driverService;

    public DriverAssignmentService(Connection connection) {
        this.carDAO = new CarDAO(connection);
        this.driverDAO = new DriverDAO(connection);
        this.driverAssignmentDAO = new DriverAssignmentDAO(connection);
        this.carService = new CarService(connection);
        this.driverService = new DriverService(connection);
    }

    public List<Driver> getAllAvailableDrivers() throws SQLException {
        return driverDAO.getAllDrivers();
    }

    public boolean assignDriverToCar(Car car, Driver driver) throws Exception {

        // Create DriverAssignment object and save it using DAO
        DriverAssignment driverAssignment = new DriverAssignment(car, driver);
        driverAssignmentDAO.addDriverAssignment(driverAssignment);

        // Update car and driver status after assignment
        carService.changeAssignedCarStatus(car.getCarId(),"Unavailable");
        driverService.changeDriverStatus(driver.getDriverId(), "Unavailable");

        return true;
    }

    public boolean removeDriverFromCar(Car car, Driver driver) {
        driverAssignmentDAO.deleteDriverAssignment(car.getCarId());

        // Update car and driver status after assignment
        carService.changeAssignedCarStatus(car.getCarId(),"Available");
        driverService.changeDriverStatus(driver.getDriverId(), "Available");
        return true;
    }

}
