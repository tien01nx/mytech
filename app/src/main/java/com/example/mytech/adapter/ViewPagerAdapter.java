package com.example.mytech.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mytech.fragments.AdminHomeFragment;
import com.example.mytech.fragments.ProfileFragment;
import com.example.mytech.fragments.StudentFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new AdminHomeFragment();
            case 1: return new StudentFragment();
            case 2: return new ProfileFragment();
            default: return new AdminHomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
