package com.example.siddhant.loginui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class PicnicAdapter  extends RecyclerView.Adapter<PicnicAdapter.MyViewHolder>implements Filterable{
    Context context;
    ArrayList<newpicnic> picnic;
    private List<newpicnic> picnic2;

    public PicnicAdapter(Context c,ArrayList<newpicnic>n){
        context=c;
        picnic=n;
        picnic2=new ArrayList<>();
        picnic2.addAll(picnic);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.picnic_list_item,parent,false);
        //        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.picnic_list_item2,parent,false));

        //new holder for picnic details
        final MyViewHolder viewHolder=new MyViewHolder(view);
        viewHolder.view_picnic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(context,picnic_details.class);
                p.putExtra("spot_name",picnic.get(viewHolder.getAdapterPosition()).getSpotName());
                p.putExtra("Description",picnic.get(viewHolder.getAdapterPosition()).getDescription());
                p.putExtra("Address",picnic.get(viewHolder.getAdapterPosition()).getAddress());
                context.startActivity(p);
            }
        });
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.SpotName.setText(picnic.get(position).getSpotName());

        //load image from internet and set it into imageview using Glide
//        Glide.with(context).load(picnic.get(position).getImg_url()).centerCrop().into(holder.carouselView);

    }

    @Override
    public int getItemCount() {
        return picnic.size();
    }

    @Override
    public Filter getFilter() {
        return  picnicDataFilter;
    }
    private Filter picnicDataFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<newpicnic> filteredList=new ArrayList<>();
            if (charSequence == null || charSequence.length()==0){
                filteredList.addAll(picnic2);
            }
            else {
                String filter=charSequence.toString().toLowerCase().trim();
                for(newpicnic dataItem:picnic2){
                    if(dataItem.getSpotName().toLowerCase().contains(filter)){
                        filteredList.add(dataItem);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            picnic.clear();
            picnic.addAll((Collection<? extends newpicnic>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView SpotName;
        LinearLayout view_picnic;
        TextView Description;
        TextView Address;

        public MyViewHolder( View itemView) {
            super(itemView);
            SpotName=itemView.findViewById(R.id.SpotName);
            view_picnic=itemView.findViewById(R.id.list_root);
            Description=itemView.findViewById(R.id.picnic_description);
            Address=itemView.findViewById(R.id.picnic_address);

        }
    }
}
