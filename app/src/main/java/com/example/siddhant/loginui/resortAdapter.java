package com.example.siddhant.loginui;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class resortAdapter extends RecyclerView.Adapter<resortAdapter.MyViewHolder> {


    List<cardItems> items;
    private resortAdapter.onCardClick cardClickListener;

    public interface onCardClick{
        void onCardClick(cardItems items1);
    }


    public void setCardClickListener(resortAdapter.onCardClick listner)
    {
        this.cardClickListener=listner;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;
        public RatingBar ratingBar;
        public ImageView img;
        public CardView card;


        public MyViewHolder(View view,final resortAdapter.onCardClick listener) {
            super(view);
            name = (TextView) view.findViewById(R.id.staysname);
            price = (TextView) view.findViewById(R.id.staysprice);
            ratingBar=view.findViewById(R.id.staysratingBar);
            img=view.findViewById(R.id.staysimg);
            card=view.findViewById(R.id.stayscard);

            card.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {

                            listener.onCardClick(items.get(position));
                        }
                    }
                }
            });
        }
    }


    public resortAdapter(List<cardItems> staysList) {

        this.items = staysList;

    }
    @Override
    public resortAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new MyViewHolder(itemView,cardClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull resortAdapter.MyViewHolder holder, int position) {
        cardItems item = items.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("\u20B9"+item.getprice());
        holder.ratingBar.setRating(Float.parseFloat(item.getRating()+""));
        String splitted[] =items.get(position).getUrl().split(",,",2);
        Picasso.get().load(splitted[0]).into(holder.img);
    }
    public void updateData(cardItems viewModels) {
        items.add(viewModels);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void updateList(List<cardItems> list){
        this.items = list;
        notifyDataSetChanged();
    }
}
