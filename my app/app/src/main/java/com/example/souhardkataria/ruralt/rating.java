package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
//Aashay (17CO201) -- start

public class rating extends AppCompatActivity {


    private DatabaseReference mDatabase;

    private ListView mUserList;


    private ArrayList<String> mUsernames=new ArrayList<>();
    private ArrayList<String> mKeys=new ArrayList<>();
    int votes;
    float rating;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Guide");
        mUserList=findViewById(R.id.user_list);
        final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mUsernames);
        mUserList.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                String value=dataSnapshot.getKey();//child("Object").child("Name").getValue().toString();
                mUsernames.add(value);
                String key=dataSnapshot.getKey();
                arrayAdapter.notifyDataSetChanged();

                mKeys.add(key);
                mUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        votes = dataSnapshot.child("Votes").getValue(Integer.class);



                        rating = dataSnapshot.child("Rating").getValue(Float.class);

                        String currentguidename= parent.getItemAtPosition(position).toString();
                        Intent guideintent=new Intent(rating.this,grate.class);
                        guideintent.putExtra("guide",currentguidename);
                        guideintent.putExtra("votes",votes);
                        guideintent.putExtra("rating",rating);
                        startActivity(guideintent);


                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String key=dataSnapshot.getKey();
                String value=dataSnapshot.getValue(String.class);
                int index=mKeys.indexOf(key);
                mUsernames.set(index,value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

//Aashay (17CO201) -- end