package com.hua.goddog.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 *
 * Created by hzw on 2016/8/24.
 */
public class MainAdapter extends FragmentStatePagerAdapter {

    private List<FragmentHolder> mList;

    public MainAdapter(FragmentManager fm, List<FragmentHolder> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position).mFragment;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).mTitle;
    }
}
