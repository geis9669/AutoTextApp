package com.example.autotextapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

public class AddMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        // put the code for the item menu and return.
        if(item.getItemId() == R.id.action_save)
        {

            // save the info.

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}