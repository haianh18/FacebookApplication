package com.example.facebookapplication;

import java.util.List;


public class UserData {
    private String username;
    private String password;
    private String name;

    public UserData(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
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
    public static List<UserData> getUserData() {
        return List.of(
            new UserData("admin", "123456", "Admin User"),
            new UserData("user1", "user1@123", "User One"),
            new UserData("user2", "user2@123", "User Two")
        );
    }
}
