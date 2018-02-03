package edu.fsu.cs.mobile.hw3;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsMessage;

public class MyReceiver extends BroadcastReceiver{

    private static final String TAG = MyReceiver.class.getCanonicalName();
    static String url = "";

         public MyReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // This method is called when the BroadcastReceiver is receiving
            // an SMS broadcast.

            // TODO: Extract url from sms and add to UrlListFragment
        }
        @TargetApi(Build.VERSION_CODES.M)
        public  String extractUrlFromIntent(Intent intent) {
            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

            for(int i = 0; i < messages.length; i++) {
                String messagebody = messages[i].getMessageBody();
                url += messagebody;

            }
            return url;
        }

    public static SharedPreferences getSharedPreferences (Context ctxt) {

        SharedPreferences settings = ctxt.getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("url", url);
        editor.commit();
        return ctxt.getSharedPreferences("FILE", 0);


    }


    }

