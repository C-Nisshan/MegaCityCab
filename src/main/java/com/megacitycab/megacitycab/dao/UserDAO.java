package com.megacitycab.megacitycab.dao;
import com.megacitycab.megacitycab.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public int createUser(User user) {
        int generatedUserId = -1; // Default value if not retrieved

        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO user (username, password, name, address, " +
                        "nic, telephone, email, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)); // Hash password
            stmt.setString(1, user.getUsername());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getNic());
            stmt.setString(6, user.getTelephone());
            stmt.setString(7, user.getEmail());
            stmt.setString(8, user.getRole());

            int affectedRows = stmt.executeUpdate();
            System.out.println("User Insertion: Affected Rows = " + affectedRows);

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedUserId = generatedKeys.getInt(1);
                        System.out.println("Generated User ID: " + generatedUserId); // Debugging
                    } else {
                        System.out.println("No generated key returned.");
                    }
                }
            } else {
                System.out.println("User insertion failed!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedUserId;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setNic(rs.getString("nic"));
                user.setTelephone(rs.getString("telephone"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getString("created_at"));
                user.setUpdatedAt(rs.getString("updated_at"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // delete user
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setNic(rs.getString("nic"));
                user.setTelephone(rs.getString("telephone"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE user SET username=?, password=?, name=?, address=?, nic=?, telephone=?, email=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getNic());
            stmt.setString(6, user.getTelephone());
            stmt.setString(7, user.getEmail());
            stmt.setInt(8, user.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Authenticate User Securely
    public User authenticateUser(String username, String password) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM User WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    // If password matches, create User object
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if authentication fails
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

    public boolean isCustomerExists() {
        String sql = "SELECT COUNT(*) FROM user WHERE role = 'Customer'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"), // Ensure this is hashed
                            rs.getString("full_name"),
                            rs.getString("address"),
                            rs.getString("nic"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
