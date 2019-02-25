package com.example.souhardkataria.ruralt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class packages extends AppCompatActivity {
    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        Button b=(Button) findViewById(R.id.mBuy);

        mdatabase= FirebaseDatabase.getInstance().getReference("Guides");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=mdatabase.push().getKey();
                pakage_noti p=new pakage_noti("suresh");
                mdatabase.child("4O50aa84M8htAIZGS0qaPa0BrDJ3").child("noti").child(id).setValue(p);
                Toast.makeText(packages.this, "You bought this package", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
