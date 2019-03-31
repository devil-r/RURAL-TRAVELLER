package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class display_profile extends AppCompatActivity {

    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        final TextView name=findViewById(R.id.Name);
        final TextView email=findViewById(R.id.Email);
        final TextView gender=findViewById(R.id.Gender);
        final TextView dob=findViewById(R.id.Dob);
        final TextView mob=findViewById(R.id.editText);
        final TextView add=findViewById(R.id.editText2);
        final ImageView image = findViewById(R.id.photo);
        Button b=(Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(display_profile.this,editprofile.class));
            }
        });
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                UserAK us=dataSnapshot.getValue(UserAK.class);
                while(us==null);
                name.setText(us.Name);
                email.setText(us.Email);
                gender.setText(us.Gender);
                dob.setText(us.Date_of_Birth);
                mob.setText(us.Mob);
                add.setText(us.Add);
                url = us.image;
                Glide.with(getApplicationContext()).load(url).into(image);
            }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
