package com.example.siddhant.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class picnic extends AppCompatActivity {
    DatabaseReference reff;
    List<newpicnic> picnic;
    private newpicnic[] darr;
    private Picnicadapter madapter;

    private EditText editText;
    private SearchView searchView;

    static int acount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picnic);
        acount=0;
        final Bundle d = getIntent().getExtras();
        final Adapter[] bAdapter = new Adapter[1];
        final RecyclerView list = findViewById(R.id.picniclist);
        list.setLayoutManager(new LinearLayoutManager(this));
        reff = FirebaseDatabase.getInstance().getReference().child("picnic").child("SpotName");
        picnic =new ArrayList<newpicnic>();

       reff.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot listsnapshot: dataSnapshot.getChildren()){



                   newpicnic lst=new newpicnic(listsnapshot.getKey(),listsnapshot.getValue().toString());

                   picnic.add(lst);

                   darr=new newpicnic[picnic.size()];
                   darr=picnic.toArray(darr);

                   madapter=new Picnicadapter(darr);
                   list.setAdapter(madapter);


               }





//               editText=findViewById(R.id.picnicsearch);
//
//              editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
//              editText.setOnQ
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
}
