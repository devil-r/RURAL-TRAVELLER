package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admindash extends AppCompatActivity  {
    FirebaseDatabase database;
    ListView lvv;
    DatabaseReference myRef;
    Button goTravelGuideList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindash);
        goTravelGuideList=findViewById(R.id.button2);
 /*       goTravelGuideList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),TravelGuides.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }
        });
*/
        Button guiderate  = ( Button )findViewById(R.id.button4);
        guiderate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(admindash.this, rating.class);
                startActivity(i);

            }


        });
        lvv=findViewById(R.id.listView);
        final ArrayList<register_users> array=new ArrayList<>();
        final ArrayList<String> arrayID=new ArrayList<>();
        int i;
        /*for(i=0;i<5;i++)
        {
            array.add(new register_users(i));
        }*/
        try {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.child("registers").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //for(int i=0;i<2;i++) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    array.clear();
                    // myRef.child("guide-2f253").child("hello").setValue("Hello");
                    //Toast.makeText(getApplicationContext(),"lol "+dataSnapshot.getChildrenCount(),Toast.LENGTH_LONG).show();
                    for (DataSnapshot child : children) {
                        register_users ch = child.getValue(register_users.class);
                        array.add(ch);
                        arrayID.add(child.getKey());
                        // Toast.makeText(getApplicationContext(),ch.name,Toast.LENGTH_LONG).show();
                    }
                    TravelGuideAdapter ada = new TravelGuideAdapter(array, getApplicationContext(), arrayID);
                    try {
                        //Toast.makeText(getApplicationContext(),"before "+array.get(0).name,Toast.LENGTH_LONG).show();
                        lvv.setAdapter(ada);
                        //Toast.makeText(getApplicationContext(),"after",Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Exception " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                //}

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        /*final register_users[] array=new register_users[5];
        for(int i=0;i<5;i++) {
            array[i] = new register_users(i);
        }*/



    }
}
