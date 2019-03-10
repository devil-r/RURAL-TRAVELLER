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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TabOne extends Fragment {
    View view;
    ListView lv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_one,container,false);
        lv=view.findViewById(R.id.tgadminlist);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String[] array={"hello","World"};
        DatabaseReference myRef = database.getReference("message");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.listview_textbox,array);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent here
                Intent in=new Intent(getContext(),TGDataForAdmin.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }
        });
        return view;
    }
}
