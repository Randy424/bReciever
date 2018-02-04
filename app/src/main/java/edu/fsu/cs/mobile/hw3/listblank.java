package edu.fsu.cs.mobile.hw3;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link listblank.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link listblank#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listblank extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public listblank() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static listblank newInstance(String param1, String param2) {
        listblank fragment = new listblank();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        SharedPreferences settings = getActivity().getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("url"+1,"https://www.google.com" );
        editor.putString("url"+2,"https://www.netflix.com" );
        editor.putString("url"+3,"https://www.hulu.com" );
        editor.putInt("urlnumber", 3);
        editor.commit();
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<String> Array = new ArrayList<String>();
        Array.add("https://www.google.com");
        Array.add("https://www.netflix.com");
        Array.add("https://www.hulu.com");

        SharedPreferences pref = this.getActivity().getSharedPreferences("PREFS",0);



        View v = inflater.inflate(R.layout.fragment_listblank, container, false);


        ListView listView = (ListView) v.findViewById(R.id.listView);

        ArrayAdapter <String>  Adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
             getResources().getStringArray(R.array.urls));

        listView.setAdapter(Adapter);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
