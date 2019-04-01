package com.example.souhardkataria.ruralt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// Souhard (17CO147) -- start

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Asking for permissions Starts from here
        //Camera and Location and storage
        if(ContextCompat.checkSelfPermission(Start.this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(Start.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(Start.this, android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(Start.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(Start.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(Start.this,new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE},1);
        //Asking for location ended here
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button travel  = ( Button )findViewById(R.id.button);
        travel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, travelguidelogin.class);
                startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }


        });

        Button signup  = ( Button )findViewById(R.id.button8);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start.this, DisplayMessageActivity2.class);
                startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }


        });

        Button login  = ( Button )findViewById(R.id.start_login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start.this, Rural_Traveller.class);
                startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }


        });
        Button admin=findViewById(R.id.button2);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Adminlogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
}

// Souhard (17CO147) -- start
