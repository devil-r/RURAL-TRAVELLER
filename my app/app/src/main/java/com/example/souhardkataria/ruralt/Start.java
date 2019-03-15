package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
