package edu.fsu.cs.mobile.hw3;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Toast;

import edu.fsu.cs.mobile.hw3.MyReceiver;



public class MainActivity extends AppCompatActivity
        implements UrlListFragment.OnFragmentInteractionListener, MyWebFragment.OnFragmentInteractionListener {

    public static final String SMS_RECEIVED_ACTION =
            "android.provider.Telephony.SMS_RECEIVED";
    public static Bundle myBundle = new Bundle();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("url"+1,"https://www.google.com" );
        editor.putString("url"+2,"https://www.netflix.com" );
        editor.putString("url"+3,"https://www.hulu.com" );
        editor.putInt("urlnumber", 3);
        editor.commit();


        MyWebFragment myWebFragment = new MyWebFragment();
        FragmentManager m = getSupportFragmentManager();
        m.beginTransaction()
        .replace(R.id.firstlayout,myWebFragment,myWebFragment.getTag())
                .commit();


        UrlListFragment UrlListFragment = new UrlListFragment();
        m.beginTransaction()
                .replace(R.id.secondlayout,UrlListFragment,UrlListFragment.getTag())
                .commit();


       int x = settings.getInt("urlnumber", 0);

        for(int i = 1;i <= x; i++)
        {Log.i("URLS!!!:",settings.getString("url"+i, "nothing was found"));}

        myBundle.putString("site", settings.getString("url"+x, "nothing was found"));

        IntentFilter filter = new IntentFilter(SMS_RECEIVED_ACTION);
        this.registerReceiver(receiver, filter);



    }


    @Override
    public void onFragmentInteraction(Uri Uri){

    }

    MyReceiver receiver = new MyReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {


                // TODO: Extract url from sms and add to UrlListFragment


            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

            for(int i = 0; i < messages.length; i++) {
                String messagebody = messages[i].getMessageBody();
                url += messagebody;

            }

        String c = url;

            Log.i("URLLLL!!!",url);

            SharedPreferences settings = context.getSharedPreferences("PREFS", 0);
            SharedPreferences.Editor editor = settings.edit();
            int unum = settings.getInt("urlnumber", 0);
            unum = 4;

            if(!URLUtil.isValidUrl(c)) {
                c = "https://" + c;
            }

            Log.i("!!!RECEIVER:", c);

            editor.putInt("urlnumber", unum);
            editor.putString("url"+unum, c);
            editor.commit();

            url = "";


            int x = settings.getInt("urlnumber", 0);
            for(int i = 1;i <= x; i++)
            {Log.i("URLS!!!:",settings.getString("url"+i, "nothing was found"));}


            UrlListFragment UrlListFragment = new UrlListFragment();
            FragmentManager m = getSupportFragmentManager();
            m.beginTransaction()
                    .replace(R.id.secondlayout, UrlListFragment,UrlListFragment.getTag())
                    .commit();

            myBundle.putString("site", settings.getString("url"+x, "nothing was found"));
            MyWebFragment myWebFragment = new MyWebFragment();
            m.beginTransaction()
             .replace(R.id.firstlayout,myWebFragment,myWebFragment.getTag())
                    .commit();
        }
    };
    IntentFilter filter = new IntentFilter();

}

