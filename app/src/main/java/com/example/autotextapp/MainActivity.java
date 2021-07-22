package com.example.autotextapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static MainActivity inst;
    public static MainActivity instance() {return inst;}
    private static final int REQUEST_PERMISSION = 1;
    private SwitchCompat autoTextSwitch;
    private SwitchCompat readTextSwitch;
    private TextToSpeech readText;

    private EditText smsEditText;

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

        });

        readText = new TextToSpeech(MainActivity.instance().getApplicationContext(), status ->{
            if(status != TextToSpeech.ERROR)
            {
                readText.setLanguage(Locale.US);
                // may need the language to be passed in.
            }
        });

        smsEditText = findViewById(R.id.textView);



        getAllPermissions();
    }

    public void handleSpeakText(String message)
    {
        readText.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void sendText()
    {

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