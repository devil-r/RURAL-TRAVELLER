package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class display_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        final EditText name=findViewById(R.id.Name);
        final EditText email=findViewById(R.id.Email);
        final EditText gender=findViewById(R.id.Gender);
        final EditText dob=findViewById(R.id.Dob);
        final EditText mob=findViewById(R.id.editText);
        final EditText add=findViewById(R.id.editText2);
        Button b=(Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(display_profile.this,editprofile.class));
            }
        });
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserAK us=dataSnapshot.getValue(UserAK.class);
                name.setText(us.Name);
                email.setText(us.Email);
                gender.setText(us.Gender);
                dob.setText(us.Date_of_Birth);
                mob.setText(us.Mob);
                add.setText(us.Add);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
