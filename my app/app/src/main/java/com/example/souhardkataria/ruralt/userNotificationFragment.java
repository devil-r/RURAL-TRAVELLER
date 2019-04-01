package com.example.souhardkataria.ruralt;

//Akash(17CO2016) -- start


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class userNotificationFragment extends Fragment {

    DatabaseReference def;
    ArrayList<String> names=new ArrayList<String>();
    ArrayList<String> re=new ArrayList<>();
    ArrayList<notify_guide> n=new ArrayList<>();
    ListView l;
    public userNotificationFragment() {
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
        def= FirebaseDatabase.getInstance().getReference("Notify_user").child("noti");

        def.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    String id=def.getKey();
                    pakage_noti u=data.getValue(pakage_noti.class);

                    n.add(new notify_guide(u.name+" accepted your package request for "+u.village,u.village,id));
                }
                //adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final mylist2 list=new mylist2(view.getContext(),n);

        l.setAdapter(list);
        return view;
    }

}

//Akash(17CO2016) -- start