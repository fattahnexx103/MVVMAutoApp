package com.nexx.mvvmauto.model;

//This is the model class

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table") //this is the database table
public class Item {

    //id is primary key
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "item_name")
    private String name;

    @ColumnInfo(name = "item_phoneNumber")
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
