package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.LocationDAO;
import com.megacitycab.megacitycab.dao.PricingDAO;
import com.megacitycab.megacitycab.models.Location;
import com.megacitycab.megacitycab.models.Pricing;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class PricingService {
    private final PricingDAO pricingDAO;

    public PricingService(Connection connection) {
        this.pricingDAO = new PricingDAO(connection);
    }

    public List<Pricing> getAllPricings() throws Exception {
        return pricingDAO.getAllPricings();
    }

    public boolean savePricing(HttpServletRequest request) throws Exception {
        int pickupLocation = Integer.parseInt(request.getParameter("pickupLocation"));
        int dropoffLocation = Integer.parseInt(request.getParameter("dropoffLocation"));
        double price = Double.parseDouble(request.getParameter("price"));

        Pricing pricing = new Pricing();

        pricing.setPickupLocation(pickupLocation);
        pricing.setDropoffLocation(dropoffLocation);
        pricing.setPrice(BigDecimal.valueOf(price));

        Date now = new Date();
        pricing.setCreatedAt(now);
        pricing.setUpdatedAt(now);

        pricingDAO.createPricing(pricing);
        return true;
    }

    public boolean editPricing(HttpServletRequest request) throws Exception {
        int pricingId = Integer.parseInt(request.getParameter("pricingId"));
        int pickupLocation = Integer.parseInt(request.getParameter("pickupLocation"));
        int dropoffLocation = Integer.parseInt(request.getParameter("dropoffLocation"));
        double price = Double.parseDouble(request.getParameter("price"));

        Pricing pricing = new Pricing();

        pricing.setPricingId(pricingId);
        pricing.setPickupLocation(pickupLocation);
        pricing.setDropoffLocation(dropoffLocation);
        pricing.setPrice(BigDecimal.valueOf(price));

        Date now = new Date();
        pricing.setUpdatedAt(now);
        pricingDAO.updatePricing(pricing);

        return true;
    }

    public boolean removePricingById(int pricingId) {
        return pricingDAO.deletePricing(pricingId);
    }

}
