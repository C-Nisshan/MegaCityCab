package com.megacitycab.megacitycab.models;

public class DriverAssignment extends DateEntity{
    private int assignmentID;
    private Car car;
    private Driver driver;

    public DriverAssignment(int assignmentID, Car car, Driver driver) {
        this.assignmentID = assignmentID;
        this.car = car;
        this.driver = driver;
    }

    public DriverAssignment(Car car, Driver driver) {
        this.car = car;
        this.driver = driver;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
