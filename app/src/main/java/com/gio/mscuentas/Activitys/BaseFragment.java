package com.gio.mscuentas.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;

public class BaseFragment extends AppCompatActivity implements OnFragmentInteractionListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void changeFragment(FragmentType fragmentType, boolean addToBackStack, Bundle args, Fragment targetFragment)
    {

        Fragment fragment = new Fragment();

        switch (fragmentType)
        {
            case SPLASH:
                break;
            case LOGING:
                break;
            case REGISTRY:
                break;
            case PIN:
                break;
            case COUNTS:
                break;
            case ADDNEWCOUNT:
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


    @Override
    public void onFragmentInteractionChangeFragment(FragmentType fragmentType, boolean addToBackStack, @Nullable Bundle args) {
        changeFragment(fragmentType, addToBackStack, args, null);
    }
}
