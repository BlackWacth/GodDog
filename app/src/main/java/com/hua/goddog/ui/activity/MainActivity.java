package com.hua.goddog.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hua.goddog.R;
import com.hua.goddog.entity.HttpResult;
import com.hua.goddog.entity.gallery.GalleryClassify;
import com.hua.goddog.entity.hotspot.Classify;
import com.hua.goddog.net.HttpManager;
import com.hua.goddog.net.Subscriber.MSubscriber;
import com.hua.goddog.ui.adapter.FragmentHolder;
import com.hua.goddog.ui.adapter.MainAdapter;
import com.hua.goddog.ui.base.BaseActivity;
import com.hua.goddog.ui.fragment.GalleryListFragment;
import com.hua.goddog.ui.fragment.NewsListFragment;
import com.hua.goddog.utils.L;
import com.hua.goddog.utils.ProgressDialogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<FragmentHolder> mList;
    private MainAdapter mMainAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolbar = findView(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findView(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findView(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mTabLayout = findView(R.id.tl_main_top_tab);
        mViewPager = findView(R.id.vp_main_content_viewPager);
    }

    @Override
    protected void initData() {
        if (mList == null) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }
        mMainAdapter = new MainAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mMainAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        loadNewsClassify();
//        loadGalleryClassify();
    }

    @Override
    protected void addListener() {

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_hot_spot) {
            loadNewsClassify();
        } else if (id == R.id.nav_gallery) {
            loadGalleryClassify();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadNewsClassify() {
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.getClassify(new MSubscriber(ProgressDialogUtils.showProgressDialog(this)) {
            @Override
            protected void onSuccess(HttpResult httpResult) {
                if (mList == null) {
                    return;
                }
                mList.clear();
                mViewPager.removeAllViews();
                Classify classify = (Classify) httpResult;
                for (Classify.TngouBean tngou : classify.getTngou()) {
                    L.i(tngou.toString());
                    mList.add(new FragmentHolder(tngou.getName(), NewsListFragment.newInstance(tngou.getId())));
                }
                mMainAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadGalleryClassify() {
        HttpManager.getInstance().getGalleryClassify(new MSubscriber(ProgressDialogUtils.showProgressDialog(this)) {
            @Override
            protected void onSuccess(HttpResult httpResult) {
                if (mList == null) {
                    return;
                }
                mList.clear();
                mViewPager.removeAllViews();
                GalleryClassify classify = (GalleryClassify) httpResult;
                for(GalleryClassify.TngouBean tngou : classify.getTngou()) {
                    L.i(tngou.toString());
                    mList.add(new FragmentHolder(tngou.getName(), GalleryListFragment.newInstance(tngou.getId())));
                }
                mMainAdapter.notifyDataSetChanged();
            }
        });
    }
}
