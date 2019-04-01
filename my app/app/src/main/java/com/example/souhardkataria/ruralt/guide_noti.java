package com.example.souhardkataria.ruralt;

//Tushar (17CO149) -- start
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class guide_noti extends BaseAdapter {
    ArrayList<String> lvList;
    Context context;
    LayoutInflater layoutInflater;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    public guide_noti(ArrayList<String> lvList, Context context) {
        this.lvList = lvList;
        this.context = context;
        mAuth=FirebaseAuth.getInstance();
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            database=FirebaseDatabase.getInstance();
            myRef=database.getReference();
        }catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getCount() {
        return lvList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=layoutInflater.inflate(R.layout.activity_guide_noti,viewGroup,false);
        TextView name=view1.findViewById(R.id.Tname);
        name.setText(lvList.get(i));
        //final String id=arrayID.get(i);
        //final register_users obj=lvList.get(i);
        /*accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Authenticated").child(id).setValue(obj);
                myRef.child("registers").child(id).removeValue();
                Toast.makeText(context,"Onclick Travel ",Toast.LENGTH_LONG).show();
                //Code for delete
                //myRef.child("registers")
            }
        });*/



        // Toast.makeText(context ,lvList.get(i).name,Toast.LENGTH_LONG).show();
        return view1;
    }}

//Tushar (17CO149) -- start