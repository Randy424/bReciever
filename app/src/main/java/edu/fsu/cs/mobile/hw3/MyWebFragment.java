package edu.fsu.cs.mobile.hw3;


import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class MyWebFragment extends android.support.v4.app.Fragment {



    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    private OnFragmentInteractionListener mListener;
    public MyWebFragment() {
            // Required empty public constructor
        }


    public static MyWebFragment newInstance(String param1, String param2) {
        MyWebFragment fragment = new MyWebFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        WebView webview = (WebView) getActivity().findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

        Bundle bundle = this.getArguments();
        String website = bundle.getString("site");

        webview.loadUrl(website);

        return inflater.inflate(R.layout.fragment_my_web, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
