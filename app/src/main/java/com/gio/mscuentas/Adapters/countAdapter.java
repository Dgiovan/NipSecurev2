package com.gio.mscuentas.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gio.mscuentas.Interfaces.ItemClickListener;
import com.gio.mscuentas.Models.countModel;
import com.gio.mscuentas.R;

import java.util.ArrayList;

public class countAdapter extends RecyclerView.Adapter <countAdapter.VHcount> {

    Context context;
    ArrayList<countModel> data;
    private ItemClickListener clickListener;

    public countAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public class VHcount extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView  named;
        TextView  pasword;
        ImageView see;
        public VHcount(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iconItem);
            named = itemView.findViewById(R.id.nameItem);
            pasword = itemView.findViewById(R.id.passwordItem);
            see = itemView.findViewById(R.id.seeItem);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
            {
                clickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
    public void setListener(ItemClickListener itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public VHcount onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_counts,parent,false);

        return new VHcount(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHcount holder, int position) {
        countModel countmodel = data.get(position);
        holder.named.setText(countmodel.getNameCount());
        holder.named.setText(countmodel.getPassworCount());
        switch (countmodel.getIcon())
        {
            case 1:
                holder.icon.setImageResource(R.drawable.creditcards);
            break;
            case 2:
                holder.icon.setImageResource(R.drawable.facebook);
            break;
            case 3:
                holder.icon.setImageResource(R.drawable.gmail);
                break;
            case 4:
                holder.icon.setImageResource(R.drawable.githubsign);
                break;
            case 5:
                holder.icon.setImageResource(R.drawable.domain);
                break;
            case 6:
                holder.icon.setImageResource(R.drawable.laptop);
                break;
            case 7:
                holder.icon.setImageResource(R.drawable.smartphone);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
