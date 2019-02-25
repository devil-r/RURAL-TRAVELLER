package com.example.souhardkataria.ruralt;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class userdash extends AppCompatActivity implements fragment_village.CLICK {
    TextView Dvillage, Dpackage, Duser;
    Button btn;
    EditText sc;
    ImageButton ib;
    ViewPager vp;
    UserFragmentAdapter vpadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdash);
        Dvillage = findViewById(R.id.Dvillages);
        //Dpackage = findViewById(R.id.Dpackages);
        Duser = findViewById(R.id.Duser);
        vp = findViewById(R.id.vp);
        sc = findViewById(R.id.search_content);
        btn=findViewById(R.id.account_details);
        ib = findViewById(R.id.search_but);
        sc.setSelected(false);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchS = sc.getText().toString();
                if (!searchS.isEmpty()) {
                    Intent in = new Intent(getApplicationContext(), Search.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    in.putExtra("StringS", searchS);
                    startActivity(in);
                } else {
                    sc.setError("Please,Enter a Name of Village");
                }
            }
        });
        vpadapter=new UserFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(vpadapter);
        Duser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);
            }
        });
       /* Dpackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);
            }
        });*/
        Dvillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(0);
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                onChangePage(i);
            }



            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });*/
    }

    private void onChangePage(int i) {
        if(i==0)
        {
            setTextuni();
            Dvillage.setTextSize(17);
        }
        else if(i==1){
            setTextuni();
            Duser.setTextSize(17);
        }
        else
        {
            setTextuni();
            Duser.setTextSize(17);
        }
    }

    private void setTextuni() {
        Dvillage.setTextSize(13);
       // Dpackage.setTextSize(13);
        Duser.setTextSize(13);
    }

    @Override
    public void Villagestart(String str) {

        try{
        Intent intent = new Intent(this,VillageHome.class);
        intent.putExtra("Village",str);
        startActivity(intent);
    }
    catch (Exception e)
    {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    }
}

