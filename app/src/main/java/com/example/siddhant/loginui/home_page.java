package com.example.siddhant.loginui;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

    }
    public void gotoLogin(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
    }


    public void gotoRickshaw(View view) {
        startActivity(new Intent(getApplicationContext(),rikshaw3.class));
    }

    public void registerRickshaw2(View view) {
        startActivity(new Intent(getApplicationContext(),rikshaw2.class));
    }
    public void gotoBus(View view) {
        startActivity(new Intent(getApplicationContext(),basic_bushome.class));
    }

    public void gotoTrail(View view) {
        startActivity(new Intent(getApplicationContext(),trail.class));
    }

    public void gotovilla(View view) {
        startActivity(new Intent(getApplicationContext(),new_villa.class));
    }

    public void gotovilla_destination(View view) {
        startActivity(new Intent(getApplicationContext(),villa_destination2.class));
    }

//    public void gotoVilla_card2(View view) {
//        startActivity(new Intent(getApplicationContext(),villa_card2.class));
//    }
//    public void gotopicnic(View view) {
//         startActivity(new Intent(getApplicationContext(),picnic.class));
//    }
    public void stays(View view) {
        startActivity(new Intent(getApplicationContext(),stays.class));
    }
//
    public void gotopicnic2(View view) {
        startActivity(new Intent(getApplicationContext(),picnic2.class));
    }

    public void gotopicnicdetails(View view) {
        startActivity(new Intent(getApplicationContext(),picnic_details.class));
    }
}