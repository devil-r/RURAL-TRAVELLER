package com.example.souhardkataria.ruralt;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//Tushar (17CO149) -- start

public class MyPageAdapter extends FragmentStatePagerAdapter {
    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new Homeguide();
            case 1:
                return new ProfileGuide();

            case 2:
                return new Notification();
            case 3:
                return new ChatFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 4;
    }
}

//Tushar (17CO149) -- end