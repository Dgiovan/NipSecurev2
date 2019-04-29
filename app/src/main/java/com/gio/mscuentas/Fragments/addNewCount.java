package com.gio.mscuentas.Fragments;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;
import com.gio.mscuentas.Utils.ConexionSQLiteHelper;
import com.gio.mscuentas.Utils.Utilis;

/**
 * A simple {@link Fragment} subclass.
 */
public class addNewCount extends BaseFragmentListener implements View.OnClickListener
{
    private static final String TAG = addNewCount.class.getSimpleName();
    ImageView one,two,tree,four,five,six,seven;
    EditText name,password;
    Button registry;
    View v;
    Integer iconSelected=0;

    public addNewCount() {
        // Required empty public constructor
    }

    public static addNewCount newInstance(OnFragmentInteractionListener onFragmentInteractionListener){
       addNewCount fragment = new addNewCount();
       fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
       return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_add_new_count, container, false);
        one = v.findViewById(R.id.icoOne);
        one.setOnClickListener(this);
        two = v.findViewById(R.id.icoTwo);
        two.setOnClickListener(this);
        tree = v.findViewById(R.id.icoThree);
        tree.setOnClickListener(this);
        four = v.findViewById(R.id.iconFor);
        four.setOnClickListener(this);
        five = v.findViewById(R.id.icoFive);
        five.setOnClickListener(this);
        six = v.findViewById(R.id.icoSix);
        six.setOnClickListener(this);
        seven = v.findViewById(R.id.icoSeven);
        seven.setOnClickListener(this);

        name = v.findViewById(R.id.nameCountNewCount);
        password = v.findViewById(R.id.passworNewCount);

        registry = v.findViewById(R.id.buttonNewCount);
        registry.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.icoOne:
                iconSelected =1;
                one.setBackgroundColor(R.color.colorGreenlight);
            break;
            case R.id.icoTwo:
                iconSelected =2;
                two.setBackgroundColor(R.color.colorGreenlight);
            break;
            case R.id.icoThree:
                tree.setBackgroundColor(R.color.colorGreenlight);
                iconSelected =3;
            break;
            case R.id.iconFor:
                iconSelected =4;
                four.setBackgroundColor(R.color.colorGreenlight);
            break;
            case R.id.icoFive:
                iconSelected =5;
                five.setBackgroundColor(R.color.colorGreenlight);
            break;
            case R.id.icoSix:
                iconSelected =6;
                six.setBackgroundColor(R.color.colorGreenlight);
            break;
            case R.id.icoSeven:
                iconSelected =7;
                seven.setBackgroundColor(R.color.colorGreenlight);
            break;
            case R.id.buttonNewCount:
                postnewCount();
                break;
        }
    }

    private void postnewCount() {
        name.getText().toString();
        password.getText().toString();
        if (name.equals(""))
        {
         name.setError("Debes ingresar el nombre de la cuenta");
        }
        if (password.equals(""))
        {
            password.setError("Introduce la copntraseña de la cuenta");
        }
        if (iconSelected==0){
            Toast.makeText(getActivity(), "Por Favor selecciona un icono", Toast.LENGTH_SHORT).show();
        }
        if (!name.equals("") && !password.equals("") && iconSelected>1)
        {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getContext(),"bd_cuentas",null,1);

            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Utilis.FIELD_ICON, String.valueOf(iconSelected));
            values.put(Utilis.FIELD_NAME,name.getText().toString());
            values.put(Utilis.FIELD_PASSWORD,password.getText().toString());

            Long idResultante=db.insert(Utilis.CREAR_TABLA_CUENTAS,Utilis.FIELD_ICON,values);

            Toast.makeText(getContext(), "Cuenta Registrada", Toast.LENGTH_SHORT).show();

            db.close();


        }
    }
}
