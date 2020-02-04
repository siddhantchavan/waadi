package com.example.siddhant.loginui;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cust_info_page extends AppCompatActivity {

    TextView from;
    TextView to;
    TextView uname;
    TextView mobileno;
    EditText otp1;
    Button verify;
    Button cancel;
    DatabaseReference reff2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_info_page);
        Bundle b=getIntent().getExtras();


        final String src = (b.getString("src")).trim();
        final String dest = (b.getString("dest")).trim();
        String username=(b.getString("username").trim());
        String mob=(b.getString("mob").trim());
        final String otp=(b.getString("verotp").trim());
        final double lata=b.getDouble("latitude");
        final double longia=b.getDouble("longitude");


        from=findViewById(R.id.from);
        to=findViewById(R.id.to);
        uname=findViewById(R.id.uname);
        mobileno=findViewById(R.id.mob);
        otp1=findViewById(R.id.editText2);
        verify=findViewById(R.id.verify);
        cancel=findViewById(R.id.cancel);


        from.setText(src);
        to.setText(dest);
        uname.setText(username);
        mobileno.setText(mob);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),otp1.getText().toString()+" "+otp,Toast.LENGTH_LONG).show();
                if(otp1.getText().toString().equals(otp))
                {
                    Toast.makeText(getApplicationContext(),"Verified",Toast.LENGTH_LONG).show();
                    reff2=FirebaseDatabase.getInstance().getReference().child(uname.getText().toString());
                    reff2.child("status").setValue("true");
                    startActivity(new Intent(getApplicationContext(),home_page.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=uname.getText().toString();
                reff2=FirebaseDatabase.getInstance().getReference().child(username);
                reff2.child("source").setValue(src);
                reff2.child("destination").setValue(dest);
                reff2.child("latitude").setValue(lata);
                reff2.child("longitude").setValue(longia);
            }
        });
    }
}
