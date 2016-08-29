package com.hua.goddog.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hzw on 2016/8/27.
 */
public class SapceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSapce;
    private int mSpan;

    public SapceItemDecoration(int sapce, int span) {
        mSapce = sapce;
        mSpan = span;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mSapce;
        if (parent.getChildLayoutPosition(view) % mSpan == 0) {
            outRect.left = 0;
        } else {
            outRect.left = mSapce;
        }
    }
}
