package com.example.autotextapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;
    private ArrayList<String> messages;

    public ListAdapter(Context ct, ArrayList<String> messages)
    {
        context = ct;

        this.messages = messages;
        if(this.messages == null)
        {
            this.messages = new ArrayList<>();
        }

    }

    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_message, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount(){
        return messages.size();
    }

    public void add(String message) {
        messages.add(message);
    }

    public void clear(){
        messages.clear();
    }

    public void insert(String message, int index){
        messages.add(index, message);
    }

    public void remove(int index){
        messages.remove(index);
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {

        public ListViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
