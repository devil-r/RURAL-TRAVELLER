package com.example.souhardkataria.ruralt;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapt extends FragmentPagerAdapter {

    String[] s = new String[5];
    int[] bitmap = {R.drawable.index0,R.drawable.index1,R.drawable.index2,R.drawable.index3,R.drawable.index4,
            R.drawable.index5,R.drawable.index6,R.drawable.index7,R.drawable.index8,R.drawable.index9};
    public PagerAdapt(FragmentManager fm,String s[]) {
        super(fm);
        this.s = s;
    }

    @Override
    public Fragment getItem(int i) {

        BlankFragment blankFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Image",bitmap[i]);
        bundle.putString("Url",s[i]);
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
