package com.megacitycab.megacitycab.models;

import java.math.BigDecimal;
import java.util.Date;

public class Pricing {
    private int pricingId;
    private int pickupLocation;
    private int dropoffLocation;
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;

    // Constructors
    public Pricing() {
    }

    public Pricing(int pickupLocation, int dropoffLocation, BigDecimal price, Date createdAt, Date updatedAt) {
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getPricingId() {
        return pricingId;
    }

    public void setPricingId(int pricingId) {
        this.pricingId = pricingId;
    }

    public int getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(int pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public int getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(int dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
