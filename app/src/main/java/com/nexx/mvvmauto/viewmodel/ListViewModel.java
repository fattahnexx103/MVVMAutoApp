package com.nexx.mvvmauto.viewmodel;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nexx.mvvmauto.model.Item;

import java.util.ArrayList;
import java.util.List;

// The viewModel Class which the UI observes
public class ListViewModel extends ViewModel {

    public MutableLiveData<Item> item = new MutableLiveData<>(); //We are using Mutable Live Data for observing

    private int idCounter = 0; //this is used to keep track of the current item and add that id to the id, name and phone

    public void setItems(){
        addSampleData();
    } //this method is accessed by MainActivity

    private void addSampleData(){

        List<Item> itemListArray = new ArrayList<>();

        Handler handler = new Handler(); //we create a handler to run this on the background

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                idCounter++; //increment id counter
                Item itemnew = new Item(idCounter,"Name is " + idCounter, "Phone Number is " + idCounter ); //create the item
                item.setValue(itemnew); // add the item to the livedata so now the livedata item is the item we created
                itemListArray.add(itemnew); //add the item we created to the list
                handler.postDelayed(this,5000); // post delay it for 5 seconds, you can play with this number

            }
        };

        handler.postDelayed(runnable,5000); //execute after 5 seconds
    }

}


