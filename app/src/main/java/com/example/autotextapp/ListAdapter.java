package com.example.autotextapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        holder.messageView.setText(messages.get(position));
        holder.mainLayout.setOnClickListener( new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(context, AddMessage.class);
                intent.putExtra("message", messages.get(position));
                intent.putExtra("index", position);
                context.startActivity(intent);

            }
        });
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

        private TextView messageView;
        private ConstraintLayout mainLayout;
        public ListViewHolder(@NonNull View itemView){
            super(itemView);
            mainLayout = itemView.findViewById(R.id.listLayout);
            messageView = itemView.findViewById(R.id.textViewCell);
        }
    }
}
