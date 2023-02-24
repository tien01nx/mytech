package com.example.mytech.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mytech.fragments.ProfileFragment;
import com.example.mytech.fragments.StudentFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ProfileFragment();
            case 1: return new StudentFragment();

            default: return new ProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
