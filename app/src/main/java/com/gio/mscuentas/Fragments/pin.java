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
public class pin extends BaseFragmentListener {
    private static final String TAG = pin.class.getSimpleName();


    public pin() {
        // Required empty public constructor
    }

public static pin newInstance (OnFragmentInteractionListener onFragmentInteractionListener)
{
    pin fragment = new pin();
    fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
    return fragment;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pin, container, false);
    }

}
