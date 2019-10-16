package com.example.testperoject.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间： 2019/7/8 10:45
 * 作者： Lee
 * 描述：
 */
public class MyVPAdapter extends FragmentPagerAdapter {

    private List<String> titles;
    private List<Fragment> fragments;

    public MyVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void setTitles(int index, String title, Fragment fragment) {
        titles.add(index, title);
        fragments.add(index, fragment);
        notifyDataSetChanged();
    }

    public void setTitles(List<String> mTitles, List<Fragment> mFragments) {
        this.titles = mTitles;
        this.fragments = mFragments;
        notifyDataSetChanged();
    }

}



