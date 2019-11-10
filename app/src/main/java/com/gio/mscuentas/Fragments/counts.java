package com.gio.mscuentas.Fragments;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
    String nameCount;
    LinearLayout Layoutcoun;



    private EditText user;
    private EditText pass;
    ImageView one,two,tree,four,five,six,seven,eigth,nine;
    public String iconSelected="";
    String names;
    String passw;


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
            adapter.setOnEditItem(this);
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
                onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.ADDNEWCOUNT,false,null);
                break;
            case R.id.logoutCount:
                if (alertDialog == null) {
                    alertDialog = new AlertDialog.Builder(getActivity() ).create();
                    alertDialog.setIcon(R.drawable.doorknob);
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
    public void onEditItem(View view, int Position) {

        nameCount = listCount.get(Position).getNameCount();
        String string = nameCount;
        String[] parts = string.split("#a!%bc");
        String name = parts[0]; // 19

        ShowDialog();


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
        String [] parametros ={listCount.get(position).getNameCount()};
        String nameCount = listCount.get(position).getNameCount();
        String string = nameCount;
        String[] parts = string.split("#a!%bc");
        String name = parts[0]; // 19

        delateCountDialog(parametros,name);


    }


    public void updateCount(String icon,String password)
    {
        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros ={nameCount};
        ContentValues values = new ContentValues();
        values.put(Utilidades.FIELD_ICON,icon);
        values.put(Utilidades.FIELD_PASSWORD,password);

        db.update(Utilidades.TABLA_CUENTA,values,Utilidades.FIELD_NAME+"=?",parametros);
        db.close();
        initRecycler();
    }
    public void ShowDialog(){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        LayoutInflater inflate = getActivity().getLayoutInflater();
        View vie = inflate.inflate(R.layout.updatecount,null);
        pass = vie.findViewById(R.id.updatePassword);

        one = vie.findViewById(R.id.editicoOne);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        two = vie.findViewById(R.id.editicoTwo);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        tree = vie.findViewById(R.id.editicoThree);
        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        four = vie.findViewById(R.id.editiconFor);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        five = vie.findViewById(R.id.editicoFive);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        six = vie.findViewById(R.id.editicoSix);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        seven = vie.findViewById(R.id.editicoSeven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        eigth = vie.findViewById(R.id.editicoEigth);
        eigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        nine = vie.findViewById(R.id.editicoNine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconSelected ="9";
                one.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                two.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                tree.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                four.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                five.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                six.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                seven.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                eigth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_nonebacgrount));
                nine.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bacground));
            }
        });

        builder.setView(vie)
                .setTitle("Editar Cuenta")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        passw = pass.getText().toString();
                        updateCount(iconSelected,passw);
                    }
                });
        builder.create();
        builder.show();
    }

    public void delateCountDialog(final String[] name,String getname)
    {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(getActivity() ).create();
            alertDialog.setIcon(R.drawable.trash);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("msCuentas");
            alertDialog.setMessage("¿Estás seguro de querer borrar la cuenta "+getname+ "?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "borrar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            SQLiteDatabase db = conn.getWritableDatabase();
                            db.delete(Utilidades.TABLA_CUENTA,Utilidades.FIELD_NAME+"=?",name);
                            Toast.makeText(getActivity(), "Cuenta Eliminada"  , Toast.LENGTH_SHORT).show();
                            db.close();
                            initRecycler();
                            alertDialog = null;
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Uff por poco"  , Toast.LENGTH_SHORT).show();
                            alertDialog = null;
                        }
                    });
            alertDialog.show();
        }

    }
}
