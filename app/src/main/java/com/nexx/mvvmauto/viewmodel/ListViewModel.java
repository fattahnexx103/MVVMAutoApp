package com.nexx.mvvmauto.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nexx.mvvmauto.database.ItemDao;
import com.nexx.mvvmauto.database.ItemDatabase;
import com.nexx.mvvmauto.model.Item;


// The viewModel Class which the UI observes
public class ListViewModel extends AndroidViewModel { //we have to extend AndroidViewModel when we are using the context in this class.

    public MutableLiveData<Item> itemLiveData = new MutableLiveData<>(); //We are using Mutable Live Data for observing
    public MutableLiveData<Integer> deletedPositionLiveData = new MutableLiveData<>(); //we now observe this livedata to see if data is being deleted from database

    private int idCounter = 0; //this is used to keep track of the current item and add that id to the id, name and phone
    private int removeCounter = 0;

    private AsyncTask<Item,Void,Void> insertTask; //the asynctask for the insert operation in the db


    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public void setItems(){
        addSampleData();
    } //this method is accessed by MainActivity

    private void addSampleData(){

        Handler handler = new Handler(); //we create a handler to run this on the background

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                idCounter++; //increment id counter
                Item itemnew = new Item(idCounter,"Name is " + idCounter, "Phone Number is " + idCounter ); //create the item
                insertTask = new InsertItemAsyncTask(); //use the asynctask here insert into the db
                insertTask.execute(itemnew);
                handler.postDelayed(this,5000); // post delay it for 5 seconds, you can play with this number

            }
        };

        handler.postDelayed(runnable,5000); //execute after 5 seconds
    }

    //asynctask created for insert operation since db taks are done in backend
    private class InsertItemAsyncTask extends AsyncTask<Item, Void, Void> {

        //this values are used to update the live data in post execute
        private Item item; //this keeps track of the item for postExecution method
        private int deletedPosition; //this keeps track to update the live data

        @Override
        protected Void doInBackground(Item... items) {
            ItemDao dao = ItemDatabase.getInstance(getApplication()).itemDao();
            if(idCounter > 10){ //we do the logic here for having more than 10 items in list
                removeCounter++; //increment remove counter
                dao.deleteItem(removeCounter); // delete the item from database
                deletedPosition = removeCounter; // update the position
            }
            dao.insertItem(items[0]); //insert the item to the database
            item = items[0]; //update the item object above
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            itemLiveData.setValue(item); // add the item to the livedata so now the livedata item is the item we created
            deletedPositionLiveData.setValue(deletedPosition); //add the deletedposition to the livedata
        }

    }

}


