package com.gio.mscuentas.Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;
import com.gio.mscuentas.Utils.KeyStoreHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class registry extends BaseFragmentListener implements View.OnClickListener {

    private static final String TAG = registry.class.getSimpleName();
    View v;
    private EditText name,
                     password,
                     checkPassword;
    Button adduser;
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
        name= v.findViewById(R.id.userFieldRegistry);
        password= v.findViewById(R.id.passwordFieldRegistry);
        checkPassword= v.findViewById(R.id.confirmFieldRegistry);
        adduser = v.findViewById(R.id.buttonRegistry);

        adduser.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        addOnliUser();
    }

    private void addOnliUser() {

        if (name.getText().toString().equals(""))
        {
            name.setError("Por favorllena este campo");
        }else if(password.getText().toString().equals(""))
         {
          password.setError("Por favor introduce una contraseña");
         }else if (!password.getText().toString().equals(checkPassword.getText().toString()))
         {
             Log.e(TAG,checkPassword.getText().toString()+"aa"+password.getText().toString());
             checkPassword.setError("Las Contraseñas no coinsiden");
         }else
        if (!name.getText().toString().equals("")&&password.getText().toString().equals(checkPassword.getText().toString()))
        {

            KeyStoreHelper.getInstance().saveToken(name.getText().toString()+checkPassword.getText().toString());
            onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.LOGING,false,null);
        }

    }
}
