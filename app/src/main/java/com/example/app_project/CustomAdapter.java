package com.example.app_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList phone_id,phone_title,phone_marque,phone_price;
    Activity activity;
    int position;
    CustomAdapter(Activity activity,Context context, ArrayList phone_id, ArrayList phone_title,ArrayList phone_marque,ArrayList phone_price){
        this.context = context;
        this.phone_id = phone_id;
        this.phone_title= phone_title;
        this.phone_marque= phone_marque;
        this.phone_price=phone_price;
        this.activity=activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v =  inflater.inflate(R.layout.row,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        this.position = position;
        holder.id_txt.setText(String.valueOf(phone_id.get(position)));
        holder.title_txt.setText(String.valueOf(phone_title.get(position)));
        holder.marque_txt.setText(String.valueOf(phone_marque.get(position)));
        holder.price_txt.setText(String.valueOf(phone_price.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MiseJour.class);
                intent.putExtra("id",String.valueOf(phone_id.get(position)));
                intent.putExtra("title",String.valueOf(phone_title.get(position)));
                intent.putExtra("marque",String.valueOf(phone_marque.get(position)));
                intent.putExtra("price",String.valueOf(phone_price.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return phone_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt,title_txt,marque_txt,price_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_Phone);
            title_txt = itemView.findViewById(R.id.title_txt);
            marque_txt = itemView.findViewById(R.id.marque_txt);
            price_txt = itemView.findViewById(R.id.price_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}