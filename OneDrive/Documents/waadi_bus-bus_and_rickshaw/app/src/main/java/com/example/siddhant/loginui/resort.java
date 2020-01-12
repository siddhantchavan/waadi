package com.example.siddhant.loginui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.devs.readmoreoption.ReadMoreOption;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// here no appcompatactivity

public class resort extends AppCompatActivity {
    static List<String> imgUrls;
    CarouselView carouselView;
    TextView rname;
    TextView rloc;
    TextView rdesc;
    ListView meal;
    String number;


    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
//    int clickCounter=0;


    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort);
        rname=findViewById(R.id.rname);
        rloc=findViewById(R.id.rloc);
        rdesc=findViewById(R.id.rdesc);
        meal=findViewById(R.id.meal);


        //Readmore

        final ReadMoreOption readMoreOption = new ReadMoreOption.Builder(this)
                .textLength(5, ReadMoreOption.TYPE_LINE) // OR
                //.textLength(300, ReadMoreOption.TYPE_CHARACTER)
                .moreLabel("Show more")
                .lessLabel("Show less")
                .moreLabelColor(Color.RED)
                .lessLabelColor(Color.BLUE)
                .expandAnimation(true)
                .build();





        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        meal.setAdapter(adapter);

        reff=FirebaseDatabase.getInstance().getReference().child("resorts");
        imgUrls=new ArrayList<>();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {

                    //add name here
                    if(listsnapshot.child("name").getValue().toString().contains("Beach Camping At Alibaug"))
                    {
                        Collection<String> urls=new ArrayList<>();
                        urls.addAll(Arrays.asList(listsnapshot.child("img_url").getValue().toString().split(",,")));
                        imgUrls.addAll(urls);
                        rname.setText(listsnapshot.child("name").getValue().toString());
                        rloc.setText(listsnapshot.child("location").getValue().toString());
                        number=listsnapshot.child("mobile").getValue().toString();
                        readMoreOption.addReadMoreTo(rdesc,listsnapshot.child("about").getValue().toString());

                       //urls.addAll(Arrays.asList(listsnapshot.child("Meal").getValue().toString().split(",")+"\n"));
                        listItems.addAll(Arrays.asList(listsnapshot.child("Meal").getValue().toString().split(",")));
                        adapter.notifyDataSetChanged();

//                        Collection<String> meals=new ArrayList<>();
//                        meals.addAll(Arrays.asList(listsnapshot.child("Meal").getValue().toString().split(",")));

                        //Toast.makeText(getApplicationContext(),imgUrls.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setImageToCaroussel();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setImageToCaroussel();
            }
        }, 5000);
    }
    private void setImageToCaroussel(){
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(imgUrls.size());
        carouselView.setImageListener(imageListener);

    }
    ImageListener imageListener = new ImageListener(){
        @Override
        public void setImageForPosition(final int position, final ImageView imageView) {

            Glide.with(getApplicationContext())
                    .asBitmap()
                    .load(imgUrls.get(position))
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            imageView.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });

        }
    };


}
