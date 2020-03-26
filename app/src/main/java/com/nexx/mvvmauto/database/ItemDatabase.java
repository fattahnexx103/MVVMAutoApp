package com.nexx.mvvmauto.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nexx.mvvmauto.model.Item;

//Room Database class
@Database(entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    //Database instance is Singleton since we need it through app lifecycle
    private static ItemDatabase databaseInstance;

    //create a Singleton instance of the database
    public static ItemDatabase getInstance(Context context){
        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDatabase.class,
                    "itemdatabase") //name of the database
                    .build();
        }
        return databaseInstance;
    }

    //provide the dao
    public abstract ItemDao itemDao();
}
