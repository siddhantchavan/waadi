package com.example.siddhant.loginui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private FusedLocationProviderClient client;
    DatabaseReference reff;
    static List<cabdriver> driversLoc=new ArrayList<cabdriver>();
    Button btn;
    private SaveSharedPreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        final Bundle b = getIntent().getExtras();


        session=new SaveSharedPreference(getApplicationContext());


        HashMap<String, String> user = session.getUserDetails();

        final String name=user.get(SaveSharedPreference.KEY_NAME);
        final String src = (b.getString("src")).toLowerCase().trim();
        final String dest = (b.getString("dest")).toLowerCase().trim();
        btn=findViewById(R.id.button19);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(this);
        client.getLastLocation().addOnSuccessListener(MapsActivity3.this, new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    getNearby(location.getLatitude(), location.getLongitude());
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff=FirebaseDatabase.getInstance().getReference().child("member").child(name);
                reff.child("source").setValue(src);
                reff.child("destination").setValue(dest);
                client.getLastLocation().addOnSuccessListener(MapsActivity3.this, new OnSuccessListener<Location>() {

                    @Override
                    public void onSuccess(Location location) {
                        if(location != null) {
                            reff.child("latitude").setValue(location.getLatitude());
                            reff.child("longitude").setValue(location.getLongitude());
                        }
                    }
                });
                String a=generateOTP();
                reff.child("OTP").setValue(a);
                Intent i = new Intent(getApplicationContext(), cabdriver_details.class);
                i.putExtra("otp", a);
                startActivity(i);
                //startActivity(new Intent(getApplicationContext(),driverDetails.class));
            }
        });
    }
    public static String generateOTP()
    {  //int randomPin declared to store the otp
        //since we using Math.random() hence we have to type cast it int
        //because Math.random() returns decimal value
        int randomPin   =(int) (Math.random()*9000)+1000;
        String otp  = String.valueOf(randomPin);
        return otp; //returning value of otp
    }
    public void getNearby(final Double lat, final Double longi)
    {
        reff=FirebaseDatabase.getInstance().getReference().child("cab");

        reff.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {
                    String cust = listsnapshot.child("role").getValue(String.class);
                    if(!cust.isEmpty() && cust.equals("Driver")) {
                        if(!listsnapshot.child("location").getValue().toString().toLowerCase().equals("not available")) {
                            //  Toast.makeText(getApplicationContext(),listsnapshot.child("location").getValue().toString(),Toast.LENGTH_LONG).show();
                            Map<String,Double> loc=(HashMap<String,Double>)listsnapshot.child("location").getValue();
                            Double newlat = loc.get("latitude");
                            if ((lat + 0.01) >= newlat && (lat - 0.01) <= newlat) {
                                if ((longi + 0.01) >= loc.get("longitude") && (longi - 0.01) <= loc.get("longitude")) {
                                    Map<String, String> driverDetails = (HashMap<String, String>) listsnapshot.getValue();
                                    cabdriver newd = new cabdriver();
                                    newd.setName(driverDetails.get("name"));
                                    newd.setLatitude(loc.get("latitude"));
                                    newd.setLongitude(loc.get("longitude"));
                                    newd.setCabno(driverDetails.get("cabno"));
                                    newd.setMobileno(driverDetails.get("mobileno"));
                                    driversLoc.add(newd);
                                }
                            }
                        }
                    }
                }
                onMapReady(mMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        for(int i=0;i<driversLoc.size();i++)
        {
            LatLng latLng=new LatLng(driversLoc.get(i).getLatitude(),driversLoc.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).position(latLng)
                    .title(driversLoc.get(i).getName())
                    .snippet(driversLoc.get(i).getCabno())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15.0f));
            mMap.setOnMarkerClickListener(this);
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        reff=FirebaseDatabase.getInstance().getReference().child("member");
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot listsnapshot : dataSnapshot.getChildren()) {
                    Map<String,String>  customer=(HashMap<String,String>)listsnapshot.getValue();

                    if(!customer.isEmpty() && customer.get("role").equals("Driver")&&customer.get("name").equals(marker.getTitle())) {
                        String s = "tel:" + customer.get("mobileno");

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(s));
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        // Toast.makeText(getApplicationContext(), "sds", Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//         Return false to indicate that we have not consumed the event and that we wish
//         for the default behavior to occur (which is for the camera to move such that the
//         marker is centered and for the marker's info window to open, if it has one).
        return true;
    }
}
