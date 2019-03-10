package com.example.souhardkataria.ruralt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class change_pass extends AppCompatActivity {

    private EditText oldpass;
    private EditText newPass;
    private static final String TAG = "ChangePassActivity";
    private Button resetpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);





        oldpass = (EditText) findViewById(R.id.old_pass);
        newPass=(EditText) findViewById(R.id.new_pass);
        resetpass = (Button) findViewById(R.id.reset_pass);




        resetpass.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (!validate()) {
                    onFailed();
                    return;
                }
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                 String email = user.getEmail();

                 AuthCredential credential = EmailAuthProvider.getCredential(email, oldpass.getText().toString());


                //user.updatePassword(newPass.getText().toString());
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //Toast.makeText(change_pass.this, "Credential authenticated", Toast.LENGTH_SHORT).show();
                                if (task.isSuccessful()) {
                                    user.updatePassword(newPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "Password updated");
                                                Toast.makeText(change_pass.this, "Password Changed", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Log.d(TAG, "Error password not updated");
                                                Toast.makeText(change_pass.this, "Password not changed+ "+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Log.d(TAG, "Error auth failed");
                                    Toast.makeText(change_pass.this, "Incorrect Old Password!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            });


    }

    public boolean validate()
    {
        boolean valid =true;

        if(TextUtils.isEmpty(oldpass.getText().toString()))
        {
            oldpass.setError("This field is required");
            valid=false;
        }
        else{
            oldpass.setError(null);
        }

        if(TextUtils.isEmpty(newPass.getText().toString()))
        {
            newPass.setError("This field is required");
            valid=false;
        }
        else{
            newPass.setError(null);
        }

        return valid;
    }

    public void onFailed() {
        Toast.makeText(getBaseContext(), "Password could not be changed", Toast.LENGTH_LONG).show();

        resetpass.setEnabled(true);
    }
}
