package com.example.souhardkataria.ruralt;

// Parth Dodiya (17CO215) -- start

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.ViewPager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admindash extends AppCompatActivity  {
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView tgdata,fq;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindash);
        tgdata=findViewById(R.id.tgdata);
        fq=findViewById(R.id.fq);
        tgdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(0);
            }
        });
        fq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(1);
            }
        });
        pager=findViewById(R.id.pager);
        final PageAdapterAdmin pageAdapterAdmin=new PageAdapterAdmin(getSupportFragmentManager());
        pager.setAdapter(pageAdapterAdmin);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
               /* if(v>0.2||v<0.8) {
                    if (i == 1) {
                        pager.setCurrentItem(2);
                    } else {
                        pager.setCurrentItem(1);
                    }
                }*/
            }

            @Override
            public void onPageSelected(int i) {
                if(i==0)
                {
                    tgdata.setTextSize(15);
                    fq.setTextSize(12);
                }else
                {
                    tgdata.setTextSize(12);
                    fq.setTextSize(15);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}

// Parth Dodiya (17CO215) -- end