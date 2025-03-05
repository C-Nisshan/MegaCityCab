package com.megacitycab.megacitycab.servlets;

import com.megacitycab.megacitycab.dao.LoginDAO;
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
        System.out.println("Auhtentication Successful using servlet");

        // Authenticate user and fetch their role
        String role = authService.getRoleByCredentials(username, password);

        // insert into login table.
        String ipAddress = request.getRemoteAddr();
        boolean success = role != null;
        LoginDAO loginDAO = new LoginDAO(DatabaseConnection.getInstance().getConnection());
        loginDAO.logLoginAttempt(username, ipAddress, success);

        if (role != null) {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("role", role);

            if ("Admin".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                System.out.println("Successfully redirected to /admin/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/user/home");
                System.out.println("Successfully redirected to /user/home");
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}

