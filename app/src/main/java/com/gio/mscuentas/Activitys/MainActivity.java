package com.gio.mscuentas.Activitys;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.R;

public class MainActivity extends BaseFragment{

    boolean isLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
         isLogout = sharedPreferences.getBoolean(getString(R.string.Logout), false);
        Log.e("UUD", String.valueOf(isLogout));
        if (findViewById(R.id.mainContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }
        changeFragment(FragmentType.SPLASH,false,null,null);


    }
    @Override
    public void onResume() {
        super.onResume();
        if (isLogout==false){
            changeFragment(FragmentType.PIN,false,null,null);
        }

    }

}
