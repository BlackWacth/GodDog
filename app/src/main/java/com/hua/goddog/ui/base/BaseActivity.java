package com.hua.goddog.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Activity基类
 * Created by hzw on 2016/8/23.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        init();
    }

    protected abstract int getLayoutResId();

    private void init() {
        initView();
        initData();
        addListener();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void addListener();

    protected <T extends View> T findView(int id) {
        View view = findViewById(id);
        if(view == null) {
            throw new NullPointerException("未找到指定View");
        }
        return (T) view;
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


}
