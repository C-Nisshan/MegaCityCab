package com.megacitycab.megacitycab.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer implementation to handle admin creation notifications.
 */
public class AdminCreationObserver {
    private static final List<AdminObserver> observers = new ArrayList<>();

    // Register observer
    public static void addObserver(AdminObserver observer) {
        observers.add(observer);
    }

    // Notify all observers
    public static void notifyAdminCreated(String username, String email) {
        for (AdminObserver observer : observers) {
            observer.onAdminCreated(username, email);
        }
    }
}
