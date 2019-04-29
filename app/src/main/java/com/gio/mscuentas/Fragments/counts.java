package com.gio.mscuentas.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gio.mscuentas.Adapters.countAdapter;
import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.Models.countModel;
import com.gio.mscuentas.R;
import com.gio.mscuentas.Utils.ConexionSQLiteHelper;
import com.gio.mscuentas.Utils.Utilis;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class counts extends BaseFragmentListener implements View.OnClickListener {

    private static final String TAG = counts.class.getSimpleName();
    View v;
    ImageView addnewcount;
    ArrayList<countModel> listCount;
    RecyclerView rvcCounts;
    ConexionSQLiteHelper conn;
    public counts() {
        // Required empty public constructor
    }

    public  static counts newInstance(OnFragmentInteractionListener onFragmentInteractionListener)
    {
        counts fragment = new counts();
        fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_counts, container, false);
        addnewcount = v.findViewById(R.id.newCount);
        addnewcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.ADDNEWCOUNT,true,null);
            }
        });
        rvcCounts  = v.findViewById(R.id.rcvCounts);


        conn = new ConexionSQLiteHelper(getContext(),"bd_cuentas",null,1);
        listCount= new ArrayList<>();

        rvcCounts.setLayoutManager(new LinearLayoutManager(getContext()));
        getCounts();

        countAdapter adapter = new countAdapter(getContext());
        rvcCounts.setAdapter(adapter);


        return v;
    }

    private void getCounts() {
        SQLiteDatabase db= conn.getReadableDatabase();

        countModel cuenta = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilis.TABLA_CUENTAS,null);

        while (cursor.moveToNext()){
            cuenta = new countModel();
            cuenta.setIcon(cursor.getInt(0));
            cuenta.setNameCount(cursor.getString(1));
            cuenta.setPassworCount(cursor.getString(2));

            listCount.add(cuenta);

        }

    }

    @Override
    public void onClick(View v) {

        switch(getId())
        {
            case R.id.newCount:
                onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.ADDNEWCOUNT,true,null);
                break;
        }
    }
}
