package com.example.siddhant.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class stays extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stays);
    }

    public void gotovilla_destination(View view) {
        startActivity(new Intent(getApplicationContext(),villa_discription.class));
    }


//    public void gotoVilla_card2(View view) {
//        startActivity(new Intent(getApplicationContext(),villa_card2.class));
//    }

    public void gotovilla(View view) {
        startActivity(new Intent(getApplicationContext(),villa_destination2.class));
    }
}
