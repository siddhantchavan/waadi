package com.example.siddhant.loginui;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Villasadapter extends RecyclerView.Adapter<Villasadapter.MyViewHolder>  {
    private static List<newvilla> mDataset;
    private Villasadapter.OnItemClickListner mlistner;
    private Villasadapter.OnAcceptListener alistner;


    public interface OnItemClickListner{
        void onButtonClick(int position);
    }
    public interface OnAcceptListener{
        void onAcceptClick(int position);
    }

    public void setOnClickListnerItem(Villasadapter.OnItemClickListner listner)
    {
        this.mlistner=listner;
    }

    public void setOnClickAccept(Villasadapter.OnAcceptListener listner)
    {
        this.alistner=listner;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView villaname;
        public TextView villaprice;
        public RatingBar ratingBar;
        public TextView villadetails;
        public ImageView villaimage;


        public MyViewHolder(View v, final Villasadapter.OnItemClickListner listner, final Villasadapter.OnAcceptListener acceptListener) {
            super(v);
            villaname = (TextView) v.findViewById(R.id.staysname);
            villaprice = (TextView)v.findViewById(R.id.staysprice);
            ratingBar=v.findViewById(R.id.staysratingBar);
            villaimage=v.findViewById(R.id.staysimg);
//            rejectbtn.setOnClickListener(new View.OnClickListener(){
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
//            acceptbrn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(acceptListener!=null)
//                    {
//                        int position=getAdapterPosition();
//                        if(position!=RecyclerView.NO_POSITION)
//                        {
//                            acceptListener.onAcceptClick(position);
//                        }
//                    }
//                }
//            });
        }
    }

    public Villasadapter(List<newvilla> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public Villasadapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.card,parent,false);
        return new Villasadapter.MyViewHolder(view,mlistner,alistner);
    }

    @Override
    public void onBindViewHolder(Villasadapter.MyViewHolder holder, int position) {
        holder.villaname.setText(mDataset.get(position).getVillaName());
        // holder.src.setTextColor(Color.BLACK);
        String splitted[] =mDataset.get(position).getUrl().split(",",2);
        Picasso.get().load(splitted[0]).into(holder.villaimage);
        //holder.villaimage.setImageURI(Uri.parse("http://hotels.konkanyatra.com/jomres/uploadedimages/10/slideshow/0/2013-05-21-325.jpg"));
        holder.villaprice.setText(mDataset.get(position).getVillaprice()+"/-");
        holder.ratingBar.setRating(Float.parseFloat(mDataset.get(position).getRating()));
        //  holder.dest.setTextColor(Color.BLACK);

    }

    public void updateData(newvilla viewModels) {
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
