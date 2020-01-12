package com.example.siddhant.loginui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class comments_adapter extends RecyclerView.Adapter<comments_adapter.MyViewHolder> {
    private commentDetails[] mDataset;
    private  OnItemClickListner mlistner;

    public interface OnItemClickListner{
        void onButtonClick(int position);
    }

    public void setOnClickListnerItem(OnItemClickListner listner)
    {
        this.mlistner=listner;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cname;
        public TextView cdate;
        public TextView description;
        public Button callbtn;

        public MyViewHolder(View v, final OnItemClickListner listner) {
            super(v);
            cname = (TextView) v.findViewById(R.id.cname);
            cdate = (TextView)v.findViewById(R.id.cdate);
            description=(TextView) v.findViewById(R.id.comments);

//            callbtn.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View view) {
//                    if(listner!=null)
//                    {
//                        int position=getAdapterPosition();
//                        if(position!=RecyclerView.NO_POSITION)
//                        {
//                            listner.onButtonClick(position);
//                        }
//                    }
//                }
//            });
        }
    }

    public comments_adapter(commentDetails[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public comments_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.comments,parent,false);
        return new MyViewHolder(view,mlistner);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.cname.setText(mDataset[position].getCname());
        holder.cname.setTextColor(Color.BLACK);

        holder.cdate.setText(mDataset[position].getCdate());
        holder.cdate.setTextColor(Color.GRAY);

        holder.description.setText(mDataset[position].getDescription());
        holder.description.setTextColor(Color.BLACK);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}