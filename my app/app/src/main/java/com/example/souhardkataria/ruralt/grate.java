package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class grate extends AppCompatActivity {
    private String GuideName;
    private int votes;
    private float rating;
    float r;
    RatingBar mRatingbar;
    float rater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grate);
        mRatingbar=findViewById(R.id.ratingBar);
        //GuideName=getIntent().getExtras().get("guide").toString();
        votes=23;
        rating= (float) 4.1;
        mRatingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {

                rater=ratingBar.getRating();

                r=(rater+(rating*votes))/(votes+1);

            }
        });

    }

    public void Thankyou(View view) {
        Toast.makeText(this, "Thank You For Your Feedback", Toast.LENGTH_SHORT).show();

        FirebaseDatabase.getInstance().getReference().child("Guide").child("Samar").child("Rating").setValue(r);
        FirebaseDatabase.getInstance().getReference().child("Guides").child("Samar").child("Votes").setValue(votes+1);
        Intent rateintent=new Intent(grate.this,rating.class);
        //rateintent.putExtra("rate",r);
        finish();
    }


}
//Aashay Maheshwarkar