package com.example.autotextapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;

/**
 * This class is a receiver which will determine what was received then pass that to the main activity to
 * handle it.
 */
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

        if(!(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)))
        {
            return;
        }

        Bundle intentExtras = intent.getExtras();
        if(intentExtras != null)
        {
            //unpackage the message info to then send to mainActivity
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            SmsMessage[] smsMessages = new SmsMessage[sms.length];
            for (int i = 0; i < sms.length; i++)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) sms[0], Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
                }else{
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) sms[0]);
                }
            }//packages the object[] as a smsMessage[] for later use.

            inst.handleSMS_Message(smsMessages);
        }


//        if (intentExtras != null) {
//            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
//            SmsMessage smsMessage = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//
//            }else {
//                smsMessage = SmsMessage.createFromPdu((byte[]) sms[0]);
//            }
//            String address = smsMessage.getOriginatingAddress();
//
//            if (inst.getAutoTextSwitchState()) {
//                inst.sendText(address);
//            }
//
//            if (inst.getReadTextSwitchState()) {
//                String smsMessageStr = "";
//                for (int i = 0; i < sms.length; i++) {
//                    smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
//                    String smsBody = smsMessage.getMessageBody();
//                    smsMessageStr += "SMS From: " + address + "\n";
//                    smsMessageStr += smsBody + "\n";
//                }
//                inst.handleSpeakText(smsMessageStr);
//            }
//        }


    }


}
