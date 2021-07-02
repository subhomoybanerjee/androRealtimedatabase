package com.example.testand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;



public class showdata extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    adapter adapter;
    ArrayList<user> list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        Intent intent= getIntent();
        String a = intent.getStringExtra(product.s);

        recyclerView=findViewById(R.id.recview);
        database=FirebaseDatabase.getInstance().getReference().child(a);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        adapter=new adapter(this,list);
        recyclerView.setAdapter(adapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    user us=dataSnapshot.getValue(user.class);
                    Log.i(TAG, "onDataChange: value of topic is: "+ Objects.requireNonNull(us).getCompany());
                    Log.i(TAG, "onDataChange: value of order is: "+Objects.requireNonNull(us).getProduct());
                    list.add(us);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });

    }
}