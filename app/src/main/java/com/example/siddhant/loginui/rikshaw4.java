package com.example.siddhant.loginui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class rikshaw4 extends AppCompatActivity{
    Switch aswitch;
    private FusedLocationProviderClient client;
    static double lat1;
    static double long1;
    DatabaseReference reff;
    DatabaseReference reff2;
    usersAdapter[] uAdapter = new usersAdapter[1];
    // List<member> customers = new ArrayList<>();
    static RecyclerView list;
    // static int countm=0;
    List<member> newlist=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rikshaw4);

        //add here
        Toast.makeText(getApplicationContext(),"username need to be add",Toast.LENGTH_SHORT).show();
        reff=FirebaseDatabase.getInstance().getReference().child("member").child("Dude").child("location");
        aswitch=findViewById(R.id.switch2);
        list = findViewById(R.id.lists);
        uAdapter[0] = new usersAdapter(newlist);

        list.setLayoutManager(new LinearLayoutManager(rikshaw4.this));

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            }
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET}, 101);
            }
        }
        final int[] count = {0};
        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {

                    client= LocationServices.getFusedLocationProviderClient(rikshaw4.this);
                    //add here

                    Toast.makeText(getApplicationContext(), "Location on", Toast.LENGTH_SHORT).show();
                    client.getLastLocation().addOnSuccessListener(rikshaw4.this, new OnSuccessListener<Location>() {

                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                reff.setValue(location);
                                lat1 = (double)location.getLatitude();
                                long1 = (double)location.getLongitude();
                                reff2=FirebaseDatabase.getInstance().getReference().child("member");
                                reff2.addListenerForSingleValueEvent(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {

                                            // Toast.makeText(getApplicationContext(),String.valueOf("AS"+customers.size()),Toast.LENGTH_LONG).show();
                                            Map<String, Double> td = (HashMap<String, Double>) listsnapshot.getValue();
                                            Toast.makeText(rikshaw4.this, listsnapshot.getValue()+"", Toast.LENGTH_SHORT).show();

                                            //Toast.makeText(getApplicationContext(),String.valueOf(),Toast.LENGTH_LONG).show();
                                            if(!String.valueOf(td.get("latitude")).equals("null")&&td.get("latitude")!=1000.02f) {
                                                Toast.makeText(getApplicationContext(),"here"+td.get("latitude"),Toast.LENGTH_LONG).show();

                                                double lata=(Double)td.get("latitude");
                                                double longia=(Double)td.get("longitude");
                                                if (lat1 + 0.01 >= lata && lat1 - 0.01 <= lata) {
                                                    if (long1 + 0.01 >= longia && long1 - 0.01 <= longia) {
                                                        member nd = new member();
                                                        Map<String, String> td1 = (HashMap<String, String>) listsnapshot.getValue();
                                                        nd.setSource(td1.get("source"));
                                                        nd.setDestination(td1.get("destination"));
                                                        nd.setUsername(td1.get("username"));
                                                        nd.setVerification(td1.get("mobileno"));
                                                        nd.setLatitude(lata);
                                                        nd.setLongitude(longia);

                                                        if (td1.get("OTP") != null)
                                                            nd.setOtp(td1.get("OTP"));

                                                        Toast.makeText(getApplicationContext(), "source " + nd.getSource() + " ", Toast.LENGTH_LONG).show();
                                                        addData(newlist, list, nd, count[0]++);
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    });
                } else {
                    reff.setValue("Not available");
                    Toast.makeText(getApplicationContext(), "Location off", Toast.LENGTH_SHORT).show();
                }
            }
        });
        uAdapter[0].setOnClickListnerItem(new usersAdapter.OnItemClickListner() {
            @Override
            public void onButtonClick(int position) {
                newlist.remove(position);
                list.setAdapter(uAdapter[0]);
            }
        });
        uAdapter[0].setOnClickAccept(new usersAdapter.OnAcceptListener() {
            @Override
            public void onAcceptClick(int position) {
                member mb=newlist.get(position);
                reff2.child(mb.getUsername()).child("source").setValue("null");
                reff2.child(mb.getUsername()).child("destination").setValue("null");
                reff2.child(mb.getUsername()).child("longitude").setValue(1000.02);
                reff2.child(mb.getUsername()).child("latitude").setValue(1000.02);


                //add username here
                reff2.child(mb.getUsername()).child("driver_name").setValue("Dude");

                gotocustInfoPage(mb.getSource(),mb.getDestination(),mb.getUsername(),mb.getVerification(),mb.getOtp(),mb.getLatitude(),mb.getLongitude());
            }
        });
    }

    private void gotocustInfoPage(String src,String dest,String username,String mob,String verotp,double latitude,double longitude) {
        //add username here
        reff=FirebaseDatabase.getInstance().getReference().child("member").child("Dude").child("location");
        reff.setValue("Not Available");
        Intent i = new Intent(getApplicationContext(), cust_info_page.class);
        i.putExtra("src", src);
        i.putExtra("dest", dest);
        i.putExtra("username",username);
        i.putExtra("mob",mob);
        i.putExtra("latitude",latitude);
        i.putExtra("longitude",longitude);
        i.putExtra("verotp",verotp);

        startActivity(i);
    }

    public void addData(List<member> newlist,RecyclerView list,member c,int count)
    {
        if (c != null) {
            if (count == 0){
                newlist.add(c);
                list.setAdapter(uAdapter[0]);
            }else {
                uAdapter[0].updateData(c);
            }
        }
    }
}
