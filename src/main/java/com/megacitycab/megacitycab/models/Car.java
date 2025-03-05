package com.megacitycab.megacitycab.models;

import java.util.Date;

public class Car {
    private int carId;
    private String registrationNumber;
    private String make;
    private String model;
    private int year;
    private int capacity;
    private String status;
    private String imageUrl;
    private String carType;
    private float ratePerKm;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    // Getters and Setters
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }

    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public float getRatePerKm() { return ratePerKm; }
    public void setRatePerKm(float ratePerKm) { this.ratePerKm = ratePerKm; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
