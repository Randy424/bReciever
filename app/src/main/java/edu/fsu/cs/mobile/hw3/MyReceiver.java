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
import android.support.v4.app.FragmentTransaction;
import android.telephony.SmsMessage;
import android.util.Log;
import android.util.Patterns;
import android.webkit.URLUtil;

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



    }

