package com.example.autotextapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
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

    public void sendText(String destinationAddress)
    {
        //get text to send for the sms message.
        String smsMessage = smsEditText.getText().toString();

        //Set the service center address if needed, otherwise null.
        String scAddress = null;

        // Set pending intents to broadcast
        // when message sent and when delivered, or set to null;
        PendingIntent sentIntent = null, deliveryIntent = null;

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(destinationAddress, scAddress, smsMessage,
                sentIntent, deliveryIntent);
        Toast.makeText(this, "messageSent", Toast.LENGTH_SHORT).show();

        Build.getSerial();


//        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//        smsIntent.setData(Uri.parse(destinationAddress));
//        smsIntent.putExtra("sms_body", smsMessage);
//        smsIntent.setType("vnd.android-dir/mms-sms");
//        if(smsIntent.resolveActivity(getPackageManager()) != null){
//            startActivity(smsIntent);
//        } else {
//            Toast.makeText(this, "Can't resolve app for ACTION_VIEW Intent", Toast.LENGTH_SHORT).show();
////            startActivity(smsIntent);
//        }

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