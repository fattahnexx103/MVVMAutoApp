package com.nexx.mvvmauto.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nexx.mvvmauto.R;
import com.nexx.mvvmauto.model.Item;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public ListItemAdapter(List<Item> itemList){
        this.itemList = itemList;
    }

    //this method updates the list with the data
    public void updateList(Item newItem){
        itemList.add(newItem);
        notifyDataSetChanged();
    }

    //this method removes the top row from the recylcerview
    public void removeTopItem(){
        itemList.remove(0);
        notifyItemRemoved(0);
        notifyItemRangeChanged(0,itemList.size());

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView textView_itemId,textView_name,textView_phoneNumber;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_itemId = (TextView) itemView.findViewById(R.id.item_id);
            textView_name =  (TextView) itemView.findViewById(R.id.item_name);
            textView_phoneNumber = (TextView) itemView.findViewById(R.id.item_phoneNumber);
        }

        //this method sets the values in the UI with the value from the model
        void bind(Item item){
            textView_itemId.setText(String.valueOf(item.getId()));
            textView_name.setText(item.getName());
            textView_phoneNumber.setText(item.getPhoneNumber());
        }
    }
}
