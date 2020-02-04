package com.example.siddhant.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class new_villa extends AppCompatActivity {
    DatabaseReference reff;
    Long nop;
    String near;
    String destination;
    Villasadapter[] vAdapter=new Villasadapter[1];
    RecyclerView list;
    List<newvilla> villaList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_villa);


        Bundle b=getIntent().getExtras();
        destination=b.getString("dest");
        nop = Long.parseLong(b.getString("nop"));
        near=b.getString("near");
        vAdapter[0] = new Villasadapter(villaList);
        list = findViewById(R.id.hlist);
        list.setLayoutManager(new LinearLayoutManager(new_villa.this));
        reff = FirebaseDatabase.getInstance().getReference().child("villas").child("Sheet1");

       // Toast.makeText(getApplicationContext(),nop+"/"+near,Toast.LENGTH_SHORT).show();



        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final int[] count = {0};
                for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {
                    Map<String,String> mp=(HashMap<String,String>) listsnapshot.getValue();
                    Map<String,Long> mp1=(HashMap<String,Long>) listsnapshot.getValue();





                    if(mp.get("Location").equals(destination) && mp1.get("number of person")>=nop)
                    {
                        if(mp.get("Near").toLowerCase().equals(near.toLowerCase())) {
                            newvilla nd = new newvilla(mp.get("Name"), mp.get("Price_per_night"), mp.get("img_url"), mp.get("Ratings"));
                            addData(villaList, list, nd, count[0]++);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addData(List<newvilla> newlist,RecyclerView list,newvilla c,int count)
    {
        if (c != null) {
            if (count == 0) {
                newlist.add(c);
                list.setAdapter(vAdapter[0]);
            }else {
                vAdapter[0].updateData(c);
            }
        }
    }

}
