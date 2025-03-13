package com.megacitycab.megacitycab.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking extends DateEntity {
    private int bookingId;
    private Customer customer; // Foreign key reference to Customer table
    private DriverAssignment assignment; // Foreign key reference to Assignment table
    private Location pickupLocation; // Foreign key reference to Location table
    private Location dropoffLocation; // Foreign key reference to Location table
    private LocalDateTime bookingDate;
    private BigDecimal totalAmount;
    private String status;

    public Booking() {
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DriverAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(DriverAssignment assignment) {
        this.assignment = assignment;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(Location dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
