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
public class registry extends BaseFragmentListener {

    private static final String TAG = registry.class.getSimpleName();
    View v;
    public registry() {
        // Required empty public constructor
    }

public static  registry newInstance(OnFragmentInteractionListener onFragmentInteractionListener)
{
    registry fragment = new registry();
    fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
    return  fragment;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_registry, container, false);
        // Inflate the layout for this fragment
        return v;
    }

}
