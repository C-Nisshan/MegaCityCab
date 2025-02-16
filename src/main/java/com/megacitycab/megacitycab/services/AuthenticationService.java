package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.CookieDAO;
import com.megacitycab.megacitycab.dao.TokenDAO;
import com.megacitycab.megacitycab.dao.UserDAO;
import com.megacitycab.megacitycab.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.UUID;

public class AuthenticationService {
    private final UserDAO userDAO;

    public AuthenticationService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    public boolean authenticateUser(String username, String password) {
        // Generate a unique cookie value
        String generatedCookieValue = UUID.randomUUID().toString();

        // Set expiration timestamp for 7 days from now
        long expirationMillis = System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000); // 7 days in milliseconds
        Timestamp expirationTimestamp = new Timestamp(expirationMillis);

        // Save the cookie
        CookieDAO cookieDAO = new CookieDAO(DatabaseConnection.getInstance().getConnection());
        cookieDAO.saveCookie(username, generatedCookieValue, expirationTimestamp);

        // Generate a unique token
        String generatedToken = UUID.randomUUID().toString();

        // Set token expiration timestamp (7 days from now)
        Timestamp tokenExpiration = new Timestamp(expirationMillis);

        // Save the token
        TokenDAO tokenDAO = new TokenDAO(DatabaseConnection.getInstance().getConnection());
        tokenDAO.saveToken(username, generatedToken, tokenExpiration);
        return userDAO.authenticateUser(username, password); // Uses BCrypt for password validation
    }

    public String getRoleByCredentials(String username, String password) {
        boolean isAuthenticated = authenticateUser(username, password);
        System.out.println("User Authenticated: " + isAuthenticated);

        if (isAuthenticated) {
            String role = userDAO.getUserRole(username);
            System.out.println("Retrieved Role: " + role);
            return role;
        }
        return null;
    }

}
