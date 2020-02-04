package com.example.siddhant.loginui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class picnic_details extends AppCompatActivity {

    private String pname;
    private List<String> imgurl;
    private CarouselView carouselView;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picnic_details);


        Bundle r=getIntent().getExtras();
        pname=r.getString("spot_name");

        imgurl=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference("picnic");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot list:dataSnapshot.getChildren()){
                    if (list.child("SpotName").getValue().toString().equals(pname)){
                        imgurl.addAll((List)list.child("img_url").getValue());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        setLayout();

        //Recieve data
        String placname2=getIntent().getExtras().getString("spot_name");
        String placename=getIntent().getExtras().getString("spot_name");
        String description=getIntent().getExtras().getString("Description");
        String address=getIntent().getExtras().getString("Address");

//
//
        //in views
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsingbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView picnicplacename=findViewById(R.id.place_name2);
        TextView picnicdescription=findViewById(R.id.picnic_description);
        TextView picnicaddress=findViewById(R.id.picnic_address);
        //setting vlaues
        picnicdescription.setText(description);
        picnicaddress.setText(address);
        picnicplacename.setText(placname2);
        //img_url
        carouselView=findViewById(R.id.picnic_carousel);

        loadcarousel();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadcarousel();
            }
        },5000);
    }
    public void loadcarousel(){
        carouselView.setPageCount(imgurl.size());
         //setting image carousel
         carouselView.setImageListener(new ImageListener() {
             @Override
             public void setImageForPosition(int position, final ImageView imageView) {
                 Glide.with(getApplicationContext()).asBitmap().load(imgurl.get(position)).into(new CustomTarget<Bitmap>() {
                     @Override
                     public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                         imageView.setImageBitmap(resource);
                     }

                     @Override
                     public void onLoadCleared(@Nullable Drawable placeholder) {

                     }
                 });

        }
    });
         //end of the slider
}
}



