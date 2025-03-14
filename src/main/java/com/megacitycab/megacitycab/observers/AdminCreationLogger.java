package com.megacitycab.megacitycab.observers;

/**
 * Logs admin creation events.
 */
public class AdminCreationLogger implements AdminObserver {
    @Override
    public void onAdminCreated(String username, String email) {
        System.out.println("New Admin Created: " + username + " (" + email + ")");
    }
}
