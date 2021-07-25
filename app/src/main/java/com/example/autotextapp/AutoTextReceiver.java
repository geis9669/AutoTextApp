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

        if(!(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")))
        {
            return;
        }

        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[0]);
            String address = smsMessage.getOriginatingAddress();


            if (inst.getAutoTextSwitchState()) {

                inst.sendText(address);

            }

            if (inst.getReadTextSwitchState()) {


                String smsMessageStr = "";
                for (int i = 0; i < sms.length; i++) {
                    smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
                    String smsBody = smsMessage.getMessageBody();
                    smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr += smsBody + "\n";
                }


                inst.handleSpeakText(smsMessageStr);
            }
        }

    }


}
