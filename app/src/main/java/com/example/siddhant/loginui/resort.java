package com.example.siddhant.loginui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
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
    TextView rprice;
    RatingBar rRating;
    String mobileno;
    ListView meal;
    Button rcallbtn;
    ListView ract;

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    String name;
    ArrayList<String> listact=new ArrayList<String>();
    ArrayAdapter<String> actAdapter;

    String nearby;
    Button backbtn;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort);
        rname=findViewById(R.id.rname);
        rloc=findViewById(R.id.rloc);
        rdesc=findViewById(R.id.rdesc);
        meal=findViewById(R.id.meal);
        rprice=findViewById(R.id.rprice);
        rRating=findViewById(R.id.rRating);
        rcallbtn=findViewById(R.id.rcallbtn);
        ract=findViewById(R.id.listact);
        backbtn=findViewById(R.id.backbtn);

        //getting name from resorts_list

        Bundle b=getIntent().getExtras();

        name=b.get("name").toString();


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

        //adding meals to listview
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        meal.setAdapter(adapter);

        //for adding activity to listview
        actAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listact);
        ract.setAdapter(actAdapter);


        reff=FirebaseDatabase.getInstance().getReference().child("resorts");
        imgUrls=new ArrayList<>();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {

                    //add name here
                    if(listsnapshot.child("name").getValue().toString().contains(name))
                    {
                        Collection<String> urls=new ArrayList<>();
                        urls.addAll(Arrays.asList(listsnapshot.child("img_url").getValue().toString().split(",,")));
                        imgUrls.addAll(urls);
                        rname.setText(listsnapshot.child("name").getValue().toString());
                        rloc.setText(listsnapshot.child("location").getValue().toString());
                       // number=listsnapshot.child("mobile").getValue().toString();
                        readMoreOption.addReadMoreTo(rdesc,listsnapshot.child("about").getValue().toString());

                        listItems.addAll(Arrays.asList(listsnapshot.child("Meal").getValue().toString().split(",")));
                        adapter.notifyDataSetChanged();
                        rprice.setText(" \u20B9"+listsnapshot.child("price").getValue().toString()+ "/- "+Html.fromHtml("<sub>per person</sub>"));
                        rRating.setRating(Float.parseFloat(listsnapshot.child("rating").getValue().toString()));
                        mobileno=listsnapshot.child("mobile").getValue().toString();
                        nearby=listsnapshot.child("near").getValue().toString();

                        listact.addAll(Arrays.asList(listsnapshot.child("activity").getValue().toString().split(",")));
                        actAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rcallbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobileno));
                startActivity(intent);
            }
        });

        setImageToCaroussel();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setImageToCaroussel();
            }
        }, 5000);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
