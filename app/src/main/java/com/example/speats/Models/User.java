package com.example.speats.Models;

/**
 * Created by Nicholas on 18/7/2017.
 */

public class User {

    private String name;
    private String uid;

    public User() {

    }

    public User(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }
}
