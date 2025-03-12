package com.megacitycab.megacitycab.services;

import com.megacitycab.megacitycab.dao.CarDAO;
import com.megacitycab.megacitycab.dao.LocationDAO;
import com.megacitycab.megacitycab.models.Car;
import com.megacitycab.megacitycab.models.Driver;
import com.megacitycab.megacitycab.models.Location;
import com.megacitycab.megacitycab.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class LocationService {
    private final LocationDAO locationDAO;

    public LocationService(Connection connection) {
        this.locationDAO = new LocationDAO(connection);
    }

    public List<Location> getAllLocations() throws Exception {
        return locationDAO.getAllLocations();
    }

    public boolean saveLocation(HttpServletRequest request) throws Exception {
        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));

        Date now = new Date();
        location.setCreatedAt(now);
        location.setUpdatedAt(now);

        locationDAO.createLocation(location);
        return true;
    }

    public boolean editLocation(HttpServletRequest request) throws Exception {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String locationName = request.getParameter("locationName");

        Location location = new Location();
        location.setLocationId(locationId);
        location.setLocationName(locationName);
        location.setUpdatedAt(new Date());

        return locationDAO.updateLocation(location);
    }

    public boolean removeLocation(int locationId) {
        return locationDAO.deleteLocation(locationId);
    }
}
