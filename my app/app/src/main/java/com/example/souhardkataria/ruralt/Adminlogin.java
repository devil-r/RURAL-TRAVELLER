package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class Adminlogin extends AppCompatActivity {

    EditText email, pass;
    FirebaseAuth mAuth;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        email = findViewById(R.id.editText4);
        pass = findViewById(R.id.editText2);
        pb = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
//        email.setText("parthdodiya999@gmail.com");
//        pass.setText("123456");
    }

    /*    public void goSignup(View view)
        {
            startActivity(new Intent(this,Signup.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    */
    public void login(View view) {
        pb.setVisibility(View.VISIBLE);
        /*String eid=email.getText().toString().trim();
        String passw=pass.getText().toString().trim();
        if(eid.isEmpty())
        {
            email.setError("Email is required");
            email.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(eid).matches())
        {
            email.setError("Please enter a valid email");
            email.requestFocus();
        }
        if(passw.isEmpty())
        {
            pass.setError("Password is required");
            pass.requestFocus();
        }
        if(passw.length()<6)
        {
            pass.setError("min length of password is 6");
            pass.requestFocus();
        }*/
        String eid = email.getText().toString();
        String passw = pass.getText().toString();
        if (passw.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
        }
        if (eid.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
        }
        eid = eid.trim();
        passw = passw.trim();
        if (!eid.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(eid).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
        }

        if (!passw.isEmpty() && passw.length() < 6) {
            pass.setError("min length of password is 6");
            pass.requestFocus();
        }

        if (!(eid.isEmpty() || passw.isEmpty())) {
            mAuth.signInWithEmailAndPassword(eid, passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        pb.setVisibility(View.GONE);
                        Intent in = new Intent(getApplicationContext(), admindash.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(in);
                    } else {
                        pb.setVisibility(View.GONE);
                        //Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
