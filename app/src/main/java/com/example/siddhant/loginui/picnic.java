//package com.example.siddhant.loginui;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//
//import android.widget.ArrayAdapter;
//import android.widget.SearchView.OnQueryTextListener;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.inputmethod.EditorInfo;
//import android.widget.AutoCompleteTextView;
//import android.widget.EditText;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.database.DataSnapshot;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class picnic extends AppCompatActivity {
//
//    List<newpicnic> picnic;
//    private newpicnic[] darr;
//
//    private EditText editText;
//
//    static int acount;
//    RecyclerView recyclerView;
//    FirebaseDatabase mFirebaseDatabase;
//    SearchView search;
//    DatabaseReference reff;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_picnic);
//
//
////        acount=0;
////        final Bundle d = getIntent().getExtras();
////        final Adapter[] bAdapter = new Adapter[1];
////        final RecyclerView list = findViewById(R.id.picniclist);
////        list.setLayoutManager(new LinearLayoutManager(this));
////        reff = FirebaseDatabase.getInstance().getReference().child("picnic").child("SpotName");
////        picnic =new ArrayList<newpicnic>();
////
////       reff.addListenerForSingleValueEvent(new ValueEventListener() {
////           @Override
////           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////               for(DataSnapshot listsnapshot: dataSnapshot.getChildren()){
////
////
////
////                   newpicnic lst=new newpicnic(listsnapshot.getValue().toString());
////
////                   picnic.add(lst);
////
////                   darr=new newpicnic[picnic.size()];
////                   darr=picnic.toArray(darr);
////
////                   madapter=new Picnicadapter(darr);
////                   list.setAdapter(madapter);
////
////
////               }
////
////
////
////
////
//////               editText=findViewById(R.id.picnicsearch);
//////
//////              editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
//////              editText.setOnQ
////           }
////
////           @Override
////           public void onCancelled(@NonNull DatabaseError databaseError) {
////
////           }
////       });
//
//        //Search Filter
////        search=findViewById(R.id.picnic_search);
////        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////                return false;
////            }
////        });
//
//        //recyclerview
//        recyclerView=findViewById(R.id.recyclerpicniclist);
//        recyclerView.setHasFixedSize(true);
//        //set layout as LinearLayout
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        //send query to firebaseDatabse
//        mFirebaseDatabase=FirebaseDatabase.getInstance();
//        reff=mFirebaseDatabase.getReference("picnic");
//
//    }
//    //load data into recycler view onStart
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<newpicnic,PicnicHolder>firebaseRecyclerAdapter=
//                new FirebaseRecyclerAdapter<newpicnic, PicnicHolder>(
//                        newpicnic.class, R.layout.picnic_list_item, PicnicHolder.class, reff
//                ) {
//                    @Override
//                    protected void populateViewHolder(PicnicHolder picnicHolder, newpicnic newpicnic, int i) {
//                        picnicHolder.setLocation(getApplicationContext(),newpicnic.getSpotName());
//                    }
//                };
//        //set adapter to tecyclerview
//        recyclerView.setAdapter(firebaseRecyclerAdapter);
////        search=findViewById(R.id.picnic_search);
////        //method for picniclist
////        private void fillpicniclist(){
////            Sname=new ArrayList<>();
////            Sname.addAll(new newpicnic.("picnic"));
////    }
//
//    }
//}
