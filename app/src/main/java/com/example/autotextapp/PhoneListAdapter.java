package com.example.autotextapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneListViewHolder>{

    private Context context;
    private ArrayList<String> phoneList;
    public PhoneListAdapter(Context context, ArrayList<String> phoneList){
        this.context = context;
        this.phoneList = phoneList;
        if(this.phoneList==null)
        {
            this.phoneList= new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public PhoneListAdapter.PhoneListViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_message, parent, false);
        return new PhoneListAdapter.PhoneListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneListAdapter.PhoneListViewHolder holder, int position) {
        holder.messageView.setText(phoneList.get(position));
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }

    public void add(String message) {
        phoneList.add(message);
    }

    public void clear(){
        phoneList.clear();
    }

    public void insert(String message, int index){
        phoneList.add(index, message);
    }

    public void remove(int index){
        phoneList.remove(index);
    }

    public class PhoneListViewHolder extends RecyclerView.ViewHolder {

        private TextView messageView;
        private ConstraintLayout mainLayout;
        public PhoneListViewHolder(@NonNull View itemView){
            super(itemView);
//            mainLayout = itemView.findViewById(R.id.listLayout);
//            messageView = itemView.findViewById(R.id.textViewCell);
        }
    }
}
