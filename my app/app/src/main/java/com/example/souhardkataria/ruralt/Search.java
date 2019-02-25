package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    TextView tv,res;
    DatabaseReference mRef;
    ArrayList<String> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tv=findViewById(R.id.conf);
        Intent in=getIntent();
        final String sc=in.getStringExtra("StringS").trim();
        tv.setText("Searched for: "+sc);
        res=findViewById(R.id.res_s);
        res.setText("Searching...");
        mRef= FirebaseDatabase.getInstance().getReference();
        mRef.child("Villages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if(dataSnapshot.child(sc).exists())
               {
                    res.setText(sc);
               }
               else
               {
                   res.setText("Village not Found");
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
