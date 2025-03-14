package com.megacitycab.megacitycab.dao;

import com.megacitycab.megacitycab.models.Booking;
import com.megacitycab.megacitycab.models.Customer;
import com.megacitycab.megacitycab.models.DriverAssignment;
import com.megacitycab.megacitycab.models.Location;
import com.megacitycab.megacitycab.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private final Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertBooking(Booking booking) {
        String sql = "INSERT INTO Booking (customer_id, assignment_id, pickup_location_id, dropoff_location_id, booking_date, total_amount, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            System.out.println("Executing SQL: " + sql);
            System.out.println("Parameters: " +
                    booking.getCustomer().getCustomerId() + ", " +
                    booking.getAssignment().getAssignmentID() + ", " +
                    booking.getPickupLocation().getLocationId() + ", " +
                    booking.getDropoffLocation().getLocationId() + ", " +
                    booking.getBookingDate() + ", " +
                    booking.getTotalAmount() + ", " +
                    booking.getStatus() + ", " +
                    new Timestamp(booking.getCreatedAt().getTime()) + ", " +
                    new Timestamp(booking.getUpdatedAt().getTime())
            );

            stmt.setInt(1, booking.getCustomer().getCustomerId());
            stmt.setInt(2, booking.getAssignment().getAssignmentID());
            stmt.setInt(3, booking.getPickupLocation().getLocationId());
            stmt.setInt(4, booking.getDropoffLocation().getLocationId());
            stmt.setObject(5, booking.getBookingDate());
            stmt.setBigDecimal(6, booking.getTotalAmount());
            stmt.setString(7, booking.getStatus());
            stmt.setTimestamp(8, new Timestamp(booking.getCreatedAt().getTime()));
            stmt.setTimestamp(9, new Timestamp(booking.getUpdatedAt().getTime()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> getBookingsByCustomerId(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.booking_id, " +
                "       p.location_name AS pickup_location_name, " +
                "       d.location_name AS dropoff_location_name, " +
                "       b.total_amount AS total_amount, " +
                "       b.status, " +
                "       b.booking_date, " +
                "       b.created_at, " +
                "       b.assignment_id, " +
                "       b.pickup_location_id, " +
                "       b.dropoff_location_id " +
                "FROM booking b " +
                "JOIN location p ON b.pickup_location_id = p.location_id " +
                "JOIN location d ON b.dropoff_location_id = d.location_id " +
                "WHERE b.customer_id = ? " +
                "ORDER BY b.created_at DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();

                // Populate basic Booking fields
                booking.setBookingId(rs.getInt("booking_id"));
                // Set Pickup Location with both ID and Name
                Location pickupLocation = new Location();
                pickupLocation.setLocationId(rs.getInt("pickup_location_id")); // Set the ID
                pickupLocation.setLocationName(rs.getString("pickup_location_name")); // Set the name
                booking.setPickupLocation(pickupLocation);

                // Set Dropoff Location with both ID and Name
                Location dropoffLocation = new Location();
                dropoffLocation.setLocationId(rs.getInt("dropoff_location_id")); // Set the ID
                dropoffLocation.setLocationName(rs.getString("dropoff_location_name")); // Set the name

                booking.setDropoffLocation(dropoffLocation);
                booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                booking.setStatus(rs.getString("status"));
                booking.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());

                // Convert created_at to Date before setting it
                Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
                if (createdAtTimestamp != null) {
                    booking.setCreatedAt(new Date(createdAtTimestamp.getTime())); // Convert to Date
                }

                // Populate Assignment (DriverAssignment)
                DriverAssignment assignment = new DriverAssignment();
                assignment.setAssignmentID(rs.getInt("assignment_id"));
                booking.setAssignment(assignment);

                // Populate Customer (fetch customer by ID)
                Customer customer = new Customer(); // Assuming you have a method to fetch customer by ID
                customer.setCustomerId(customerId); // This can be fetched if required
                booking.setCustomer(customer);

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.booking_id, " +
                "       p.location_name AS pickup_location_name, " +
                "       d.location_name AS dropoff_location_name, " +
                "       b.total_amount AS total_amount, " +
                "       b.status, " +
                "       b.booking_date, " +
                "       b.created_at, " +
                "       b.assignment_id, " +
                "       b.pickup_location_id, " +
                "       b.dropoff_location_id " +
                "FROM booking b " +
                "JOIN location p ON b.pickup_location_id = p.location_id " +
                "JOIN location d ON b.dropoff_location_id = d.location_id " +
                "ORDER BY b.created_at DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();

                // Populate basic Booking fields
                booking.setBookingId(rs.getInt("booking_id"));
                // Set Pickup Location with both ID and Name
                Location pickupLocation = new Location();
                pickupLocation.setLocationId(rs.getInt("pickup_location_id")); // Set the ID
                pickupLocation.setLocationName(rs.getString("pickup_location_name")); // Set the name
                booking.setPickupLocation(pickupLocation);

                // Set Dropoff Location with both ID and Name
                Location dropoffLocation = new Location();
                dropoffLocation.setLocationId(rs.getInt("dropoff_location_id")); // Set the ID
                dropoffLocation.setLocationName(rs.getString("dropoff_location_name")); // Set the name

                booking.setDropoffLocation(dropoffLocation);
                booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                booking.setStatus(rs.getString("status"));
                booking.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());

                // Convert created_at to Date before setting it
                Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
                if (createdAtTimestamp != null) {
                    booking.setCreatedAt(new Date(createdAtTimestamp.getTime())); // Convert to Date
                }

                // Populate Assignment (DriverAssignment)
                DriverAssignment assignment = new DriverAssignment();
                assignment.setAssignmentID(rs.getInt("assignment_id"));
                booking.setAssignment(assignment);

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

}
