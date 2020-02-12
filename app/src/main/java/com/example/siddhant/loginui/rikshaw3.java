package com.example.siddhant.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class rikshaw3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rikshaw3);
    }

    public void gotoRickshaw1(View view) {
        startActivity(new Intent(getApplicationContext(), rikshaw1.class));
    }

    public void gotoRickshaw2(View view) {
        startActivity(new Intent(getApplicationContext(),rikshaw2.class));

    }

    public void on_rikshaowner(View view) {

        startActivity(new Intent(getApplicationContext(),rikshaowner.class));
    }
}
