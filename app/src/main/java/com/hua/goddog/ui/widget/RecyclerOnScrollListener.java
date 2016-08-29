package com.hua.goddog.ui.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hzw on 2016/8/29.
 */
public abstract class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int mLastVisibleItem;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            mLastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(newState == recyclerView.SCROLL_STATE_IDLE && mLastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()) {
            onScrolledBottom();
        }
    }

    protected abstract void onScrolledBottom();
}
