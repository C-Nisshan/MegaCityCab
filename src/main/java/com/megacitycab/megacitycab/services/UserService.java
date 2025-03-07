package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.UserDAO;
import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public int saveUser(User user) {
        return userDAO.createUser(user);
    }

    public boolean removeUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean authenticateUser(String username, String password) {
        return userDAO.authenticateUser(username, password);
    }

    public String getUserRole(String username) {
        return userDAO.getUserRole(username);
    }

    public void updateUserLoginMetadata(String username) {
        userDAO.updateUserLoginMetadata(username);
    }

    public User userFromRequest(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        User user = new User();
        String userId = request.getParameter("id");
        if (userId != null) {
            user.setId(Integer.parseInt(userId));
        }
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12))); // Hash password
        user.setName(name);
        user.setAddress(address);
        user.setNic(nic);
        user.setTelephone(telephone);
        user.setEmail(email);
        user.setRole(role);

        return user;
    }

    public User updateUserFormRequest(HttpServletRequest request) {
        String password = request.getParameter("password");
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("user_id")));
        user.setUsername(request.getParameter("username"));
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12))); // Hash password
        user.setName(request.getParameter("name"));
        user.setAddress(request.getParameter("address"));
        user.setNic(request.getParameter("nic"));
        user.setTelephone(request.getParameter("telephone"));
        user.setEmail(request.getParameter("email"));
        return user;
    }
}
