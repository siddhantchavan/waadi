package com.example.siddhant.loginui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Itemadapter extends RecyclerView.Adapter<Itemadapter.MyViewholder> {
    @NonNull
    List<String>items;

    public static class MyViewholder extends RecyclerView.ViewHolder{

        EditText title,title1,title2;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
//            title1 = itemView.findViewById(R.id.title1);
//            title2 = itemView.findViewById(R.id.title2);
        }
    }

    public Itemadapter(@NonNull List<String> items) {
        this.items = items;
    }

    @Override
    public Itemadapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.itemcontent ,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.title.setText(items.get(position));
//                       holder.title1.setText(items.get(position));
//                       holder.title2.setText(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
