package com.example.autotextapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AddMessage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_message_toolbar, menu);
        return true;
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