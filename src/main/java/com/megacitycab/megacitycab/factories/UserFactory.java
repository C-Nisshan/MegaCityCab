package com.megacitycab.megacitycab.factories;

import com.megacitycab.megacitycab.models.User;

public class UserFactory {
    public static User createUser(int id, String username, String password,
                                  String name, String address, String nic,
                                  String phone, String email, String role) {
        return new User(id, username, password, name, address, nic, phone, email, role);
    }
}


