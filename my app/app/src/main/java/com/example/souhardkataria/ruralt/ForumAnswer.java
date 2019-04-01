package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
// Parth (17CO215) -- start

public class ForumAnswer extends AppCompatActivity {
    TextView quest;
    EditText ans;
    Button save;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_answer);
        quest=findViewById(R.id.question);
        ans=findViewById(R.id.answer);
        save=findViewById(R.id.save);
        Intent in=getIntent();
        final String id=in.getStringExtra("id");
        mRef= FirebaseDatabase.getInstance().getReference("Forum");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Question qn=dataSnapshot.child(id).getValue(Question.class);
                quest.setText(qn.query);
                try {
                    ans.setText(qn.answer);
                }catch (Exception e)
                {}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer=ans.getText().toString();
                mRef.child(id).child("answer").setValue(answer);
                finish();
            }
        });
    }
}

// Parth (17CO215) -- end
