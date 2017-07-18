package com.example.speats.Models;

/**
 * Created by Nicholas on 18/7/2017.
 */

public class User {

    private String name;
    private String uid;
    private String email;

    public User() {

    }

    public User(String name, String uid, String email) {
        this.name = name;
        this.uid = uid;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }
}
