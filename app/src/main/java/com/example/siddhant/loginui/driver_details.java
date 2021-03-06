package com.example.siddhant.loginui;

import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class driver_details extends AppCompatActivity {
    TextView tp,name,mob,rno;
    Button geturl;
    ImageView img;
    DatabaseReference reff;
    String driver_name;
    String mobileno;
    String rickshawno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);
        Bundle b = getIntent().getExtras();
        String otp = (b.getString("otp")).trim();
        tp = findViewById(R.id.textView13);
        name=findViewById(R.id.textView39);
        mob=findViewById(R.id.textView38);
        rno=findViewById(R.id.textView37);


        tp.setText(otp);

        img = findViewById(R.id.profilepic);
        reff = FirebaseDatabase.getInstance().getReference().child("member").child("Dude");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> mp = (HashMap<String, String>) dataSnapshot.getValue();
                driver_name = mp.get("driver_name");
                mobileno=mp.get("mobileno");
                rickshawno=mp.get("rickshawno");
                name.setText(driver_name);
                mob.setText(mobileno);
                rno.setText(rickshawno);
                showUrl(driver_name);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void showUrl(String driver_name)
    {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        StorageReference dateRef = storageRef.child("/images/profile/"+driver_name+".jpg");
        dateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri downloadUrl)
            {
                Picasso.get().load(downloadUrl).into(img);
            }
        });
    }
}
