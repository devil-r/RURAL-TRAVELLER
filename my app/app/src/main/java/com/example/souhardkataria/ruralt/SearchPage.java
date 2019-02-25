package com.example.souhardkataria.ruralt;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchPage extends AppCompatActivity {
    TextView searched_S;
    DatabaseReference mRef;
    ArrayList<String> villages;
    ArrayList<HashMap<String,String>> villageList;
    ArrayAdapter<String> adapter;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        searched_S=findViewById(R.id.searched_string);
        //Intent in=getIntent();
        //final String sc=in.getStringExtra("StringS");
       // searched_S.setText("Searched for: "+sc);
        result=findViewById(R.id.result);
  /*      try {
            mRef = FirebaseDatabase.getInstance().getReference();
            mRef.child("Villages").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String s;

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        s=child.getKey();
                        villages.add(s);
                        if(s.equals(sc))
                        {
                            result.setText(sc);
                            result.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //GO to village Activity where village name is sc
                                }
                            });
                        }

                    }
                    adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item,R.id.product_name,sarray);
                    CharSequence charSequence=sc;
                    adapter.getFilter().filter(charSequence);
                    lv.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
*/
        }
}
