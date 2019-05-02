package com.gio.mscuentas.Activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Fragments.loging;
import com.gio.mscuentas.R;

public class MainActivity extends BaseFragment   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.mainContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }

        changeFragment(FragmentType.SPLASH,false,null,null);

    }


}
