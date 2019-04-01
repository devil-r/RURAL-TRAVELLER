package com.example.souhardkataria.ruralt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

//  Akash (17CO206) -- start
public class Forum extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);


    }

    public void Upload(View view) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getCurrentUser().getUid();
        EditText editText = findViewById(R.id.edit_textForum);
        String query = editText.getText().toString();
        if(TextUtils.isEmpty(query))
            return ;
        editText.setText("");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Forum");
        Map<String,Object> map = new HashMap<>();
        map.put("Id",user);
        map.put("query",query);
        reference.push().updateChildren(map);
        Toast.makeText(this, "Your Query Has been Posted !!", Toast.LENGTH_SHORT).show();
    }
}

//  Akash (17CO206) -- end
