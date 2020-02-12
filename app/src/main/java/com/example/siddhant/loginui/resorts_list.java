package com.example.siddhant.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class resorts_list extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseReference reff;
    String near;
    String destination;
    static resortAdapter[] vAdapter=new resortAdapter[1];
    RecyclerView list;
    List<cardItems> List=new ArrayList<>();
    SeekBar seekBar;
    TextView seekbarop;
    static int[] progressv = new int[1];
    static String search;
    Spinner nearbySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resorts_list);
        EditText ed=findViewById(R.id.searchField);
        nearbySpinner=findViewById(R.id.nearbySpinner);
        Bundle b=getIntent().getExtras();
        destination=b.getString("dest");
        near=b.getString("near");
        int pos=b.getInt("pos");
        ed.setText(destination);
        seekBar=findViewById(R.id.seekBar);
        seekbarop=findViewById(R.id.seekbarop);
        search=destination;
        vAdapter[0] = new resortAdapter(List);
        list = findViewById(R.id.resortList);
        nearbySpinner.setOnItemSelectedListener(this);
        nearbySpinner.setSelection(pos);
        progressv[0]=2000;


        list.setLayoutManager(new LinearLayoutManager(resorts_list.this));
        reff = FirebaseDatabase.getInstance().getReference().child("resorts");

        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final int[] count = {0};
                for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {
                    Map<String,String> mp=(HashMap<String,String>) listsnapshot.getValue();
                    Map<String,Long> mp1=(HashMap<String,Long>) listsnapshot.getValue();
                    Map<String,Double> mp2=(HashMap<String,Double>) listsnapshot.getValue();

                  //  if(mp.get("location").equals(destination) && mp.get("near").toLowerCase().equals(near.toLowerCase()))
                         cardItems nd = new cardItems(mp.get("name"), mp1.get("price"), mp.get("img_url"),Double.parseDouble(mp2.get("rating")+""),mp.get("location"),mp.get("near"));
                         addData(List, list, nd, count[0]++);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        vAdapter[0].setCardClickListener(new resortAdapter.onCardClick(){
            @Override
            public void onCardClick(cardItems items) {
                cardItems mb=items;
                Intent i=new Intent(getApplicationContext(),resort.class);
                i.putExtra("name",mb.getName());
                startActivity(i);
            }
        });


        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                search=s.toString();
                filter(s.toString(),progressv[0],nearbySpinner.getSelectedItem().toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });

        seekBar.incrementProgressBy(500);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressv[0] =progress;
                seekbarop.setText("Price below: \u20B9"+progress);
                filter(search,progressv[0],nearbySpinner.getSelectedItem().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarop.setText("Price below: \u20B9 "+progressv[0]);
                filter(search,progressv[0],nearbySpinner.getSelectedItem().toString());
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                filter(destination,progressv[0],nearbySpinner.getSelectedItem().toString());
            }
        }, 1000);
    }

    void filter(String text,long price,String item){
        List<cardItems> temp = new ArrayList();
        for(cardItems d: List){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getLoc().toLowerCase().contains(text.toLowerCase())&&price>=d.getprice()&&d.getNearby().toLowerCase().equals(item.toLowerCase())){
                temp.add(d);
            }
        }
        //update recyclerview
        vAdapter[0].updateList(temp);
    }
    public void addData(List<cardItems> newlist,RecyclerView list,cardItems c,int count)
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        filter(search,progressv[0],parent.getItemAtPosition(position).toString());
        //  Toast.makeText(getApplicationContext(),parent.getItemAtPosition(position)+"",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
