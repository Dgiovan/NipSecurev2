package com.gio.mscuentas.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class splash extends BaseFragmentListener {
    private static final String TAG = splash.class.getSimpleName();
    private static final long SPLASH=2000;
    View v;

    public splash() {
        // Required empty public constructor
    }

public static splash newInstance(OnFragmentInteractionListener onFragmentInteractionListener)
{
    splash fragment = new splash();
    fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
    return fragment;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_splash, container, false);
        // Inflate the layout for this fragment
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

               onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.LOGING,false,null);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,SPLASH);
        return v;
    }

}
