package com.nexx.mvvmauto.model;

//This is the model class
public class Item {

    private int id;
    private String name;
    private String phoneNumber;

    //Constructor for object creation
    public Item(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    //Getter Methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
