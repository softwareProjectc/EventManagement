package com.software.eventmanagement.entities;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String password, String username) {
        this.password = password;
        this.username = username;
    }
    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
