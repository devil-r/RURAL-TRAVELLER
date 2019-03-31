package com.example.souhardkataria.ruralt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registertravelguide extends AppCompatActivity {
    private static final String Tag = "register";
    private TextView mdate;
    private String gender;
    private EditText about;
    private String  ph,yrs;
    private String dd,pp;
    Spinner spinner;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText name,email,phno,ab,ad,y,pass;
    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdatabase= FirebaseDatabase.getInstance().getReference("registers");
        setContentView(R.layout.activity_registertravelguide);
        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        phno=(EditText) findViewById(R.id.phno);
        ab=(EditText) findViewById(R.id.about);
        ad=(EditText) findViewById(R.id.address);
        ph=phno.getText().toString();
        y=(EditText) findViewById(R.id.yrs);
        pass=findViewById(R.id.pass);
        pp=pass.getText().toString();
        Button submit=(Button) findViewById(R.id.register);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=mdatabase.push().getKey();
                com.example.souhardkataria.ruralt.register_users u=new com.example.souhardkataria.ruralt.register_users(pp,name.getText().toString(),email.getText().toString(),ph,dd,gender,spinner.getSelectedItem().toString(),
                        ad.getText().toString(),ab.getText().toString(),y.getText().toString(),id);
                int flag=0;
                String ee=email.getText().toString();
                if(name.getText().toString().isEmpty())
                {
                    flag=1;
                    name.setError("password is required");
                    name.requestFocus();
                }
                if(email.getText().toString().isEmpty())
                {
                    flag=1;
                    email.setError("email is empty");
                    email.requestFocus();
                }
                if(!ee.isEmpty()&&!Patterns.EMAIL_ADDRESS.matcher(ee).matches())
                {
                    flag=1;
                    email.setError("enter a valid email");
                    email.requestFocus();
                }
                if(ad.getText().toString().isEmpty())
                {
                    flag=1;
                    ad.setError("enter your address");
                    ad.requestFocus();
                }
                Pattern p = Pattern.compile("[0-9]");
                Matcher m = p.matcher(pp);
                boolean x = m.find();
                boolean ded = (pp.length() > 4);
                boolean z = x && ded;
                if (pp.isEmpty() || !z) {
                    pass.setError("length more than 4 and containing a number");
                    flag=1;
                } else {
                    pass.setError(null);
                }
                if(y.getText().toString().isEmpty())
                {
                    flag=1;
                    y.setError("enter about yourself");
                    y.requestFocus();
                }
                if(flag==0){
                    Toast.makeText(registertravelguide.this, "YOUR REGISTER REQUEST HAS BEEN SENT", Toast.LENGTH_LONG).show();
                    mdatabase.child(id).setValue(u);
                    Intent i=new Intent(registertravelguide.this,travelguidelogin.class);
                    finish();
                    startActivity(i);
            }}

        });
        spinner = findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.villages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        mdate=(Button) findViewById(R.id.dob);
        mdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        registertravelguide.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day
                );
                dialog.show();
            }
        });

        mDateSetListener=new DatePickerDialog.OnDateSetListener()

        {
            @Override
            public void onDateSet(DatePicker datepicker,int year,int month,int day)
            {
                month = month + 1;
                Log.d(Tag, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                mdate.setText(date);
                dd=date;
            }
        };}
    public void onclickradio(View v)
    {
        boolean checked =((RadioButton) v).isChecked();
        switch (v.getId())
        {
            case R.id.male:
                gender="male";
                break;
            case R.id.female:
                gender="female";
                break;
        }
    }

   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
