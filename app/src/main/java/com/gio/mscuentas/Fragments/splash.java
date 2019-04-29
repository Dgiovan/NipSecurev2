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
public class splash extends BaseFragmentListener {
    private static final String TAG = splash.class.getSimpleName();


    public splash() {
        // Required empty public constructor
    }

public splash newInstance(OnFragmentInteractionListener onFragmentInteractionListener)
{
    splash fragment = new splash();
    fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
    return fragment;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

}
