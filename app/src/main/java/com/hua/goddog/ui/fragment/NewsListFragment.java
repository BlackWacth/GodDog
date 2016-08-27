package com.hua.goddog.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hua.goddog.R;
import com.hua.goddog.entity.HttpResult;
import com.hua.goddog.entity.hotspot.NewsList;
import com.hua.goddog.global.C;
import com.hua.goddog.net.HttpManager;
import com.hua.goddog.net.Subscriber.MSubscriber;
import com.hua.goddog.ui.activity.hotspot.ShowNewsActivity;
import com.hua.goddog.ui.adapter.NewsListAdapter;
import com.hua.goddog.ui.base.BaseFragment;
import com.hua.goddog.utils.L;
import com.hua.goddog.utils.ProgressDialogUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;


/**
 * 热点模块
 */
public class NewsListFragment extends BaseFragment {

    private static final String EXTRA_NEWS_ID = "EXTRA_NEWS_ID";

    private int id;
    private int currentPage = 1;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<NewsList.TngouBean> mList;
    private NewsListAdapter mNewsListAdapter;

    private int totalCounter;
    private int totalPage;

    public NewsListFragment() {
    }

    public static NewsListFragment newInstance(int id) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_NEWS_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            id = bundle.getInt(EXTRA_NEWS_ID);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        mRecyclerView = findView(R.id.rv_news_recycler_view);
        if(mList == null) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }
        mSwipeRefreshLayout = findView(R.id.srl_new_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsListAdapter = new NewsListAdapter(R.layout.item_news, mList);
        mNewsListAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mNewsListAdapter.isFirstOnly(true);
        mRecyclerView.setAdapter(mNewsListAdapter);
    }

    @Override
    protected void addListener() {

        mNewsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if(currentPage >= totalCounter) {
                    currentPage = totalPage;
                } else {
                    currentPage++;
                }
                L.i("page ---> " + currentPage);
                HttpManager.getInstance().getNewsList(id, currentPage, new Subscriber<NewsList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(NewsList newsList) {
                        if(mNewsListAdapter.getData().size() >= totalCounter) {
                            mNewsListAdapter.loadComplete();
                        } else {
                            L.i("load more ++++++++++ " + newsList.getTngou().toString());
                            mNewsListAdapter.addData(newsList.getTngou());
                        }
                    }
                });
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getActivity(), ShowNewsActivity.class);
                NewsList.TngouBean tngou = (NewsList.TngouBean) baseQuickAdapter.getData().get(i);
                intent.putExtra(C.EXTRA_NEWS_ID, tngou.getId());
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                HttpManager.getInstance().getNewsList(id, currentPage, new Subscriber<NewsList>() {
                    @Override
                    public void onCompleted() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(NewsList newsList) {
                        if(mList != null) {
                            mList.clear();
                            totalCounter = newsList.getTotal();
                            L.i("totalCounter = " + totalCounter);
                            if(totalCounter % 10 == 0) {
                                totalPage = totalCounter / 10;
                            } else {
                                totalPage = totalCounter / 10 + 1;
                            }
                            L.i("SwipeRefresh >>>>>> " + newsList.getTngou().toString());
                            mList.addAll(newsList.getTngou());
                            mNewsListAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onVisible() {
        L.i("onVisible");
        loadNewsList();
    }

    private void loadNewsList() {
        L.i("frist page = " + currentPage);
        currentPage = 1;
        HttpManager.getInstance()
                .getNewsList(id, currentPage, new MSubscriber(ProgressDialogUtils.showProgressDialog(getActivity())) {
                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        NewsList newsList = (NewsList) httpResult;
                        totalCounter = newsList.getTotal();
                        L.i("totalCounter = " + totalCounter);
                        if(totalCounter % 10 == 0) {
                            totalPage = totalCounter / 10;
                        } else {
                            totalPage = totalCounter / 10 + 1;
                        }
                        L.i("totalPage = " + totalPage);
                        L.i("first -----------> " + newsList.getTngou().toString());
                        mList.addAll(newsList.getTngou());
                        mNewsListAdapter.notifyDataSetChanged();
                    }
                });
    }
}
