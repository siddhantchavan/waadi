package com.example.siddhant.loginui;

import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class usersAdapter extends RecyclerView.Adapter<usersAdapter.MyViewHolder>  {
    private static List<member> mDataset;
    private usersAdapter.OnItemClickListner mlistner;
    private usersAdapter.OnAcceptListener alistner;


    public interface OnItemClickListner{
        void onButtonClick(int position);
    }
    public interface OnAcceptListener{
        void onAcceptClick(int position);
    }

    public void setOnClickListnerItem(usersAdapter.OnItemClickListner listner)
    {
        this.mlistner=listner;
    }

    public void setOnClickAccept(usersAdapter.OnAcceptListener listner)
    {
        this.alistner=listner;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView src;
        public TextView dest;
        public Button rejectbtn;
        public Button acceptbrn;


        public MyViewHolder(View v, final usersAdapter.OnItemClickListner listner, final usersAdapter.OnAcceptListener acceptListener) {
            super(v);
            src = (TextView) v.findViewById(R.id.source);
            dest = (TextView)v.findViewById(R.id.dest);
            rejectbtn=v.findViewById(R.id.button18);
            acceptbrn=v.findViewById(R.id.button19);
            rejectbtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(listner!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listner.onButtonClick(position);
                        }
                    }
                }
            });
            acceptbrn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(acceptListener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            acceptListener.onAcceptClick(position);
                        }
                    }
                }
            });
        }
    }

    public usersAdapter(List<member> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public usersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.customers,parent,false);
        return new usersAdapter.MyViewHolder(view,mlistner,alistner);
    }

    @Override
    public void onBindViewHolder(usersAdapter.MyViewHolder holder, int position) {
        holder.src.setText(mDataset.get(position).getSource());
        holder.src.setTextColor(Color.BLACK);

        holder.dest.setText(mDataset.get(position).getDestination());
        holder.dest.setTextColor(Color.BLACK);

    }

    public void updateData(member viewModels) {
        mDataset.add(viewModels);
    }
    public static void removeData(int position) {
        mDataset.remove(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
