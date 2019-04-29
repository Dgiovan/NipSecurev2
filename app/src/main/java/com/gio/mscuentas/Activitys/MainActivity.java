package com.gio.mscuentas.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.R;

public class MainActivity extends BaseFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(FragmentType.SPLASH,false,null,null);
    }
}
