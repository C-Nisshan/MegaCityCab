package com.megacitycab.megacitycab.dao;

import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    private final Connection connection;

    public DriverDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new driver
    public boolean addDriver(Driver driver) {
        String sql = "INSERT INTO driver (user_id, license_number, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, driver.getUser().getId());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getStatus());
            stmt.setTimestamp(4, new Timestamp(driver.getCreatedAt().getTime()));
            stmt.setTimestamp(5, new Timestamp(driver.getUpdatedAt().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a driver by ID
    public Driver getDriverById(int driverId) {
        String sql = "SELECT d.driver_id, d.user_id, u.username, u.name, d.license_number, d.status, d.created_at, d.updated_at " +
                "FROM driver d JOIN user u ON d.user_id = u.id WHERE d.driver_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("name"));
                Driver driver = new Driver(rs.getInt("driver_id"), user, rs.getString("license_number"),
                        rs.getString("status"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
                return driver;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT d.driver_id, d.user_id, u.username, u.name, u.address, u.telephone, u.nic, u.role, u.email, " +
                "d.license_number, d.status, d.created_at, d.updated_at " +
                "FROM driver d JOIN user u ON d.user_id = u.id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("nic"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getString("role")
                );

                Driver driver = new Driver(
                        rs.getInt("driver_id"),
                        user,
                        rs.getString("license_number"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    // Update a driver's details
    public boolean updateDriver(Driver driver) {
        String query = "UPDATE driver SET license_number=?, status=? WHERE driver_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getLicenseNumber());
            stmt.setString(2, driver.getStatus());
            stmt.setInt(3, driver.getDriverId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update driver status
    public boolean updateDriverStatus(int driverId, String status) {
        String sql = "UPDATE driver SET status = ?, updated_at = NOW() WHERE driver_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, driverId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a driver
    public boolean deleteDriver(int driverId) {
        String sql = "DELETE FROM driver WHERE driver_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, driverId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
