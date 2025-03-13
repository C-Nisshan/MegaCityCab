package com.megacitycab.megacitycab.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Prevents creating a new session if none exists
        if (session != null) {
            session.invalidate(); // Destroys session
        }
        response.sendRedirect(request.getContextPath() + "/home.jsp"); // Redirect to home after logout
    }
}