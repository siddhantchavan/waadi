package com.example.siddhant.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class resortPage1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    FirebaseDatabase mref;
    DatabaseReference db;
    static String selected;
    static String near;
    AutoCompleteTextView destination;
    Button govilla ;
    Spinner spinner;
    Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort_page1);
        final ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        // final ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2);

        govilla=findViewById(R.id.go_resort);
        destination=findViewById(R.id.rdestination);
        spinner2=findViewById(R.id.nearbySpinner);
        //Arry list for no of person
        //Arry list for near places
        ArrayList<String> Near=new ArrayList<>();
        Near.add("City");
        Near.add("Forest");
        Near.add("Beach");

        ArrayAdapter<String> placeAdapter;
        placeAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Near);
        placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(placeAdapter);

        spinner2.setOnItemSelectedListener(this);
        govilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (destination.getText().toString().isEmpty()){
                    destination.setError("Please Enter Destination");
                }
                else {
                    Intent i = new Intent(getApplicationContext(), resorts_list.class);
                    i.putExtra("dest",destination.getText().toString().trim());
                    i.putExtra("near",near);
                    i.putExtra("pos",spinner2.getSelectedItemPosition());
                    startActivity(i);
                }
            }
        });
        db=FirebaseDatabase.getInstance().getReference().child("resorts");
        db.keepSynced(true);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot suggestionSnapshot:dataSnapshot.getChildren())
                {
                    String suggestion=suggestionSnapshot.child("location").getValue(String.class);
                    adapter1.remove(suggestion);
                    adapter1.add(suggestion);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        destination.setAdapter(adapter1);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            near=adapterView.getItemAtPosition(i).toString();
          //  Toast.makeText(getApplicationContext(),near,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
