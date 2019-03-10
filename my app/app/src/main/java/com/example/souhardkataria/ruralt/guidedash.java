package com.example.souhardkataria.ruralt;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.souhardkataria.ruralt.R;

public class guidedash extends AppCompatActivity {

    static Context context;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidedash);
        context = this;

        viewPager = findViewById(R.id.pager_container);
        MyPageAdapter MyAdapter = new MyPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(MyAdapter);

        TabLayout tabLayout = findViewById(R.id.bottom_navigation);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_person_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notifications_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_insert_comment_black_24dp);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ChatFragment.list.clear();
    }
}
