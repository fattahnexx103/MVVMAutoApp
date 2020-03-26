package com.nexx.mvvmauto.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nexx.mvvmauto.model.Item;

@Dao //Room DAO Interface
public interface ItemDao {

    //all the database methods here

    //insert an item method
    @Insert(onConflict = OnConflictStrategy.REPLACE) //this is used to overwrite the values in the database orelse insertion doesnt happen
    void insertItem(Item item);

    //delete an item method
    @Query("DELETE FROM items_table WHERE id = :itemId")
    void deleteItem(int itemId); //take an item id and delete it.


}
