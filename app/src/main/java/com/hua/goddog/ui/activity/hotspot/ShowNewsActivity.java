package com.hua.goddog.ui.activity.hotspot;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hua.goddog.R;
import com.hua.goddog.entity.HttpResult;
import com.hua.goddog.entity.hotspot.News;
import com.hua.goddog.global.C;
import com.hua.goddog.net.HttpManager;
import com.hua.goddog.net.Subscriber.MSubscriber;
import com.hua.goddog.ui.base.BaseActivity;
import com.hua.goddog.utils.ProgressDialogUtils;

public class ShowNewsActivity extends BaseActivity {

    private WebView mWebView;
    private Toolbar mToolbar;
    private int mId;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_show_news;
    }

    @Override
    protected void initView() {
        mWebView = findView(R.id.wv_show_news_webView);
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        mToolbar = findView(R.id.tb_show_news_toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.gray100));
        mToolbar.setNavigationIcon(R.mipmap.ic_chevron_left_white_48dp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initData() {
        mId = getIntent().getIntExtra(C.EXTRA_NEWS_ID, 0);
        loadNews();
    }

    @Override
    protected void addListener() {

    }

    private void loadNews() {
        HttpManager.getInstance().getNews(mId, new MSubscriber(ProgressDialogUtils.showProgressDialog(this)) {
            @Override
            protected void onSuccess(HttpResult httpResult) {
                News news = (News) httpResult;
                mToolbar.setTitle(news.getTitle());
                mWebView.loadDataWithBaseURL(null, news.getMessage(), "text/html", "utf-8", null);
            }
        });
    }
}
