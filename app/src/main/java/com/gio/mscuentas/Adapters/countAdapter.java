package com.gio.mscuentas.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.gio.mscuentas.Fragments.counts;
import com.gio.mscuentas.Interfaces.ItemClickListener;
import com.gio.mscuentas.Entidades.countModel;
import com.gio.mscuentas.R;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class countAdapter extends RecyclerView.Adapter <countAdapter.VHcount> implements Filterable {

    ArrayList<countModel> listaUsuario;
    ArrayList<countModel> listFullCounts;
    Context context;
    private ItemClickListener clickListener;
    public countAdapter(Context context,ArrayList<countModel> listaUsuario)
    {
        this.context = context;
        this.listaUsuario = listaUsuario;
        listFullCounts = new ArrayList<>(listaUsuario);
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
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            clickListener.onItemClick(v,getAdapterPosition());
                        }

                    }
                    return false;
                }
            });/*setOnClickListener(new View.OnClickListener() {
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
            });*/
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
      //  String pasword = listaUsuario.get(position).getPassworCount();
        holder.pasword.setText(listaUsuario.get(position).getHidenPasword());

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

    @Override
    public Filter getFilter() {
        return filterCounts;
    }

    private Filter filterCounts = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<countModel> filterList = new ArrayList<>();
            if (constraint ==null || constraint.length()==0)
            {
                filterList.addAll(listFullCounts);

            }else
                {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (countModel item: listFullCounts)
                    {
                        if (item.getNameCount().toLowerCase().contains(filterPattern))
                        {
                            filterList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filterList;
                return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaUsuario.clear();
            listaUsuario.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
