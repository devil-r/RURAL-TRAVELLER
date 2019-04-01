package com.example.souhardkataria.ruralt;

//Souhard Kataria (17CO147) --- Start

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Displayvisit extends AppCompatActivity {

    TextView Title,Contact,Address;
    ImageView imageView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayvisit);

        Title= findViewById(R.id.DisplayTitlev);
        Contact = findViewById(R.id.DisplayContactv);
        Address = findViewById(R.id.DisplayAddressv );
        ratingBar = findViewById(R.id.RatingDisplayv);

        Intent intent = getIntent();
        Visit stay = (Visit) intent.getSerializableExtra("Obj");

        Title.setText(stay.Name.toUpperCase());
        Contact.setText(String.valueOf(stay.Distance_Village)+ " from the Village");
        Address.setText(stay.Location);
        ratingBar.setRating(stay.User_Rating);

    }
}

//Souhard Kataria (17CO147) --- end