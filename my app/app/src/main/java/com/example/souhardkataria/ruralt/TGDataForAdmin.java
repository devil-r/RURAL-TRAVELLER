package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TGDataForAdmin extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tgdata_for_admin);
        final TextView name=findViewById(R.id.name);
        final TextView email=findViewById(R.id.email);
        final TextView phone=findViewById(R.id.phone);
        final TextView dob=findViewById(R.id.dob);
        final TextView gender=findViewById(R.id.gender);
        final TextView village=findViewById(R.id.village);
        final TextView experience=findViewById(R.id.experience);
        final TextView address=findViewById(R.id.address);
        final TextView about=findViewById(R.id.about);
        final Button accept,reject;
        accept=findViewById(R.id.acceptTG);
        reject=findViewById(R.id.rejectTG);
        mAuth=FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("registers");
        Intent in=getIntent();
        final int id=in.getIntExtra("id",0);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot k:dataSnapshot.getChildren())
                {
                    final String ids=k.getKey();
                    final register_users u=k.getValue(register_users.class);
                    if(i==id)
                    {
                        name.setText(u.name);
                        email.setText(u.email);
                        phone.setText("7393728492");
                        dob.setText(u.dob);
                        gender.setText(u.gender);
                        village.setText(u.village);
                        experience.setText(u.yre);
                        address.setText(u.address);
                        about.setText(u.about);
                        reject.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                myRef.child(ids).removeValue();
                                finish();
                            }
                        });
                        accept.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                database.getReference("Guides").child(ids).setValue(u);

                                myRef.child(ids).removeValue();
                                finish();
                            }
                        });

                    }
                    i=i+1;
                }

                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
