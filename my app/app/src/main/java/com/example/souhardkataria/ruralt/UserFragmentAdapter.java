package com.example.souhardkataria.ruralt;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UserFragmentAdapter extends FragmentPagerAdapter {
    public UserFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i)
        {
            case 0:
                fragment=new fragment_village();
                break;
            case 1:
                //fragment=new fragment_myaccount();
                fragment=new fragment_myaccount();
                break;
            case 2:
                fragment=new fragment_myaccount();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
