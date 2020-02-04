package com.example.siddhant.loginui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.params.BlackLevelPattern;
import android.location.Location;
import android.os.Handler;

import androidx.annotation.NonNull;
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


public class villa_card2 extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    DatabaseReference reff;
    private String villan;
    private String Location;
    private String Description;
    private String Price_per_night;
    private String amen;
    private String img_url;
    private String rating;
    private String villaName;
    TextView readmore;


    TextView bwifiroomtxt;
    TextView bwifiroom;

    TextView bpooltxt;
    TextView bpool;

    TextView bparkingtxt;
    TextView bparking;

    TextView btvtxt;
    TextView btv;

    TextView bactxt;
    TextView bac;


    TextView bpetstxt;
    TextView bpets;

    TextView bkitchentxt;
    TextView bkitchen;

    //    TextView readmore;
//    TextView readmore;





    private String[] urls = new String[] {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villa_card2);

        //add hotel name here
        villan="Ganpatiphule home stay";
        reff = FirebaseDatabase.getInstance().getReference().child("villas").child("Sheet1");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {

                   villaName= listsnapshot.child("Name").getValue(String.class);
                    if(villaName.equals(villan))
                    {
                        Location=listsnapshot.child("Location").getValue(String.class);
                        amen=listsnapshot.child("Amenities").getValue(String.class);
                        img_url=listsnapshot.child("img_url").getValue(String.class);
                        rating=listsnapshot.child("Ratings").getValue(String.class);
                        Description=listsnapshot.child("Description").getValue(String.class);
                        Price_per_night=listsnapshot.child("Price_per_night").getValue(String.class);
                        addDetails(villaName,Location,amen,img_url,rating,Price_per_night,Description);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void addDetails(String villaName,String Location,String amen,String img_url,String rating,String price,String description)
    {

        String uri;
        String color;
        int imageResource;
        Drawable res;

        TextView bname=findViewById(R.id.bname);
        bname.setText(villaName);

        TextView loc=findViewById(R.id.blocation);
        loc.setText(Location);

        RatingBar ratingBar=findViewById(R.id.bratingBar);
        ratingBar.setRating(Float.parseFloat(rating));

        TextView desc=findViewById(R.id.bdescription);
        desc.setText(description);

        bkitchentxt=findViewById(R.id.bkitchentxt);
        bkitchen=findViewById(R.id.bkitchen);

        bwifiroomtxt=findViewById(R.id.bwifiroomtxt);
        bwifiroom=findViewById(R.id.bwifiroom);

        bpooltxt=findViewById(R.id.bpooltxt);
        bpool=findViewById(R.id.bpool);

        bparkingtxt=findViewById(R.id.bparkingtxt);
        bparking=findViewById(R.id.bparking);

        btvtxt=findViewById(R.id.btvtxt);
        btv=findViewById(R.id.btv);

        bactxt=findViewById(R.id.bactxt);
        bac=findViewById(R.id.bac);


        bpetstxt=findViewById(R.id.bpetstxt);
        bpets=findViewById(R.id.bpets);



        //wifi
        if(amen.toLowerCase().contains("wifi in room"))
        {
            uri = "@drawable/bwifi";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/bwifi_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);

        bwifiroom.setBackground(res);
        bwifiroomtxt.setTextColor(Color.parseColor(color));
        //pool
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
        bpool.setBackground(res);
       bpooltxt.setTextColor(Color.parseColor(color));


        //kitchen

        if(amen.toLowerCase().contains("kitchen"))
        {
            uri="@drawable/kitchen";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/kitchen_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        btv.setBackground(res);
        btvtxt.setTextColor(Color.parseColor(color));

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
        bparking.setBackground(res);
        bparkingtxt.setTextColor(Color.parseColor(color));

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
        bac.setBackground(res);
        bactxt.setTextColor(Color.parseColor(color));

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
        bpets.setBackground(res);
        bpetstxt.setTextColor(Color.parseColor(color));

        //tv
        if(amen.toLowerCase().contains("tv"))
        {
            uri="@drawable/tv";
            color="#FF000000";
        }
        else
        {
            uri = "@drawable/tv_light";
            color="#6B000000";
        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        btv.setBackground(res);
        btvtxt.setTextColor(Color.parseColor(color));


        urls=img_url.split(",");
        Toast.makeText(getApplicationContext(),urls.toString(),Toast.LENGTH_SHORT).show();
        init();


    }
    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(villa_card2.this,urls));

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
                startActivity(new Intent(getApplicationContext(),villa_discription.class));
            }
        });
    }
}