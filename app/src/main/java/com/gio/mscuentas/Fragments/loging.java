package com.gio.mscuentas.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class loging extends BaseFragmentListener {
private static final String TAG = loging.class.getSimpleName();
View v;
Button loging;
    public loging() {
        // Required empty public constructor
    }

public static loging newInstance(OnFragmentInteractionListener onFragmentInteractionListener)
{
    loging fragment = new loging();
    fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
    return  fragment;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_loging, container, false);
        loging= v.findViewById(R.id.buttonLogin);
        // Inflate the layout for this fragment

        loging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.COUNTS,false,null);
            }
        });
        return v;
    }

}
