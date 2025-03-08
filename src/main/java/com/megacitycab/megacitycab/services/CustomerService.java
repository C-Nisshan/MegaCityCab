package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.CustomerDAO;
import com.megacitycab.megacitycab.models.Customer;
import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService(Connection connection) {
        this.customerDAO = new CustomerDAO(connection);
    }

    public void registerCustomer(Customer customer) throws SQLException {
        customerDAO.createCustomer(customer);
    }

    public Customer getCustomer(int customerId) throws SQLException {
        return customerDAO.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    public boolean removeCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }

    public Customer customerFromRequest(HttpServletRequest request, int userId) {
        Customer customer = new Customer();

        // Assign the generated userId
        User user = new User();
        user.setId(userId);
        customer.setUser(user);

        Date now = new Date();
        customer.setCreatedAt(now);
        customer.setUpdatedAt(now);

        return customer;
    }
}
