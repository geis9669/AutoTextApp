package com.example.autotextapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autotextapp.data.PhoneMessageSendList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static MainActivity inst;
    public static MainActivity instance() {return inst;}
    private static final int REQUEST_PERMISSION = 1;
    private SwitchCompat autoTextSwitch;
    private SwitchCompat readTextSwitch;
    private TextToSpeech readText;
    private FloatingActionButton addMessageButton;

    private EditText smsEditText;
    private RecyclerView smsMultipleMessageView;
    private ListAdapter smsAdapter;

    PhoneMessageSendList phoneMessageSendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inst = this;

        autoTextSwitch = findViewById(R.id.autoText);
        autoTextSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getAllNeededPermissions();
                }
            }
        });
        readTextSwitch = findViewById(R.id.readText);
        readTextSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                getPermission(Manifest.permission.RECEIVE_SMS);
            }

        });

        readText = new TextToSpeech(MainActivity.instance().getApplicationContext(), status -> {
            if (status != TextToSpeech.ERROR) {
                readText.setLanguage(Locale.US);
                // may need the language to be passed in.
            }
        });
        readText.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {

            }
            @Override
            public void onDone(String utteranceId) {

            }
            @Override
            public void onError(String utteranceId) {
                readText.stop();
                Toast.makeText(inst, "", Toast.LENGTH_LONG).show();
            }
        });

        addMessageButton = findViewById(R.id.addMessageButton);
        addMessageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMessage.class);
                startActivity(intent);
//                intent.putExtra("message","");
//                intent.putStringArrayListExtra();
            }
        });


        smsMultipleMessageView = findViewById(R.id.textMessageList);
        smsAdapter = new ListAdapter(this, new ArrayList<>());
        smsMultipleMessageView.setAdapter(smsAdapter);
        smsMultipleMessageView.setLayoutManager(new LinearLayoutManager(this));


        smsEditText = findViewById(R.id.textView);

        //may need to load the values from memory
        phoneMessageSendList = new PhoneMessageSendList();

        getAllNeededPermissions();
    }

    public void handleSMS_MessageReceived(SmsMessage[] smsMessages)
    {
        if(smsMessages.length<1)
        {
            return;
        }

        //assume that the address is the same for the message list.
        String address = smsMessages[0].getOriginatingAddress();

        if(getAutoTextSwitchState())
        {
            sendText(address);
        }
        if(getReadTextSwitchState())
        {
            String messageBody = "Message from "+address;
            for(int i = 0; i<smsMessages.length; i++)
            {
                messageBody += smsMessages[i].getMessageBody();
            }
            handleSpeakText(messageBody);
        }
    }

    public void handleSpeakText(String message)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            readText.speak(message, TextToSpeech.QUEUE_ADD, null, "MainActivity.handleSpeakText");
        }else {
            readText.speak(message, TextToSpeech.QUEUE_ADD, null);
        }
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

    private void getAllNeededPermissions()
    {
        getPermission(Manifest.permission.RECEIVE_SMS);
        getPermission(Manifest.permission.SEND_SMS);
        getPermission(Manifest.permission.READ_PHONE_STATE);

    }

    /**
     *
     * @param permission a permission from the Manifest.permission class.
     */
    private void getPermission(String permission)
    {
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                permission) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{permission}, REQUEST_PERMISSION);
        }
    }
}