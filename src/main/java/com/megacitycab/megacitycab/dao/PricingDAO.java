package com.megacitycab.megacitycab.dao;

import com.megacitycab.megacitycab.models.Pricing;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PricingDAO {
    private final Connection connection;

    public PricingDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new pricing entry
    public boolean createPricing(Pricing pricing) {
        String sql = "INSERT INTO pricing (pickup_location, dropoff_location, price, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pricing.getPickupLocation());
            stmt.setInt(2, pricing.getDropoffLocation());
            stmt.setBigDecimal(3, pricing.getPrice());
            stmt.setTimestamp(4, new Timestamp(pricing.getCreatedAt().getTime()));
            stmt.setTimestamp(5, new Timestamp(pricing.getUpdatedAt().getTime()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all pricing records
    public List<Pricing> getAllPricings() {
        List<Pricing> pricingList = new ArrayList<>();
        String sql = "SELECT * FROM pricing";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pricing pricing = new Pricing();
                pricing.setPricingId(rs.getInt("pricing_id"));
                pricing.setPickupLocation(rs.getInt("pickup_location"));
                pricing.setDropoffLocation(rs.getInt("dropoff_location"));
                pricing.setPrice(rs.getBigDecimal("price"));
                pricing.setCreatedAt(rs.getTimestamp("created_at"));
                pricing.setUpdatedAt(rs.getTimestamp("updated_at"));

                pricingList.add(pricing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pricingList;
    }

    // Update a pricing record
    public boolean updatePricing(Pricing pricing) {
        String sql = "UPDATE pricing SET pickup_location = ?, dropoff_location = ?, price = ?, updated_at = ? WHERE pricing_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pricing.getPickupLocation());
            stmt.setInt(2, pricing.getDropoffLocation());
            stmt.setBigDecimal(3, pricing.getPrice());
            stmt.setTimestamp(4, new Timestamp(pricing.getUpdatedAt().getTime()));
            stmt.setInt(5, pricing.getPricingId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a pricing record
    public boolean deletePricing(int pricingId) {
        String sql = "DELETE FROM pricing WHERE pricing_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pricingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
