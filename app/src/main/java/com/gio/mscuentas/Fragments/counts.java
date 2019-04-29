package com.gio.mscuentas.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class counts extends BaseFragmentListener {

    private static final String TAG = counts.class.getSimpleName();
    View v;
    public counts() {
        // Required empty public constructor
    }

    public  static counts newInstance(OnFragmentInteractionListener onFragmentInteractionListener)
    {
        counts fragment = new counts();
        fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_counts, container, false);
        // Inflate the layout for this fragment
        return v;
    }

}
