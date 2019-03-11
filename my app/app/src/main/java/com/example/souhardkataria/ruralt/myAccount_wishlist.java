package com.example.souhardkataria.ruralt;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
       adapter = new ArrayAdapter<String>(this,R.layout.listview_textbox,Array);
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
                    try {
                        for (DataSnapshot snapshot : dataSnapshot.child(id).child("Liked").getChildren()) {
                            String name = null;
                            name = snapshot.getKey();
                            Array.add(name);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    catch (Exception e)
                    {
                        Log.d("WishlistActivity",e.getMessage());
                        Toast.makeText(myAccount_wishlist.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
