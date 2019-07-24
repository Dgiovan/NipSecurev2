package com.gio.mscuentas.Fragments;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gio.mscuentas.ConexionSQLiteHelper;
import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;
import com.gio.mscuentas.Utils.Utilidades;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class addNewCount extends BaseFragmentListener implements View.OnClickListener
{
    private static final String TAG = addNewCount.class.getSimpleName();
    ImageView one,two,tree,four,five,six,seven,eigth,nine,back;
    EditText name,password;
    Button registry;
    View v;
    String iconSelected="";


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
        eigth = v.findViewById(R.id.icoEigth);
        eigth.setOnClickListener(this);
        nine = v.findViewById(R.id.icoNine);
        nine.setOnClickListener(this);

        back = v.findViewById(R.id.icback);
        back.setOnClickListener(this);

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
                iconSelected ="1";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
            break;
            case R.id.icoTwo:
                iconSelected ="2";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
            break;
            case R.id.icoThree:
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                iconSelected ="3";
            break;
            case R.id.iconFor:
                iconSelected ="4";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
            break;
            case R.id.icoFive:
                iconSelected ="5";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
            break;
            case R.id.icoSix:
                iconSelected ="6";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
            break;
            case R.id.icoSeven:
                iconSelected ="7";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
            break;
            case R.id.icoEigth:
                iconSelected ="8";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                break;
            case R.id.icoNine:
                iconSelected ="9";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
                break;

            case R.id.buttonNewCount:
                postnewCount();
                break;

            case R.id.icback:
                onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.COUNTS, false, null);
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
            password.setError("Introduce la contraseña de la cuenta");
        }
        if (iconSelected.equals("")){
            Toast.makeText(getActivity(), "Por Favor selecciona un icono", Toast.LENGTH_SHORT).show();
        }
        if (!name.equals("") && !password.equals("") && !iconSelected.equals(""))
        {

            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(),"cuenta",null,1);
            Random aleatorio =new Random(System.currentTimeMillis());
            int intAleatorio = aleatorio.nextInt(900);
            aleatorio.setSeed(System.currentTimeMillis());

            String keyCountType = String.valueOf(intAleatorio);

            SQLiteDatabase db= conn.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Utilidades.FIELD_ICON,iconSelected);
            values.put(Utilidades.FIELD_NAME,name.getText().toString()+"#a!%bc"+keyCountType);
            values.put(Utilidades.FIELD_PASSWORD,password.getText().toString());

            Long idResult = db.insert(Utilidades.TABLA_CUENTA,Utilidades.FIELD_ICON,values);
            Toast.makeText(getActivity(), "Cuenta Registrada" +idResult , Toast.LENGTH_SHORT).show();
            db.close();
            onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.COUNTS, false, null);

        }
    }


}
