package com.example.souhardkataria.ruralt;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {

    DatabaseReference def;
    ArrayList<String> names=new ArrayList<String>();
    ArrayList<String> re=new ArrayList<>();
    ArrayList<notify_guide> n=new ArrayList<>();
    ListView l;
    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        l=(ListView) view.findViewById(R.id.list);
        //final ArrayAdapter<String> adapter=new ArrayAdapter<String>(guide_page.this,android.R.layout.simple_list_item_1,items);
        //l.setAdapter(adapter);
        def= FirebaseDatabase.getInstance().getReference("registers");

        def.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    register_users u=data.getValue(register_users.class);
                    names.add(u.name);
                    re.add(u.village);
                    n.add(new notify_guide(u.name+" wants to buy this package from "+u.village));
                }
                //adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mylist list=new mylist(view.getContext(),n);
        l.setAdapter(list);
        return view;
    }

}
