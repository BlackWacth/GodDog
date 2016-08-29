package com.hua.goddog.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hua.goddog.utils.L;

/**
 * Fragment基类
 * Created by hzw on 2016/8/23.
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    private ProgressDialog mProgressDialog;

    protected boolean isParepared = false;
    protected boolean isVisible = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutResId(), container, false);
        isParepared = true;
        init();
        return mRootView;
    }

    protected abstract int getLayoutResId();

    private void init() {
        initView();
        initData();
        addListener();
    }

    protected abstract void initView();

    protected void initData() {
        loadData();
    }

    protected abstract void addListener();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            loadData();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    protected void loadData() {
        L.i("BaseFragment : loadData");
        if(!isParepared || !isVisible) {
            return ;
        }
        onVisible();
    }

    protected abstract void onVisible();

    protected void onInVisible(){

    }

    protected <T extends View> T findView(int id) {
        if (mRootView == null) {
            throw new NullPointerException("RootView为空");
        }
        View view = mRootView.findViewById(id);
        if (view == null) {
            throw new NullPointerException("未找到指定View");
        }
        return (T) view;
    }

    protected void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    protected void showProgressDialog() {
        if(getContext() == null) {
            return;
        }
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("正在加载.....");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    protected void closeProgressDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
