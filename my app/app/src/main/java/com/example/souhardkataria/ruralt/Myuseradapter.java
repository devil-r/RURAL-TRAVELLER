package com.example.souhardkataria.ruralt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Myuseradapter extends FragmentStatePagerAdapter {

    public Myuseradapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new userHomefragment();
            case 1:
                return new userExploreFragment();

            case 2:
                return new userNotificationFragment();
            case 3:
                return new userAccountFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 4;
    }
}
