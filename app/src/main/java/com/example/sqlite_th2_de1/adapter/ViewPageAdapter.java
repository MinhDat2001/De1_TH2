package com.example.sqlite_th2_de1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sqlite_th2_de1.fragment.MyInformation;
import com.example.sqlite_th2_de1.fragment.WorkFind;
import com.example.sqlite_th2_de1.fragment.WorkList;

public class ViewPageAdapter extends FragmentStatePagerAdapter {


    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new WorkList();
            case 1: return new MyInformation();
            case 2: return new WorkFind();
            default: return new MyInformation();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
