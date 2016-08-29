package com.hua.goddog.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hua.goddog.R;
import com.hua.goddog.entity.gallery.Gallery;
import com.hua.goddog.global.C;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by hzw on 2016/8/29.
 */
public class ShowGalleryAdapter extends PagerAdapter {

    private List<Gallery.ListBean> mList;

    public ShowGalleryAdapter(List<Gallery.ListBean> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        String uri = C.IMAGE_BASE_URL + mList.get(position).getSrc();
        Glide.with(container.getContext())
                .load(uri)
                .placeholder(R.mipmap.pikachu)
                .into(photoView);
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
