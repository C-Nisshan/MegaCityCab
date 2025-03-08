package com.megacitycab.megacitycab.models;

import java.util.Date;

public class Customer extends DateEntity {
    private int customerId;
    private User user; // Foreign key reference to User table

    // Constructors
    public Customer() {}

    public Customer(int customerId, User user, Date createdAt, Date updatedAt) {
        this.customerId = customerId;
        this.user = user;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int driverId) {
        this.customerId = customerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
