package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class guidesuccess extends AppCompatActivity {
    DatabaseReference def;
    ArrayList<String> items=new ArrayList<String>();
    ArrayList<pakage_noti> re=new ArrayList<pakage_noti>();
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidesuccess);
        l=(ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(guidesuccess.this,android.R.layout.simple_list_item_1,items);
        l.setAdapter(adapter);
        def= FirebaseDatabase.getInstance().getReference("Guides");
        def=def.child("4O50aa84M8htAIZGS0qaPa0BrDJ3").child("noti");

        def.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    pakage_noti  u=data.getValue(pakage_noti.class);
                    items.add(u.name);
                    re.add(u);
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       /* l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(guide_page.this,Show_register_details.class);
        i.putExtra("register_details",  re.get(position));
            startActivity(i);
            }
        });*/
    }
}