package com.gio.mscuentas.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;
import com.gio.mscuentas.Utils.KeyStoreHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class loging extends BaseFragmentListener implements View.OnClickListener {
private static final String TAG = loging.class.getSimpleName();
View v;
Button loging;
TextView registry;
private  String userCredential;
EditText user,
         password;
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
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_loging, container, false);
        userCredential =KeyStoreHelper.getInstance().readToken();
        loging= v.findViewById(R.id.buttonLogin);
        registry = v.findViewById(R.id.tvregistryLoging);
        user = v.findViewById(R.id.userFielLoging);
        password = v.findViewById(R.id.passwordFieldLoging);
        registry.setOnClickListener(this);
        // Inflate the layout for this fragment

        loging.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonLogin:
                checkUserCredentials();
                break;
            case R.id.tvregistryLoging:
                onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.REGISTRY,false,null);
                break;
        }
    }

    private void checkUserCredentials() {

        String userCredentials = user.getText().toString() + password.getText().toString();
        if (!userCredential.equals(""))
        {
            if (userCredentials.equals(userCredential))
            {
                Bundle args = new Bundle();
                args.putBoolean(getString(R.string.key_is_unloking), checkForPin());
                Log.e(TAG,args.toString());
                LogoutOperations();
                onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.PIN,false, args);

            }else
            {
                showAlertDialog("Usuario No valido","Revisa tus datos","ok",null);
            }
        }else
            {
                showAlertDialog("llena todos los campos","Revisa tus datos","ok",null);
            }

    }
    private boolean checkForPin() {
        boolean keyChainHasPin = false;
        String savedPin = KeyStoreHelper.getInstance().readPin();
        if (!savedPin.equals("")) {
            keyChainHasPin = true;
        }
        return keyChainHasPin;
    }


    public void LogoutOperations() {
        SharedPreferences sharedPreferences =  PreferenceManager
                .getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.Logout),false);
        editor.apply();
    }
}
