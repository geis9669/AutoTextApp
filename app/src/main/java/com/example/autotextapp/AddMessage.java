package com.example.autotextapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddMessage extends AppCompatActivity {
    public static String MESSAGEINPUT= "message";
    public static String PHONENUMS_LIST = "phone_num";

//    FloatingActionButton addPhoneNumButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        retrieveData();

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton addPhoneNumButton = findViewById(R.id.add_phone_num_button);
        addPhoneNumButton.setOnClickListener( (view)->
        {
            // code what the button will do
            // need to get the recycle view to display and show the phone numbers

        });


    }

    /**
     * retrieves data from the intents extras.
     * String message
     * ArrayList phone numbers. make use of setStringArrayListExtra for the intent.
     */
    private void retrieveData()
    {
        if(getIntent().hasExtra(MESSAGEINPUT) && getIntent().hasExtra(PHONENUMS_LIST))
        {
            messageText = getIntent().getStringExtra(MESSAGEINPUT);
            phoneNumbers = getIntent().getStringArrayListExtra(PHONENUMS_LIST);
        }else{
            messageText = "";
            phoneNumbers = new ArrayList<>();
        }
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