package com.example.autotextapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {
    private static MainActivity inst;
    public static MainActivity instance() {return inst;}
    private static final int REQUEST_PERMISSION = 1;
    private SwitchCompat autoTextSwitch;
    private SwitchCompat readTextSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inst = this;

        autoTextSwitch = findViewById(R.id.autoText);
        autoTextSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    getAllPermissions();
                }
            }
        });
        readTextSwitch = findViewById(R.id.readText);
        readTextSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {
                getPermissions(Manifest.permission.RECEIVE_SMS);
            }
//            AutoTextReceiver.instance().readTextStateChanged(isChecked);
        });

        getAllPermissions();
    }

    public boolean getAutoTextSwitchState()
    {
        return autoTextSwitch.isChecked();
    }

    public boolean getReadTextSwitchState()
    {
        return readTextSwitch.isChecked();
    }

    private void getAllPermissions()
    {
        getPermissions(Manifest.permission.RECEIVE_SMS);
        getPermissions(Manifest.permission.SEND_SMS);
        getPermissions(Manifest.permission.READ_PHONE_STATE);

    }

    /**
     *
     * @param permission a permission from the Manifest.permission class.
     */
    private void getPermissions(String permission)
    {
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                permission) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{permission}, REQUEST_PERMISSION);
        }
    }
}