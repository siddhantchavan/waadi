package com.example.siddhant.loginui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity {
    EditText username,password,cpassword,email,verfication;
    Button button;
    member member;
    DatabaseReference reff;
    ProgressDialog pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        cpassword=(EditText)findViewById(R.id.cpassword);
        email=(EditText)findViewById(R.id.email);
        verfication=(EditText)findViewById(R.id.verfication);
        button=(Button) findViewById(R.id.button);
        pro=new ProgressDialog(this);
        member=new member();
        reff= FirebaseDatabase.getInstance().getReference().child("member");
        

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View view){
                String user=username.getText().toString();
                if (email.getText().toString().trim().isEmpty()) {
                    email.setError("email field can't be empty!");
                }
                else if (username .getText().toString().trim().isEmpty()) {
                    username.setError("username field can't be empty!");
                }
                else if(username.length()>15)
                    username.setError("username cannot be greater than 15!");
                else{
                    pro.setMessage("Registering...");
                    pro.show();
                    member.setUsername(username.getText().toString().trim());
                    member.setPassword(password.getText().toString().trim());
                    member.setCpassword(cpassword.getText().toString().trim());
                    member.setEmail(email.getText().toString().trim());
                    member.setVerification(verfication.getText().toString().trim());
                    reff.child(username.getText().toString().trim()).setValue(member);
                    Toast.makeText(sign_up.this, "Successfully Signed in", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), login.class));
                }
            }
        });
    }
    public void gotologin(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
    }
}
