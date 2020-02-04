package com.example.siddhant.loginui;


import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;


public class picnic_details extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picnic_details);
        //Recieve data
        String placename = getIntent().getExtras().getString("spot_name");
        String description = getIntent().getExtras().getString("Description");
        String address = getIntent().getExtras().getString("Address");

        //in views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView picnicdescription = findViewById(R.id.picnic_description);
        TextView picnicaddress = findViewById(R.id.picnic_address);

        //setting vlaues
        picnicdescription.setText(description);
        picnicaddress.setText(address);
    }
}