package com.example.siddhant.loginui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.params.BlackLevelPattern;
import android.location.Location;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.exit;


public class hotel_desc extends AppCompatActivity {
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
    TextView readmore;

    //for comments
    private String cname;
    private String cdate;
    private String cdesc;
    //comments part ends here

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





    private String[] urls = new String[] {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_desc);

        //add hotel name here
        hoteln="Ganpatiphule home stay";
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
                                                            rating=listsnapshot.child("Ratings").getValue(String.class);
                                                            Description=listsnapshot.child("Description").getValue(String.class);
                                                            Price_per_night=listsnapshot.child("Price_per_night").getValue(String.class);

                                                            addDetails(hotelName,Location,amen,img_url,rating,Price_per_night,Description,listsnapshot);
                                                            break;
                                                        }
                                                    }
                                                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void addDetails(String hotelName,String Location,String amen,String img_url,String rating,String price,String description,DataSnapshot list)
    {

        String uri;
        String color;
        int imageResource;
        Drawable res;
        RecyclerView list1=findViewById(R.id.commentsList);

        TextView cname=findViewById(R.id.cname);
        TextView cdate=findViewById(R.id.cdate);
        TextView comm=findViewById(R.id.comments);

        commentDetails com[]=new commentDetails[100];
        int count=0;
        for(DataSnapshot dataSnapshot:list.child("comments").getChildren())
        {
            commentDetails temp=new commentDetails();
            temp.setCname(dataSnapshot.getKey());
            //cname.setText(dataSnapshot.getKey());
            temp.setCdate(dataSnapshot.child("Date").toString());
            //cdate.setText(dataSnapshot.child("Date").getValue().toString());
            temp.setCdate(dataSnapshot.child("desc").toString());
            //comm.setText(dataSnapshot.child("desc").getValue().toString());
            com[count++]=temp;
        }
        Toast.makeText(getApplicationContext(),count+"",Toast.LENGTH_LONG).show();
        comments_adapter  mAdapter=new comments_adapter(com);
        list1.setAdapter(mAdapter);

        TextView hname=findViewById(R.id.hname);
        hname.setText(hotelName);

        TextView loc=findViewById(R.id.location);
        loc.setText(Location);

        RatingBar ratingBar=findViewById(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat(rating));

        TextView desc=findViewById(R.id.description);
        desc.setText(description);

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



        urls=img_url.split(",");



        init();


    }
    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(hotel_desc.this,urls));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);

        NUM_PAGES = urls.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
        readmore=findViewById(R.id.moreDesc);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),hotelFullDesc.class));
            }
        });
    }
}
