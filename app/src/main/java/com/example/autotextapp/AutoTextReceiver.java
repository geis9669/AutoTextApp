package com.example.autotextapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;

import java.util.Locale;

public class AutoTextReceiver extends BroadcastReceiver {
    private static AutoTextReceiver inst;
    public static AutoTextReceiver instance() {return inst;}
    public static final String SMS_BUNDLE = "pdus";

    public AutoTextReceiver()
    {
        super();
        inst = this;

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        MainActivity inst = MainActivity.instance();
        if(inst.getAutoTextSwitchState())
        {

        }

        if(inst.getReadTextSwitchState())
        {

            Bundle intentExtras = intent.getExtras();
            if(intentExtras != null){
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
                String smsMessageStr = "";
                for(int i=0; i<sms.length; i++){
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
                    String smsBody = smsMessage.getMessageBody();
                    String address = smsMessage.getOriginatingAddress();
                    smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr += smsBody +"\n";
                }


                inst.handleSpeakText(smsMessageStr);
            }
        }


    }

}
