package com.gio.mscuentas.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v13.view.inputmethod.EditorInfoCompat;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gio.mscuentas.Adapters.RecyclerItemTouchHelper;
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
public class counts extends BaseFragmentListener implements View.OnClickListener , ItemClickListener , RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private static final String TAG = counts.class.getSimpleName();
    View v;
    ImageView addnewcount,logout;
    ArrayList<countModel> listCount;
    RecyclerView rvcCounts;
    ConexionSQLiteHelper conn;
    countAdapter adapter;
    AlertDialog alertDialog;
    int showhiden=0;
    boolean isLogout;
    LinearLayout Layoutcoun;
    public counts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getContext());
        isLogout = sharedPreferences.getBoolean(getString(R.string.Logout), false);
        setHasOptionsMenu(true);
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
        Layoutcoun = v.findViewById(R.id.imageNewuser);
        Layoutcoun.setOnClickListener(this);
        addnewcount.setOnClickListener(this);
        logout = v.findViewById(R.id.logoutCount);
        logout.setOnClickListener(this);

        rvcCounts  = v.findViewById(R.id.rcvCounts);

        initRecycler();





        return v;
    }

    private void initRecycler() {
        try {

            conn = new ConexionSQLiteHelper(getContext(),"cuenta",null,1);
            listCount= new ArrayList<>();
            rvcCounts.setLayoutManager(new LinearLayoutManager(getContext()));
            getCounts();
            adapter = new countAdapter(getContext(),listCount);
            adapter.setListener(this);
            adapter.setSpesifcListener(this);
            rvcCounts.setAdapter(adapter);


            ItemTouchHelper.SimpleCallback simpleCallback =
                    new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,counts.this);

            new ItemTouchHelper(simpleCallback).attachToRecyclerView(rvcCounts);

            getVisibilitys();
        }catch (Exception e){
            Log.e(TAG,e.toString());

        }
    }

    private void getVisibilitys() {


        if (listCount ==null || listCount.isEmpty() )
        {
            Layoutcoun.setVisibility(View.VISIBLE);
        }else
        {
            rvcCounts.setVisibility(View.VISIBLE);
            Layoutcoun.setVisibility(View.GONE);
        }
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.shearchcounts, menu);
        super.onCreateOptionsMenu(menu,inflater);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView  searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.imageNewuser:
            case R.id.newCount:
                onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.ADDNEWCOUNT,true,null);
                break;
            case R.id.logoutCount:
                if (alertDialog == null) {
                    alertDialog = new AlertDialog.Builder(getActivity() ).create();
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle("msCuentas");
                    alertDialog.setMessage("¿Estás seguro de querer cerrar sesión?");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cerrar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    LogoutOperations();
                                    dialog.dismiss();
                                    alertDialog = null;
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    alertDialog = null;
                                }
                            });
                    alertDialog.show();
                }

                break;
        }
    }


    public void changeItem(int position,String text)
    {
        listCount.get(position).ChangeTexts(text);
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onItemClick(View view, int Position) {
        listCount.get(Position);
        String count = listCount.get(Position).getNameCount();
        Toast.makeText(getContext(), count, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSpesificItem(View view, int Position) {
        Log.e(TAG,String.valueOf(showhiden));
        if (showhiden==0)
        {
            changeItem(Position,listCount.get(Position).getPassworCount());
            countModel model = new countModel();
            model.setHidenPasword(listCount.get(Position).getPassworCount());
            showhiden += 1;
        }else  if (showhiden!=0)
        {
            changeItem(Position,"***********");
            countModel model = new countModel();
            model.setHidenPasword("***********");
            showhiden = 0;
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        getVisibilitys();


    }
    public void LogoutOperations() {
        SharedPreferences sharedPreferences =  PreferenceManager
                .getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.Logout),true);
        editor.apply();
        onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.LOGING,false,null);
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        adapter.removeItem(viewHolder.getAdapterPosition());

        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros ={listCount.get(position).getNameCount()};
        db.delete(Utilidades.TABLA_CUENTA,Utilidades.FIELD_NAME+"=?",parametros);
        //Toast.makeText(context, listaUsuario.get(position).getNameCount(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "Cuenta Eliminada"  , Toast.LENGTH_SHORT).show();
        db.close();
        initRecycler();



    }
}
