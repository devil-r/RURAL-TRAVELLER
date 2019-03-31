package com.example.souhardkataria.ruralt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class wallet extends AppCompatActivity {
    FloatingActionButton fab;
    String add_bal,c_bal,hh;
    double old,xx;
    wallet_class cc;
    final Context c= this;
    TextView mybal;
    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference Wallet=FirebaseDatabase.getInstance().getReference("Wallet");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        fab=findViewById(R.id.fab);
        mybal=findViewById(R.id.check_balance);
        Wallet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                cc=  dataSnapshot.child(uid).getValue(wallet_class.class);
                old=cc.money;
                hh=""+old;
                mybal.setText(hh);}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       // while(cc==null);
   // old=cc.money;
     //   hh=""+old;
       // mybal.setText(hh);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);

                View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                alertDialogBuilderUserInput.setView(mView);

                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderUserInput.setCancelable(false);
                alertDialogBuilderUserInput.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                        String y = userInputDialogEditText.getText().toString();
                        //y=String.format("%.2f",y);
                        try{
                            xx=Double
                                    .parseDouble(y);}
                        catch (NumberFormatException e){

                        }
                        old+=xx;
                        String kk=""+old;
                        Wallet.child(uid).setValue(new wallet_class(old));
                       mybal.setText(kk);
                    }
                });
                alertDialogBuilderUserInput.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });
    }

}
