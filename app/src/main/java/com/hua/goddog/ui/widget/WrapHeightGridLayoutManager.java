package com.hua.goddog.ui.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hzw on 2016/8/26.
 */
public class WrapHeightGridLayoutManager extends GridLayoutManager {

    public WrapHeightGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public WrapHeightGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public WrapHeightGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//        super.onMeasure(recycler, state, widthSpec, heightSpec);
        int height = 0;
        int childCount = getItemCount();
        for(int i = 0; i < childCount; i++) {
            View child = recycler.getViewForPosition(i);
            measureChild(child, widthSpec, heightSpec);
            if(i % getSpanCount() == 0) {
                int measureHeight = child.getMeasuredHeight() + getDecoratedBottom(child);
                height += measureHeight;
            }
        }
        setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height);
    }
}
