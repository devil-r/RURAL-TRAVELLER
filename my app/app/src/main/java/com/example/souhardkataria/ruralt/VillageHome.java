package com.example.souhardkataria.ruralt;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Avdhesh (17CO210)--start

public class VillageHome extends AppCompatActivity {

    ViewPager viewPager;
    //LinearLayout linearLayout;
    //TextView
    // dotset[];
    TextView Description101 ;//;= findViewById(R.id.HomeDescription);
    static String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_home);

        Toolbar toolbar = findViewById(R.id.ToolbarVillHome);
        //setSupportActionBar(toolbar);
      // getSupportActionBar().setTitle(str);



        Intent intent = getIntent();
        str = intent.getStringExtra("Village");
        //actionBar.setTitle(str);


        Description101 = (TextView) findViewById(R.id.ThisTextView);
        TextView textView = findViewById(R.id.Title);

        String ss = str+" is the place which captures your soul in the cold breeze of the mountains and mesmerizes the heart with the everpresent calmness that is so addictive that you cannot get out of it";
        Description101.setText(ss);
        textView.setText(str);

        setContentView(R.layout.activity_village_home);
        viewPager = findViewById(R.id.VillageHomePager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Packages/"+str);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                String s[] = new String[5];
                for(;i<6;i++)
                {
                    s[i-1] = dataSnapshot.child("Im"+i).getValue().toString();
                }
                PagerAdapt adapt = new PagerAdapt(getSupportFragmentManager(),s);
                viewPager.setAdapter(adapt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ImageView maps  = findViewById(R.id.MapButton);
        maps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VillageHome.this, MapsActivity.class);

                try {
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(VillageHome.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Mapsdisplay",e.getMessage());
                }
            }


        });


    }




    public void Cicked(View view) {

        Intent intent;
        switch (view.getId())
        {
            case R.id.StayButton:
                intent = new Intent(VillageHome.this,StayActivity.class);
                //Toast.makeText(this, "Stay Activity Should Open", Toast.LENGTH_SHORT).show();
                intent.putExtra("Village",str);
                startActivity(intent);break;

            case R.id.EatButton:
                intent = new Intent(VillageHome.this,Eatactivity.class);
                intent.putExtra("Village",str);
                startActivity(intent);break;

            case R.id.VisitButton:
                intent = new Intent(VillageHome.this,Visitactivity.class);
                intent.putExtra("Village",str);
                startActivity(intent);break;

            case R.id.ButtonPackage:
                intent = new Intent(VillageHome.this,packages.class);
                intent.putExtra("Village",str);
                startActivity(intent);break;
            case R.id.Star:
                intent = new Intent(VillageHome.this,VisitReview.class);
                intent.putExtra("Village",str);
                startActivity(intent);break;


        }
    }
}
//Avdhesh (17CO210)--start