package com.gio.mscuentas.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;
import com.gio.mscuentas.Utils.KeyStoreHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class splash extends BaseFragmentListener {
    private static final String TAG = splash.class.getSimpleName();
    public static final long SPLASH=2000;
    private Boolean isLogout;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_splash, container, false);
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getContext());
        isLogout = sharedPreferences.getBoolean(getString(R.string.Logout), false);
        Log.e("UUD", String.valueOf(isLogout));
        // Inflate the layout for this fragment
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                String havePing = KeyStoreHelper.getInstance().readPin();
                if (havePing.equals(""))
                {
                    onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.LOGING, false, null);

                }
                if (!havePing.equals("") && isLogout== false)
                {
                    onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.PIN,false,null);
                }else if (!havePing.equals("") && isLogout== true) {
                    onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.LOGING, false, null);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,SPLASH);

        return v;
    }

}
