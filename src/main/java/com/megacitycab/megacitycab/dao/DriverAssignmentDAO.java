package com.megacitycab.megacitycab.dao;

import com.megacitycab.megacitycab.models.DriverAssignment;
import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.models.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverAssignmentDAO {
    private final Connection connection;

    public DriverAssignmentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDriverAssignment(DriverAssignment driverAssignment) throws SQLException {
        String sql = "INSERT INTO driver_assignment (car_id, driver_id, created_at, updated_at) VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, driverAssignment.getCar().getCarId());
            stmt.setInt(2, driverAssignment.getDriver().getDriverId());
            stmt.executeUpdate();
        }
    }

    public void updateDriverAssignment(DriverAssignment driverAssignment) throws SQLException {
        String sql = "UPDATE driver_assignment SET car_id = ?, driver_id = ?, updated_at = CURRENT_TIMESTAMP WHERE driver_assignment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, driverAssignment.getCar().getCarId());
            stmt.setInt(2, driverAssignment.getDriver().getDriverId());
            stmt.setInt(3, driverAssignment.getAssignmentID());
            stmt.executeUpdate();
        }
    }

    public DriverAssignment getDriverAssignmentById(int driverAssignmentID) throws SQLException {
        String sql = "SELECT * FROM driver_assignment WHERE driver_assignment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, driverAssignmentID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DriverAssignment(
                        rs.getInt("driver_assignment_id"),
                        new Car(rs.getInt("car_id")),
                        new Driver(rs.getInt("driver_id"))
                );
            }
        }
        return null;
    }

    public List<DriverAssignment> getAllDriverAssignments() throws SQLException {
        List<DriverAssignment> driverAssignments = new ArrayList<>();
        String sql = "SELECT * FROM driver_assignment";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                driverAssignments.add(new DriverAssignment(
                        rs.getInt("driver_assignment_id"),
                        new Car(rs.getInt("car_id")),
                        new Driver(rs.getInt("driver_id"))
                ));
            }
        }
        return driverAssignments;
    }

    public boolean deleteDriverAssignment(int carId) {
        String sql = "DELETE FROM driver_assignment WHERE car_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}