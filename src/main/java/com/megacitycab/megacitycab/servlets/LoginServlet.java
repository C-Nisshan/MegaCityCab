package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.dao.LoginDAO;
import com.megacitycab.megacitycab.models.User;
import com.megacitycab.megacitycab.services.AuthenticationService;
import com.megacitycab.megacitycab.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to login.jsp (ensure it's in WEB-INF to prevent direct access)
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AuthenticationService authService = new AuthenticationService(DatabaseConnection.getInstance().getConnection());

        // Authenticate user and get User object
        User user = authService.getUserByCredentials(username, password);

        if (user != null) {
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("role", user.getRole());

            String ipAddress = request.getRemoteAddr();
            LoginDAO loginDAO = new LoginDAO(DatabaseConnection.getInstance().getConnection());
            loginDAO.logLoginAttempt(username, ipAddress, true);

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            if (userId != null) {
                System.out.println("Logged-in User ID: " + userId);
            }

            if ("Admin".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else if ("Customer".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/customer/dashboard");
            } else if ("Driver".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/driver/dashboard");
            } else {
                response.sendRedirect(request.getContextPath());
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

}

