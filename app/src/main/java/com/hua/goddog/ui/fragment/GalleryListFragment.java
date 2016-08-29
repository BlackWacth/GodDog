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
import android.view.ViewTreeObserver;

import com.hua.goddog.R;
import com.hua.goddog.entity.HttpResult;
import com.hua.goddog.entity.gallery.GalleryList;
import com.hua.goddog.net.HttpManager;
import com.hua.goddog.net.Subscriber.MSubscriber;
import com.hua.goddog.ui.adapter.GalleryListAdapter;
import com.hua.goddog.ui.base.BaseFragment;
import com.hua.goddog.ui.widget.RecyclerOnScrollListener;
import com.hua.goddog.ui.widget.SapceItemDecoration;
import com.hua.goddog.ui.widget.WrapHeightGridLayoutManager;
import com.hua.goddog.utils.L;
import com.hua.goddog.utils.ProgressDialogUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class GalleryListFragment extends BaseFragment {

    private static final String EXTRA_GALLERY_CLASSIFY_ID = "EXTRA_GALLERY_CLASSIFY_ID";
    private static final int SPAN_COUNT = 3;
    private static final int SPAN_SAPCE = 8;
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        gridLayoutManager.setAutoMeasureEnabled(true);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new SapceItemDecoration(SPAN_SAPCE, SPAN_COUNT));
        mAdapter = new GalleryListAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void addListener() {
        //测量RecyclerView中item的宽度
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int columnWidth = (mRecyclerView.getWidth() - mRecyclerView.getPaddingLeft() - mRecyclerView.getPaddingRight() - SPAN_SAPCE * (SPAN_COUNT - 1)) / SPAN_COUNT;

                L.i("columnWidth = " + columnWidth);
                mAdapter.setItemHeight(columnWidth);
                mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        //下拉刷新监听器。
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HttpManager.getInstance().getGalleryList(mId, currentPage, new Subscriber<GalleryList>() {
                    @Override
                    public void onCompleted() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(GalleryList galleryList) {
                        if (mList == null) {
                            return ;
                        }
                        mList.clear();
                        totalCounter = galleryList.getTotal();
                        if(totalCounter % 10 == 0) {
                            totalPage = totalCounter / 10;
                        } else {
                            totalPage = totalCounter / 10 + 1;
                        }
                        mList.addAll(galleryList.getTngou());
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerOnScrollListener() {
            @Override
            protected void onScrolledBottom() {
                if(currentPage >= totalCounter) {
                    currentPage = totalPage;
                } else {
                    currentPage++;
                }
                L.i("onScrolledBottom : currentPage : " + currentPage);
                HttpManager.getInstance().getGalleryList(mId, currentPage, new Subscriber<GalleryList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(GalleryList galleryList) {
                        if (mList == null) {
                            return ;
                        }
                        int startPosition = mAdapter.getItemCount() - 1;
                        int size = galleryList.getTngou().size();
                        L.i("onScrolledBottom : tngou : " + galleryList.getTngou());
                        mList.addAll(galleryList.getTngou());
                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyItemRangeChanged(startPosition, size);
                    }
                });
            }
        });
    }

    @Override
    protected void onVisible() {
        L.i("GalleryListFragment : onVisible");
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
                mList.addAll(galleryList.getTngou());
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
