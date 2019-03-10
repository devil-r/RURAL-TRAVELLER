package com.example.souhardkataria.ruralt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapterAdmin extends FragmentStatePagerAdapter {

    public PageAdapterAdmin(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0)
        {
            return new TabOne();
        }else
        {
            return new TabTwo();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
