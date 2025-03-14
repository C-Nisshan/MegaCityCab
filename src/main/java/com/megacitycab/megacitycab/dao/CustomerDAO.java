package com.megacitycab.megacitycab.dao;

import com.megacitycab.megacitycab.models.Customer;
import com.megacitycab.megacitycab.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private final Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new Customer
    public boolean createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (user_id, created_at, updated_at) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customer.getUser().getId());
            stmt.setTimestamp(2, new Timestamp(customer.getCreatedAt().getTime()));
            stmt.setTimestamp(3, new Timestamp(customer.getUpdatedAt().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a customer by ID
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT c.customer_id, c.user_id, c.username, c.name, c.created_at, c.updated_at " +
                "FROM customer c JOIN user u ON c.user_id = u.id WHERE c.customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("name"));
                Customer customer = new Customer(rs.getInt("customer_id"), user,
                         rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all customers
    public List<com.megacitycab.megacitycab.models.Customer> getAllCustomers() {
        List<com.megacitycab.megacitycab.models.Customer> customers = new ArrayList<>();
        String sql = "SELECT c.customer_id, c.user_id, u.username, u.name, u.address, u.telephone, u.nic, u.role, u.email, " +
                "c.created_at, c.updated_at " +
                "FROM customer c JOIN user u ON c.user_id = u.id";
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

                com.megacitycab.megacitycab.models.Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        user,
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Delete a driver
    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getCustomerIdByUserId(int userId) {
        String sql = "SELECT customer_id FROM Customer WHERE user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Customer ID not found
    }

}
