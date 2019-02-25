package com.example.souhardkataria.ruralt;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class DescriptionActivity extends AppCompatActivity {

    TextView Decriptions,Details;
    Toolbar toolbar;
    DatabaseReference reference;
    String description,btv;
    Village village;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Decriptions = findViewById(R.id.DescritionText);
        Details = findViewById(R.id.TextDetails);
       // toolbar = findViewById(R.id.VillageToolbar);
        //setSupportActionBar(toolbar);

        reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference pack = reference.child("Packages").child(VillageHome.str);
        reference = reference.child("Villages").child(VillageHome.str);

            pack.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {

                        description = dataSnapshot.child("Description").getValue(String.class);
                        btv = dataSnapshot.child("Best time to visit").getValue(String.class);
                    }
                      catch (Exception e)
                    {
                        Toast.makeText(DescriptionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    Decriptions.setText(description);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            reference.child("Object").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    village = dataSnapshot.getValue(Village.class);
                    String str = "Name - " + village.Name + "\nLocation - " + village.District + "," + village.State + "\nNearest City - " + village.Nearest_City +
                            "\nNearest Railway Station - " + village.Nearest_Railway_Station + "\nNearest Airport - " + village.Nearest_Airport +
                            "\nBest Time To Visit - " + btv + "\nWhat To Carry - " + "Torch,Warm Clothes,Medicines,Thermos Flask,Cash";
                    Details.setText(str);
                   // getSupportActionBar().setTitle(village.Name + " - " + village.pincode);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }
}
