package com.megacitycab.megacitycab.dao;

import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.models.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.megacitycab.megacitycab.models.Location;
import com.megacitycab.megacitycab.models.User;

public class LocationDAO {
    private final Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new driver
    public boolean createLocation(Location location) {
        String sql = "INSERT INTO location (location_name, created_at, updated_at) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, location.getLocationName());
            stmt.setTimestamp(2, new Timestamp(location.getCreatedAt().getTime()));
            stmt.setTimestamp(3, new Timestamp(location.getUpdatedAt().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Location> getAllLocations() {
        List<Location> locationList = new ArrayList<>();
        String query = "SELECT * FROM location"; // Your query to get all cars

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Location location = new Location();
                location.setLocationId(rs.getInt("location_id"));
                location.setLocationName(rs.getString("location_name"));
                locationList.add(location);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locationList;
    }

    public boolean updateLocation(Location location) {
        String sql = "UPDATE location SET location_name = ?, updated_at = ? WHERE location_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, location.getLocationName());
            stmt.setTimestamp(2, new Timestamp(location.getUpdatedAt().getTime()));
            stmt.setInt(3, location.getLocationId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a driver
    public boolean deleteLocation(int locationId) {
        String sql = "DELETE FROM location WHERE location_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, locationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
