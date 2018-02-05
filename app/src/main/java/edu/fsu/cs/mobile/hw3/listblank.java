package edu.fsu.cs.mobile.hw3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
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


    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences pref = this.getActivity().getSharedPreferences("PREFS",0);

        ArrayList <String> l = new ArrayList<>();

        SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = settings.edit();
       int i = ( settings.getInt("urlnumber", 0));

        for(int x = 1;x <= i; x++)
        {
            l.add(settings.getString("url"+ x, "couldn't find string"));}




        View v = inflater.inflate(R.layout.fragment_listblank, container, false);


        ListView listView = (ListView) v.findViewById(R.id.listView);

        ArrayAdapter <String>  Adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
             l);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String website = ((TextView)view).getText().toString();

                MainActivity.myBundle.putString("site", website);

                Fragment someFragment = new MyWebFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.web, someFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
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
