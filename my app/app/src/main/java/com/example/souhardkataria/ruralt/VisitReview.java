package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class VisitReview extends AppCompatActivity {

    float r;
    RatingBar mRatingbar;
    float rater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_review);
        Intent intent = getIntent();
        mRatingbar=findViewById(R.id.VisitBar);

        mRatingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {

                rater=ratingBar.getRating();
            }
        });

    }

    public void RateIt(View view) {

        Toast.makeText(this, "Thank You For Your Feedback", Toast.LENGTH_SHORT).show();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference().child(user).child("Rating").child(VillageHome.str).setValue(r).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent rateintent=new Intent(VisitReview.this,VillageHome.class);
                //rateintent.putExtra("rate",r);
                startActivity(rateintent);

            }
        });

    }
}
