package com.gio.mscuentas.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Fragments.addNewCount;
import com.gio.mscuentas.Fragments.counts;
import com.gio.mscuentas.Fragments.loging;
import com.gio.mscuentas.Fragments.pin;
import com.gio.mscuentas.Fragments.registry;
import com.gio.mscuentas.Fragments.splash;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;

public class BaseFragment extends AppCompatActivity implements OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void changeFragment(FragmentType fragmentType, boolean addToBackStack, Bundle args, Fragment targetFragment)
    {

        Fragment fragment = new Fragment();

        switch (fragmentType)
        {
            case SPLASH:
                splash SPLASHFRAGMENT = splash.newInstance(this);
                SPLASHFRAGMENT.setArguments(args);
                fragment = SPLASHFRAGMENT;
                break;
            case LOGING:
                loging LOGINGFRAGMENT = loging.newInstance(this);
                LOGINGFRAGMENT.setArguments(args);
                fragment = LOGINGFRAGMENT;
                break;
            case REGISTRY:
                registry REGISTRYFRAGMENT = registry.newInstance(this);
                REGISTRYFRAGMENT.setArguments(args);
                fragment = REGISTRYFRAGMENT;
                break;
            case PIN:
                pin PINFRAGMENT = pin.newInstance(this);
                PINFRAGMENT.setArguments(args);
                fragment = PINFRAGMENT;
                break;
            case COUNTS:
                counts COUNTSFRAGMENT = counts.newInstance(this);
                COUNTSFRAGMENT.setArguments(args);
                fragment = COUNTSFRAGMENT;
                break;
            case ADDNEWCOUNT:
                addNewCount ADDCOUNTFRAGMENT = addNewCount.newInstance(this);
                ADDCOUNTFRAGMENT.setArguments(args);
                fragment = ADDCOUNTFRAGMENT;
                break;

        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentType.name());
        }
        fragmentTransaction.replace(R.id.mainContainer, fragment).commitAllowingStateLoss();
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onFragmentInteractionChangeFragment(FragmentType fragmentType, boolean addToBackStack, Bundle args) {
        changeFragment(fragmentType, addToBackStack, args, null);
    }

    @Override
    public void onHideKeyboard() {
        hideKeyboard();
    }
}
