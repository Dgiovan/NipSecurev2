package com.gio.mscuentas.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gio.mscuentas.Fragments.counts;
import com.gio.mscuentas.Interfaces.ItemClickListener;
import com.gio.mscuentas.Entidades.countModel;
import com.gio.mscuentas.R;

import java.util.ArrayList;

import static java.util.Collections.addAll;

public class countAdapter extends RecyclerView.Adapter <countAdapter.VHcount> {

    ArrayList<countModel> listaUsuario;
    Context context;
    private ItemClickListener clickListener;
    public countAdapter(Context context,ArrayList<countModel> listaUsuario)
    {
        this.context = context;
        this.listaUsuario = listaUsuario;
    }
    public void SetOnItemClickListener(ItemClickListener listener)
    {
        clickListener = listener;
    }

    public class VHcount extends RecyclerView.ViewHolder  {
        ImageView icon;
        TextView  named;
        TextView  pasword;
        ImageView see;
        public VHcount(@NonNull View itemView,final ItemClickListener listener) {
            super(itemView);
            icon = itemView.findViewById(R.id.iconItem);
            named = itemView.findViewById(R.id.nameItem);
            pasword = itemView.findViewById(R.id.passwordItem);
            see = itemView.findViewById(R.id.seeItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            clickListener.onItemClick(v,getAdapterPosition());
                        }


                    }
                }
            });
            see.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onSpesificItem(v,getAdapterPosition());
                        }
                    }
                }
            });

        }
    }
    public void setListener(ItemClickListener itemClickListener)
    {
        this.clickListener = itemClickListener;
    }
    public void setSpesifcListener(ItemClickListener spesifcListener)
    {
        this.clickListener = spesifcListener;
    }

    @NonNull
    @Override
    public VHcount onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_counts,parent,false);

        return new VHcount(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VHcount holder, int position) {
        //countModel countmodel = listaUsuario.get(position);
        holder.named.setText(listaUsuario.get(position).getNameCount().toString());
        holder.pasword.setText(listaUsuario.get(position).getPassworCount().toString());
        switch (listaUsuario.get(position).getIcon().toString())
        {
            case "1":
                holder.icon.setImageResource(R.drawable.creditcards);
            break;
            case "2":
                holder.icon.setImageResource(R.drawable.facebook);
            break;
            case "3":
                holder.icon.setImageResource(R.drawable.gmail);
                break;
            case "4":
                holder.icon.setImageResource(R.drawable.githubsign);
                break;
            case "5":
                holder.icon.setImageResource(R.drawable.domain);
                break;
            case "6":
                holder.icon.setImageResource(R.drawable.laptop);
                break;
            case "7":
                holder.icon.setImageResource(R.drawable.smartphone);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }
    
}
