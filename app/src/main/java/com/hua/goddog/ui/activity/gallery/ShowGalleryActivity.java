package com.hua.goddog.ui.activity.gallery;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.hua.goddog.R;
import com.hua.goddog.entity.gallery.Gallery;
import com.hua.goddog.global.C;
import com.hua.goddog.net.HttpManager;
import com.hua.goddog.ui.adapter.ShowGalleryAdapter;
import com.hua.goddog.ui.base.BaseActivity;
import com.hua.goddog.ui.widget.HackyViewPager;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class ShowGalleryActivity extends BaseActivity {

    private HackyViewPager mViewPager;
    private List<Gallery.ListBean> mList;
    private ShowGalleryAdapter mAdapter;
    private int mId;

    @Override
    protected void setFlag() {
        // 去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉信息栏，全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_show_gallery;
    }

    @Override
    protected void initView() {
        mViewPager = findView(R.id.hvp_show_gallery_viewpager);
    }

    @Override
    protected void initData() {
        mId = getIntent().getIntExtra(C.EXTRA_GALLERY_ID, 0);
        if(mList == null) {
            mList = new ArrayList<>();
        }
        loadGallery();
        mAdapter = new ShowGalleryAdapter(mList);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    protected void addListener() {

    }

    private void loadGallery() {
        HttpManager.getInstance().getGallery(mId, new Subscriber<Gallery>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Gallery gallery) {
                if(gallery == null) {
                    return;
                }
                mList.clear();
                mList.addAll(gallery.getList());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

}
