package com.megacitycab.megacitycab.dao;

import com.megacitycab.megacitycab.models.Car;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDAO {

    private final Connection connection;

    public CarDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        String query = "SELECT * FROM Car"; // Your query to get all cars

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Car car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setRegistrationNumber(rs.getString("registration_number"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setCapacity(rs.getInt("capacity"));
                car.setStatus(rs.getString("status"));
                car.setCarType(rs.getString("car_type"));
                car.setRatePerKm(rs.getFloat("rate_per_km"));
                car.setIsActive(rs.getBoolean("is_active"));
                car.setImageUrl(rs.getString("image_url"));
                car.setCreatedAt(rs.getTimestamp("created_at"));
                car.setUpdatedAt(rs.getTimestamp("updated_at"));

                carList.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carList;
    }

    public void saveCar(Car car) throws SQLException {
        String sql = "INSERT INTO car (registration_number, make, model, year, capacity, status, car_type, rate_per_km, is_active, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, car.getRegistrationNumber());
        ps.setString(2, car.getMake());
        ps.setString(3, car.getModel());
        ps.setInt(4, car.getYear());
        ps.setInt(5, car.getCapacity());
        ps.setString(6, car.getStatus());
        ps.setString(7, car.getCarType());
        ps.setFloat(8, car.getRatePerKm());
        ps.setBoolean(9, car.getIsActive());
        ps.setString(10, car.getImageUrl());

        System.out.println("Executing SQL: " + ps.toString());
        ps.executeUpdate();
    }

    public boolean deleteCar(int carId) {
        String sql = "DELETE FROM car WHERE car_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Car getCarById(int carId) {
        Car car = null;
        String query = "SELECT * FROM car WHERE car_id = ?"; // Adjust table/column names if needed
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setRegistrationNumber(rs.getString("registration_number"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setCapacity(rs.getInt("capacity"));
                car.setStatus(rs.getString("status"));
                car.setCarType(rs.getString("car_type"));
                car.setRatePerKm(rs.getFloat("rate_per_km"));
                car.setIsActive(rs.getBoolean("is_active"));
                car.setImageUrl(rs.getString("image_url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    public boolean updateCar(Car car) {
        String sql = "UPDATE car SET registration_number=?, make=?, model=?, year=?, capacity=?, status=?, car_type=?, rate_per_km=?, is_active=?, image_url=? WHERE car_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, car.getRegistrationNumber());
            stmt.setString(2, car.getMake());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setInt(5, car.getCapacity());
            stmt.setString(6, car.getStatus());
            stmt.setString(7, car.getCarType());
            stmt.setFloat(8, car.getRatePerKm());
            stmt.setBoolean(9, car.getIsActive());
            stmt.setString(10, car.getImageUrl());
            stmt.setInt(11, car.getCarId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
