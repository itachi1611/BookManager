package com.fox.bookmanager.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fox.bookmanager.fragment.FragmentDay;
import com.fox.bookmanager.fragment.FragmentMonth;
import com.fox.bookmanager.fragment.FragmentWeek;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new FragmentDay();
                break;
            case 1:
                fragment = new FragmentWeek();
                break;
            case 2:
                fragment = new FragmentMonth();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Day";
                break;
            case 1:
                title = "Week";
                break;
            case 2:
                title = "Month";
                break;
        }
        return title;
    }
}
