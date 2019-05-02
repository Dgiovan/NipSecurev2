package com.gio.mscuentas.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gio.mscuentas.Adapters.countAdapter;
import com.gio.mscuentas.ConexionSQLiteHelper;
import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.ItemClickListener;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.Entidades.countModel;
import com.gio.mscuentas.R;
import com.gio.mscuentas.Utils.Utilidades;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class counts extends BaseFragmentListener implements View.OnClickListener , ItemClickListener {

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


        try {
            conn = new ConexionSQLiteHelper(getContext(),"cuenta",null,1);
            listCount= new ArrayList<>();

            rvcCounts.setLayoutManager(new LinearLayoutManager(getContext()));
            getCounts();
            countAdapter adapter = new countAdapter(getContext(),listCount);
            adapter.setListener(this);
            adapter.setSpesifcListener(this);

            rvcCounts.setAdapter(adapter);
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }




        return v;
    }

    private void getCounts() {
        SQLiteDatabase db= conn.getReadableDatabase();

        countModel cuenta = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_CUENTA,null);

        while (cursor.moveToNext()){
            cuenta = new countModel();
            cuenta.setIcon(cursor.getString(0));
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

    @Override
    public void onItemClick(View view, int Position) {
        listCount.get(Position);
        String count = listCount.get(Position).getNameCount();
        Toast.makeText(getContext(), count, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSpesificItem(View view, int Position) {
        String item = listCount.get(Position).getPassworCount();
        Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
    }
}
