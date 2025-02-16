package com.megacitycab.megacitycab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TokenDAO {

    private final Connection connection;

    public TokenDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveToken(String username, String token, Timestamp expiresAt) {
        String sql = "REPLACE INTO Token (username, token, created_at, expires_at) VALUES (?, ?, NOW(), ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, token);
            pstmt.setTimestamp(3, expiresAt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
