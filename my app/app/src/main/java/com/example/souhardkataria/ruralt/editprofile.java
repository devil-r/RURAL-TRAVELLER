package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class editprofile extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 101;
    EditText name,email,gender,dob;
    String Name,Email,Gender,Dob;
    CircleImageView photo;
    Uri upi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        name = findViewById(R.id.Name);
        email = findViewById(R.id.Email);
        gender = findViewById(R.id.Gender);
        dob = findViewById(R.id.Dob);

        photo=findViewById(R.id.photo);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHOOSE_IMAGE && resultCode ==RESULT_OK && data!=null && data.getData()!=null)
        {
            upi=  data.getData();
            try {
                Bitmap bim= MediaStore.Images.Media.getBitmap(getContentResolver(),upi);
                photo.setImageBitmap(bim);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showImageChooser()
    {
        Intent in=new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(in,"Select Profile Image"),CHOOSE_IMAGE);
    }

    public void clicked(View view){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();
        HashMap<String, String> map = new HashMap();
        Name = name.getText().toString();
        Email = email.getText().toString();
        Gender = gender.getText().toString();
        Dob = dob.getText().toString();

        map.put("Name", Name);
        map.put("Email", Email);
        map.put("Gender", Gender);
        map.put("Date Of Birth", Dob);

        reference.child("Database Akshit").setValue(map);

        Intent i = new Intent(this,Rural_Traveller.class);
        startActivity(i);
    }
}
