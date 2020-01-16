package com.example.siddhant.loginui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.params.BlackLevelPattern;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.exit;


public class hotel_desc extends AppCompatActivity{
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    DatabaseReference reff;
    private String hoteln;
    private String Location;
    private String Description;
    private String Price_per_night;
    private String amen;
    private String img_url;
    private String rating;
    private String hotelName;
    CarouselView carouselView;
    Button backbtn;


    //for comments
    private String cname;
    private String cdate;
    private String cdesc;
    //comments part ends here

    TextView hprice;
    TextView wifitxt;
    TextView wifilobby;

    TextView wifiroomtxt;
    TextView wifiroom;

    TextView wifipooltxt;
    TextView wifipool;

    TextView spatxt;
    TextView spa;

    TextView parkingtxt;
    TextView parking;

    TextView actxt;
    TextView ac;


    TextView petstxt;
    TextView pets;

    TextView hotelbartxt;
    TextView hotelbar;

    TextView gymtxt;
    TextView gym;

    TextView restauranttxt;
    TextView restaurant;


    //    TextView readmore;
//    TextView readmore;





    static List<String> urls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_desc);

        backbtn=findViewById(R.id.backbtn);
        Bundle b=getIntent().getExtras();
        //add hotel name here
        hoteln=b.get("hname").toString();

        carouselView=findViewById(R.id.hcarousel);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, final ImageView imageView) {
                Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(urls.get(position))
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
        });

        reff = FirebaseDatabase.getInstance().getReference().child("hotels").child("Sheet1");
        reff.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {

                                                        hotelName= listsnapshot.child("Name").getValue(String.class);
                                                        if(hotelName.equals(hoteln))
                                                        {
                                                            Location=listsnapshot.child("Location").getValue(String.class);
                                                            amen=listsnapshot.child("Amenities").getValue(String.class);
                                                            img_url=listsnapshot.child("img_url").getValue(String.class);
                                                            rating=listsnapshot.child("Ratings").getValue().toString();
                                                            Description=listsnapshot.child("Description").getValue(String.class);
                                                            Price_per_night=listsnapshot.child("Price_per_night").getValue().toString();
                                                            String mobile=listsnapshot.child("mobile").getValue().toString();
                                                            addDetails(hotelName,Location,amen,img_url,rating,Price_per_night,Description,mobile);
                                                            break;
                                                        }
                                                    }
                                                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void addDetails(String hotelName, String Location, String amen, String img_url, String rating, String price, String description, final String mobile)
    {

        String uri;
        String color;
        int imageResource;
        Drawable res;


        //Read more
        final ReadMoreOption readMoreOption = new ReadMoreOption.Builder(this)
                .textLength(5, ReadMoreOption.TYPE_LINE) // OR
                //.textLength(300, ReadMoreOption.TYPE_CHARACTER)
                .moreLabel("Show more")
                .lessLabel("Show less")
                .moreLabelColor(Color.RED)
                .lessLabelColor(Color.BLUE)
                .expandAnimation(true)
                .build();

        Button callbtn=findViewById(R.id.hcallbtn);
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
                startActivity(intent);
            }
        });
        TextView hname=findViewById(R.id.hname);
        hname.setText(hotelName);

        TextView loc=findViewById(R.id.location);
        loc.setText(Location);

        RatingBar ratingBar=findViewById(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat(rating));

        TextView desc=findViewById(R.id.description);
        //desc.setText(description);

        readMoreOption.addReadMoreTo(desc,description);

        hprice=findViewById(R.id.hprice);
        hprice.setText("\u20B9"+price);
        wifitxt=findViewById(R.id.wifitxt);
        wifilobby=findViewById(R.id.wifilogo);

        wifiroomtxt=findViewById(R.id.wifiroomtxt);
        wifiroom=findViewById(R.id.wifiroom);

        wifipooltxt=findViewById(R.id.wifipooltxt);
        wifipool=findViewById(R.id.wifipool);

        spatxt=findViewById(R.id.spatxt);
        spa=findViewById(R.id.spa);

        parkingtxt=findViewById(R.id.parkingtxt);
        parking=findViewById(R.id.parking);

        actxt=findViewById(R.id.actxt);
        ac=findViewById(R.id.ac);


        petstxt=findViewById(R.id.petstxt);
        pets=findViewById(R.id.pets);

        hotelbartxt=findViewById(R.id.hotelbartxt);
        hotelbar=findViewById(R.id.hotelbar);

        gymtxt=findViewById(R.id.gymtxt);
        gym=findViewById(R.id.gym);

        restauranttxt=findViewById(R.id.restauranttxt);
        restaurant=findViewById(R.id.restaurant);
        if(amen.toLowerCase().contains("wifi in lobby"))
        {
            uri = "@drawable/wifi";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/wifi_light";
            color="#6B000000";
        }
        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);

        wifilobby.setBackground(res);
        wifitxt.setTextColor(Color.parseColor(color));


        if(amen.toLowerCase().contains("wifi in room"))
        {
            uri = "@drawable/wifi";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/wifi_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);

        wifiroom.setBackground(res);
        wifiroomtxt.setTextColor(Color.parseColor(color));

        if(amen.toLowerCase().contains("pool"))
        {
            uri="@drawable/pool";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/wifipool_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        wifipool.setBackground(res);
        wifipooltxt.setTextColor(Color.parseColor(color));


        //spa

        if(amen.toLowerCase().contains("spa"))
        {
            uri="@drawable/spa";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/spa_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        spa.setBackground(res);
        spatxt.setTextColor(Color.parseColor(color));

        //parking

        if(amen.toLowerCase().contains("parking"))
        {
            uri="@drawable/parking";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/parking_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        parking.setBackground(res);
        parkingtxt.setTextColor(Color.parseColor(color));

        //ac

        if(amen.toLowerCase().contains("ac"))
        {
            uri="@drawable/ac";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/ac_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        ac.setBackground(res);
        actxt.setTextColor(Color.parseColor(color));

        //pets

        if(amen.toLowerCase().contains("pet"))
        {
            uri="@drawable/pets";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/pets_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        pets.setBackground(res);
        petstxt.setTextColor(Color.parseColor(color));

        //hotelbar

        if(amen.toLowerCase().contains("hotelbar"))
        {
            uri="@drawable/bar";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/hotelbar_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        hotelbar.setBackground(res);
        hotelbartxt.setTextColor(Color.parseColor(color));

        //gym
        if(amen.toLowerCase().contains("gym"))
        {
            uri="@drawable/gym";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/gym_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        gym.setBackground(res);
        gymtxt.setTextColor(Color.parseColor(color));


        //restaurant

        if(amen.toLowerCase().contains("restaurant"))
        {
            uri="@drawable/restaurant";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/restaurant_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        restaurant.setBackground(res);
        restauranttxt.setTextColor(Color.parseColor(color));
        urls.addAll(Arrays.asList(img_url.split(",")));
        carouselView = (CarouselView) findViewById(R.id.hcarousel);
      //  Toast.makeText(getApplicationContext(), urls.size()+"",Toast.LENGTH_LONG).show();
        carouselView.setPageCount(urls.size());
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, final ImageView imageView) {
                Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(urls.get(position))
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
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
