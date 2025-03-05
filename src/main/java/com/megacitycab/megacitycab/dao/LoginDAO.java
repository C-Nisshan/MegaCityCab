package com.megacitycab.megacitycab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private final Connection connection;

    public LoginDAO(Connection connection) {
        this.connection = connection;
    }

    public void logLoginAttempt(String username, String ipAddress, boolean success) {
        String sql = "INSERT INTO Login (username, ip_address, success) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, ipAddress);
            stmt.setBoolean(3, success);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getLoginAttempts(String username) {
        String sql = "SELECT * FROM Login WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            return stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
