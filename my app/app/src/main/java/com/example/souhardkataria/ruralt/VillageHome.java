package com.example.souhardkataria.ruralt;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class VillageHome extends AppCompatActivity {

    ViewPager viewPager;
    //LinearLayout linearLayout;
    //TextView dotset[];
    static String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.ToolbarVillHome);
        setSupportActionBar(toolbar);


       Intent intent = getIntent();
       str = intent.getStringExtra("Village");

       //getSupportActionBar().setTitle(str);


        setContentView(R.layout.activity_village_home);
        viewPager = findViewById(R.id.VillageHomePager);
        PagerAdapt adapt = new PagerAdapt(getSupportFragmentManager());
        viewPager.setAdapter(adapt);


    }




    public void Cicked(View view) {

        switch(view.getId())
        {
            case R.id.StayButton:
                Intent intent = new Intent(this,StayActivity.class);
              //  intent.putExtra("Village",str);
                startActivity(intent);
                break;
            case R.id.ButtonDecr:
                //Intent intent1 = new Intent(this,)
                startActivity(new Intent(this,DescriptionActivity.class));
                Toast.makeText(this, "Stay", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ButtonPackage:

                 startActivity(new Intent(this,packages.class));
                 break;

            case R.id.Eat:
                Intent i=new Intent(this, Eatactivity.class);
                startActivity(i);
                break;

            case R.id.ButtonVisit:
                startActivity(new Intent(this,Visitactivity.class));
                break;
        }
    }

    public static class trying {
    }
}
