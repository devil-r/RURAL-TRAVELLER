package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class packages_2 extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mdatabase;
    String val;
    String uname="";
    String notid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_2);
        Button accept=(Button) findViewById(R.id.maccept);
        Button reject=(Button) findViewById(R.id.mreject);
        accept.setOnClickListener(this);
        reject.setOnClickListener(this);
        final int loader = R.drawable.loader;
        Intent intent = getIntent();
        val="";
        final String  str = intent.getStringExtra("Village");
        notid=intent.getStringExtra("notid");
        final ImageView image = findViewById(R.id.imageView3);
        final ImageLoader imgLoader = new ImageLoader(getApplicationContext());
        final   TextView viln=findViewById(R.id.mVillageview);
        final  TextView ratn=findViewById(R.id.mRate);
        final TextView itenn=findViewById(R.id.mItenary);
        final TextView durnn=findViewById(R.id.mDuration);
        final ImageView imageView=findViewById(R.id.imageButton1);
        Query query =FirebaseDatabase.getInstance().getReference().child("Packages").child(str).child("Image");
        // Image View to show
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                val=dataSnapshot.getValue(String.class);
                imgLoader.DisplayImage(val, loader, image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Query query1=FirebaseDatabase.getInstance().getReference().child("Packages").child(str);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String rate,Dur,Itenary;
                rate=dataSnapshot.child("Rate").getValue(String.class);
                Dur=dataSnapshot.child("Duration").getValue(String.class);
                Itenary=dataSnapshot.child("Overview").getValue(String.class);
                viln.setText(str);
                ratn.setText(rate);
                itenn.setText(Itenary);
                durnn.setText(Dur);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        // Image url


        // ImageLoader class instance

        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView
        // val="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXjL7j3u_FL6TXAxN7B74JIaY9Qw3IMOfkQ3csGkr57blceF3M3A";


        mdatabase= FirebaseDatabase.getInstance().getReference("Notify_guide").child("noti");

        itenn.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.maccept:
            mdatabase.child(notid).removeValue();
            Toast.makeText(this, "You can chat with user on chat box", Toast.LENGTH_SHORT).show();
            finish();
            case R.id.mreject:
                mdatabase.child(notid).removeValue();
                finish();
    }}
}
