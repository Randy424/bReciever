package edu.fsu.cs.mobile.hw3;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity
        implements listblank.OnFragmentInteractionListener, MyWebFragment.OnFragmentInteractionListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyWebFragment myWebFragment = new MyWebFragment();
        FragmentManager m = getSupportFragmentManager();
        m.beginTransaction()
        .replace(R.id.firstlayout,myWebFragment,myWebFragment.getTag())
                .commit();


        listblank listblank = new listblank();
        m.beginTransaction()
                .replace(R.id.secondlayout,listblank,listblank.getTag())
                .commit();

    }

    @Override
    public void onFragmentInteraction(Uri Uri){

    }
}

