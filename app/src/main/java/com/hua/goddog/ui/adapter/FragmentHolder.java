package com.hua.goddog.ui.adapter;

import android.support.v4.app.Fragment;

/**
 * Created by hzw on 2016/8/24.
 */
public class FragmentHolder {

    public String mTitle;
    public Fragment mFragment;

    public FragmentHolder(String title, Fragment fragment) {
        mTitle = title;
        mFragment = fragment;
    }
}
