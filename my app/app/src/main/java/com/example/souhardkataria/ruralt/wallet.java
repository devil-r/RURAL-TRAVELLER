package com.example.souhardkataria.ruralt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class wallet extends AppCompatActivity {
    FloatingActionButton fab;
    String m_text,c_bal;
    TextView mybal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        fab=findViewById(R.id.fab);
        mybal=findViewById(R.id.check_balance);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
    }
    public void opendialog()
    {
        android.app.AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Add Money");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
