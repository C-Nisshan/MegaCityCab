package com.megacitycab.megacitycab.dao;

import java.sql.*;

import java.sql.Connection;

public class CookieDAO {
    private final Connection connection;

    public CookieDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveCookie(String username, String cookieValue, Timestamp expiration) {
        String sql = "REPLACE INTO Cookies (username, cookie_value, expiration) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, cookieValue);
            pstmt.setTimestamp(3, expiration);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
