package com.megacitycab.megacitycab.observers;

/**
 * Observer interface for admin-related notifications.
 */
public interface AdminObserver {
    void onAdminCreated(String username, String email);
}

