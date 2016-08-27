package com.hua.goddog.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hua.goddog.R;
import com.hua.goddog.entity.HttpResult;
import com.hua.goddog.entity.gallery.GalleryList;
import com.hua.goddog.net.HttpManager;
import com.hua.goddog.net.Subscriber.MSubscriber;
import com.hua.goddog.ui.adapter.GalleryListAdapter;
import com.hua.goddog.ui.base.BaseFragment;
import com.hua.goddog.ui.widget.WrapHeightGridLayoutManager;
import com.hua.goddog.utils.L;
import com.hua.goddog.utils.ProgressDialogUtils;

import java.util.ArrayList;
import java.util.List;

public class GalleryListFragment extends BaseFragment {

    private static final String EXTRA_GALLERY_CLASSIFY_ID = "EXTRA_GALLERY_CLASSIFY_ID";

    private int mId;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<GalleryList.Tngou> mList = new ArrayList<>();

    private int currentPage = 1;
    private int totalPage;
    private int totalCounter;

    private GalleryListAdapter mAdapter;

    public GalleryListFragment () {

    }

    public static GalleryListFragment newInstance(int id) {
        GalleryListFragment fragment = new GalleryListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_GALLERY_CLASSIFY_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            mId = bundle.getInt(EXTRA_GALLERY_CLASSIFY_ID);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_gallery_list;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = findView(R.id.srl_gallery_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mRecyclerView = findView(R.id.rv_gallery_recycler_view);

//        GridLayoutManager gridLayoutManager = new WrapHeightGridLayoutManager(getActivity(), 3);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new GalleryListAdapter(R.layout.item_gallery, mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onVisible() {
        loadGalleryList();
    }

    private void loadGalleryList() {
        currentPage = 1;
        HttpManager.getInstance().getGalleryList(mId, currentPage, new MSubscriber(ProgressDialogUtils.showProgressDialog(getActivity())) {
            @Override
            protected void onSuccess(HttpResult httpResult) {
                if (mList == null) {
                    return ;
                }
                mList.clear();
                GalleryList galleryList = (GalleryList) httpResult;
                totalCounter = galleryList.getTotal();
                if(totalCounter % 10 == 0) {
                    totalPage = totalCounter / 10;
                } else {
                    totalPage = totalCounter / 10 + 1;
                }
                L.i(galleryList.getTngou().toString());
                mList.addAll(galleryList.getTngou());
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
