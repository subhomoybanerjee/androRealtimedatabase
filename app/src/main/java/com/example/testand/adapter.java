package com.example.testand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    Context context;
    ArrayList<user> list;

    public adapter(Context context, ArrayList<user> list) {
        this.context = context;
        this.list = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(adapter.MyViewHolder holder, int position) {
        user us=list.get(position);
        holder.pro.setText(us.getCompany());
        holder.comp.setText(us.getProduct());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView comp,pro;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            comp=itemView.findViewById(R.id.tvcompany);
            pro=itemView.findViewById(R.id.tvproduct);
        }
    }
}
