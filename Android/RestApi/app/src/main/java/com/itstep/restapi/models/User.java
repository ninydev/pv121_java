package com.itstep.restapi.models;

import android.database.Cursor;

public class User {

    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(Cursor query){
        this.name = query.getString(0);
        this.age = query.getInt(1);
    }

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
