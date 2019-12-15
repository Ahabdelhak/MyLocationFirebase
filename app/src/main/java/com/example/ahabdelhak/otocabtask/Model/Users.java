package com.example.ahabdelhak.otocabtask.Model;

public class Users {

    String displayName;
    String password;
    public String email;
    String tipe;
    long createdAt;

    public Users() {
    }

    public Users(String displayName, String email, String password, long createdAt) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.tipe = "customer";
        this.createdAt = createdAt;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}