package com.example.souhardkataria.ruralt;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class myAccount_wishlist extends AppCompatActivity {

        ArrayList<String> Array = new ArrayList<>();
        FirebaseAuth mAuth;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_wishlist);
        mAuth = FirebaseAuth.getInstance();
        GetWishlist();
       adapter = new ArrayAdapter<String>(this,R.layout.activity_my_account_wishlist,Array);
        ListView listView = this.findViewById(R.id.list_myaccount);
        listView.setAdapter(adapter);


    }

    private void GetWishlist() {

        final String id = mAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Array.clear();
                if(dataSnapshot.child(id).exists())
                {
                    for(DataSnapshot snapshot: dataSnapshot.child(id).getChildren())
                    {
                        String name = null;
                                name= snapshot.getKey();
                        Array.add(name);
                    }
                    adapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
