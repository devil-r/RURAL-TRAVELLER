package com.example.souhardkataria.ruralt;
    //Akshit Patel start
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class editprofile extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 101;
    EditText name,email,gender,dob,mob,add;
    String Name,Email,Gender,Dob,Mob,Add,Url;
    Task<Uri> task;
    CircleImageView photo;
    Uri upi=null;
    String uid;
    HashMap<String, String> map = new HashMap();
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        name = findViewById(R.id.Name);
        email = findViewById(R.id.Email);
        gender = findViewById(R.id.Gender);
        dob = findViewById(R.id.Dob);
        mob = findViewById(R.id.Mob);
        add = findViewById(R.id.Add);
        photo=findViewById(R.id.photo1);
        Intent in=getIntent();
        uid=in.getStringExtra("id");
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();

            }
        });
        storageReference = FirebaseStorage.getInstance().getReference();
        String user = FirebaseAuth.getInstance().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(user);
    }
    private void uploadFile(){
        if(upi!=null) {


            StorageReference riversRef = storageReference.child("images");
            String user = FirebaseAuth.getInstance().getUid();
            riversRef.child(user).putFile(upi)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            task = taskSnapshot.getStorage().getDownloadUrl();
                            while(!task.isComplete());
                            Url = task.getResult().toString();
                           // databaseReference.child("image").setValue(Url);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHOOSE_IMAGE && resultCode ==RESULT_OK && data!=null && data.getData()!=null)
        {
            upi=  data.getData();
            try {
                Bitmap bim= MediaStore.Images.Media.getBitmap(getContentResolver(),upi);
                uploadFile();
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

//        while(upi!=null && (Url ==null|| Url == "" ));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();
        int flag=1;

        Name = name.getText().toString();
        Email = email.getText().toString();
        Gender = gender.getText().toString();
        Dob = dob.getText().toString();
        Mob = mob.getText().toString();
        Add = add.getText().toString();
        if(Name.isEmpty()){
            name.setError("This field is required");
        }
        if(Email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("This field is required");
        }
        if(Gender.isEmpty()){
            gender.setError("This field is required");
        }
        if(Dob.isEmpty()){
            dob.setError("This field is required");
        }
        if(Mob.isEmpty() || Mob.length()!=10){
            mob.setError("This field is required");
        }
        if(Add.isEmpty() || Add.length()<=10){
            add.setError("This field is required");
        }
        map.put("Name", Name);
        map.put("Email", Email);
        map.put("Gender", Gender);
        map.put("Date_of_Birth", Dob);
        map.put("MobileNumber",Mob);
        map.put("Address",Add);
        map.put("image",Url);
        String user = FirebaseAuth.getInstance().getUid();

        reference.child("Users").child(user).setValue(map);
        if(!Name.isEmpty() && !Email.isEmpty() && !Gender.isEmpty() && !Dob.isEmpty() && !Mob.isEmpty() && !Add.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches() && Mob.length()==10) {
            Intent i = new Intent(this, Rural_Traveller.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }
}
//    //Akshit Patel end