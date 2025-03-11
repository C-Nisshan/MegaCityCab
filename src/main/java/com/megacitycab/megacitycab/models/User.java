package com.megacitycab.megacitycab.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String nic;
    private String telephone;
    private String email;
    private String role;
    private String createdAt;
    private String updatedAt;

    // No-argument constructor (added)
    public User() {
    }

    public User(int id, String username, String password, String name, String address, String nic, String telephone, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
    }

    public User(int id, String username, String name, String address, String nic, String telephone, String email, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
    }

    public User(int id, String name, String nic, String telephone, String email) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.telephone = telephone;
        this.email = email;
    }


    public User(int id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {  // Add setter for id
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNic() {
        return nic;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
