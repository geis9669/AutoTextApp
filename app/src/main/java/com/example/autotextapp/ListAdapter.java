package com.example.autotextapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;

    public ListAdapter(Context ct)
    {
        context = ct;

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
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public ListViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
