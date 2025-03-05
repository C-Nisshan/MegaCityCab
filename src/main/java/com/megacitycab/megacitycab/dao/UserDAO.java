package com.megacitycab.megacitycab.dao;

import com.megacitycab.megacitycab.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean isAdminExists() {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM User WHERE role = 'Admin'")) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createUser(User user) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO User (username, password, name, address, nic, telephone, email, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)); // Hash the password before storing
            stmt.setString(1, user.getUsername());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getNic());
            stmt.setString(6, user.getTelephone());
            stmt.setString(7, user.getEmail());
            stmt.setString(8, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Authenticate User Securely
    public boolean authenticateUser(String username, String password) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT password FROM User WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                boolean passwordMatch = BCrypt.checkpw(password, storedHashedPassword);
                System.out.println("Password Matched: " + passwordMatch);
                return passwordMatch;
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // Get User Role Securely
    public String getUserRole(String username) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT role FROM User WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                System.out.println("Role Retrieved from DB: " + role);
                return role;
            } else {
                System.out.println("Role retrieval failed: User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // update login count
    public void updateUserLoginMetadata(String username) {
        String sql = "UPDATE User SET last_login = NOW(), login_count = login_count + 1 WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
