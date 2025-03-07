package com.megacitycab.megacitycab.models;

import java.util.Date;

public class Driver extends DateEntity{
    private int driverId;
    private User user; // Foreign key reference to User table
    private String licenseNumber;
    private String status;

    // Constructors
    public Driver() {}

    public Driver(int driverId, User user, String licenseNumber, String status, Date createdAt, Date updatedAt) {
        this.driverId = driverId;
        this.user = user;
        this.licenseNumber = licenseNumber;
        this.status = status;
    }

    // Getters and Setters
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
