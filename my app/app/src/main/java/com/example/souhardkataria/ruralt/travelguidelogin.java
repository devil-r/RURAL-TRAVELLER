package com.example.souhardkataria.ruralt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Tushar (17CO149) -- start

public class travelguidelogin extends AppCompatActivity implements View.OnClickListener{
    EditText emaillayout;
    EditText passwordlayout;
    String e,p;
    String TAG="Avdesh";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelguidelogin);
        mAuth=FirebaseAuth.getInstance();

        //Souhard Kataria (17CO147) --- Start
        LinearLayout temp=findViewById(R.id.tll);
        temp.getBackground().setAlpha(120);
        //Souhard Kataria (17CO147) --- end

        EditText ee=(EditText)findViewById(R.id.em);
        emaillayout=ee;
        EditText pp=(EditText)findViewById(R.id.pass);
        passwordlayout=pp;
        Button b=(Button) findViewById(R.id.button2);
        TextView x=(TextView) findViewById(R.id.reg);
        b.setOnClickListener(this);
        x.setOnClickListener(this);

    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.button2:
                int flag=0;
                emaillayout.setError(null);
                passwordlayout.setError(null);
                String e=emaillayout.getText().toString();
                String p=passwordlayout.getText().toString();
                if(p.isEmpty())
                {
                    flag=1;
                    passwordlayout.setError("password is required");
                    passwordlayout.requestFocus();
                }
                if(e.isEmpty())
                {
                    flag=1;
                    emaillayout.setError("email is empty");
                    passwordlayout.requestFocus();
                }
                e=e.trim();
                p=p.trim();
                if(!e.isEmpty()&&!Patterns.EMAIL_ADDRESS.matcher(e).matches())
                {
                    flag=1;
                    emaillayout.setError("enter a valid email");
                    emaillayout.requestFocus();
                }
                if(!p.isEmpty()&&p.length()<6)
                {
                    flag=1;
                    passwordlayout.setError("enter a valid password");
                    passwordlayout.requestFocus();
                }
                if(flag==0)
                {
                    mAuth = null;
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(emaillayout.getText().toString(), passwordlayout.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startActivity(new Intent(travelguidelogin.this,guidedash.class));
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(travelguidelogin.this, "Not a travel guide",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
                break;
            case R.id.reg:
                startActivity(new Intent(this,registertravelguide.class));
            default:
                break;
        }}

    public static boolean isemailvalid(String email)
    {
        Pattern p= Patterns.EMAIL_ADDRESS;
        Matcher m=p.matcher(email);
        return m.matches();
    }
    public static boolean ispassvalid(String password)
    {
        return password.length()>=6;
    }
    /* public void check()
     {
         if(!validate())
         {
             return;
         }
         Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(emaillayout.getText().toString(),passwordlayout.getText().toString())
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {
                             // Sign in success, update UI with the signed-in user's information
                             Log.d(TAG, "signInWithEmail:success");
                             FirebaseUser user = mAuth.getCurrentUser();
                             Toast.makeText(getApplicationContext(),"login is successfull",Toast.LENGTH_LONG).show();
                             startActivity(new Intent(MainActivity.this, guide_page.class));
                         } else {
                             // If sign in fails, display a message to the user.
                             Log.w(TAG, "signInWithEmail:failure", task.getException());
                             Toast.makeText(MainActivity.this, "Authentication failed.",
                                     Toast.LENGTH_SHORT).show();

                         }

                         // ...
                     }
                 });
     }*/
    /*private void check(String email,String password)
    {
        if(validate(email,password))
        {
            startActivity(new Intent(MainActivity.this, guide_page.class));
        }
        else
        {
            Intent i=getIntent();
            finish();
            startActivity(i);
        }

    }*/
    private void alertDialog()
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Please ensure all Questions are answered");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }


};

// Tushar (17CO149) -- end


