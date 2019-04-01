package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

// Souhard Kataria (17CO147) -- start

public class Displayeat extends AppCompatActivity {

    TextView Title,Contact,Address;
    ImageView imageView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayeat);

        Title= findViewById(R.id.DisplayTitlee);
        Contact = findViewById(R.id.DisplayContacte);
        Address = findViewById(R.id.DisplayAddresse);
        ratingBar = findViewById(R.id.RatingDisplaye);

        Intent intent = getIntent();
        Eat stay = (Eat) intent.getSerializableExtra("Obj");

        Title.setText(stay.Name.toUpperCase());
        Contact.setText(String.valueOf(stay.Contact));
        Address.setText(stay.Address);
        ratingBar.setRating(stay.rating);

    }
}

// Souhard Kataria (17CO147)  -- end
