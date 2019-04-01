package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
// Parth (17CO215) -- start

public class TabOne extends Fragment {
    View view;
    ListView lv;
    ArrayList<register_users> array2;
    ArrayList<String> ids;
    ArrayList<String> names;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_one,container,false);
        lv=view.findViewById(R.id.tgadminlist);
        final register_users[] array1=new register_users[100];
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("registers");
        array2=new ArrayList<>();
        ids=new ArrayList<>();
        names=new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                names.clear();
                for(DataSnapshot k:dataSnapshot.getChildren())
                {
                    register_users u=k.getValue(register_users.class);
                    //array2.add(u);
                    //ids.add(k.getKey());
                    names.add(u.name);
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.listview_textbox,names);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Intent here
                        Intent in=new Intent(getContext(),TGDataForAdmin.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        in.putExtra("id",i);
                        startActivity(in);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }
}

// Parth (17CO215) -- end