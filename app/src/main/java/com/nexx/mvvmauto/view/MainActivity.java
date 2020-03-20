package com.nexx.mvvmauto.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nexx.mvvmauto.R;
import com.nexx.mvvmauto.model.Item;
import com.nexx.mvvmauto.viewmodel.ListViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView_id,textView_name,textView_phoneNumber;

    private ListViewModel viewModel;
    private ListItemAdapter listItemAdapter = new ListItemAdapter(new ArrayList<Item>());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        textView_id = (TextView) findViewById(R.id.item_id);
        textView_name =  (TextView) findViewById(R.id.item_name);
        textView_phoneNumber = (TextView) findViewById(R.id.item_phoneNumber);

        //instantiate the view model class
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.setItems(); // call the method to set the items on the list

        //configure recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listItemAdapter);

        //sample toast message to tell user to wait. Could have used progressbar but for sake of time and complexity used this.
        Toast.makeText(this, "Please wait upto 5 seconds", Toast.LENGTH_LONG).show();

        observeViewModel();

    }

    //this method observes the viewmodel
    private void observeViewModel(){

        viewModel.item.observe(this, itemModels -> {
            if(itemModels != null) {
                recyclerView.setVisibility(View.VISIBLE);
                listItemAdapter.updateList(itemModels); //update the list
                if(itemModels.getId() > 10){ //after 10 entries
                    listItemAdapter.removeTopItem(); // call the removeItem method to
                }
            }
        });

    }
}
