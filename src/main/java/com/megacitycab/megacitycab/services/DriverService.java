
package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.DriverDAO;
import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public class DriverService {
    private DriverDAO driverDAO;

    public DriverService(Connection connection) {
        this.driverDAO = new DriverDAO(connection);
    }

    public void registerDriver(Driver driver) throws SQLException {
        driverDAO.createDriver(driver);
    }

    public Driver getDriver(int driverId) throws SQLException {
        return driverDAO.getDriverById(driverId);
    }

    public List<Driver> getAllDrivers() throws SQLException {
        return driverDAO.getAllDrivers();
    }

    public boolean updateDriver(Driver driver) {
        return driverDAO.updateDriver(driver);
    }

    public boolean removeDriver(int driverId) {
        return driverDAO.deleteDriver(driverId);
    }

    public Driver driverFromRequest(HttpServletRequest request, int userId) {
        Driver driver = new Driver();
        driver.setLicenseNumber(request.getParameter("license_number"));
        driver.setStatus(request.getParameter("status") != null ? request.getParameter("status") : "Pending");

        // Assign the generated userId
        User user = new User();
        user.setId(userId);
        driver.setUser(user);

        Date now = new Date();
        driver.setCreatedAt(now);
        driver.setUpdatedAt(now);

        return driver;
    }

    public Driver updateDriverFormRequest(HttpServletRequest request) {
        Driver driver = new Driver();
        driver.setDriverId(Integer.parseInt(request.getParameter("driver_id")));
        driver.setLicenseNumber(request.getParameter("license_number"));
        driver.setStatus(request.getParameter("status"));
        return driver;
    }

    public boolean changeDriverStatus(int driverId, String status) {
        return driverDAO.updateDriverStatus(driverId, status);
    }

}
