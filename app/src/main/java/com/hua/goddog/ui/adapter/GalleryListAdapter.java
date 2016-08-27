package com.hua.goddog.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hua.goddog.R;
import com.hua.goddog.entity.gallery.GalleryList;

import java.util.List;

/**
 * Created by hzw on 2016/8/26.
 */
public class GalleryListAdapter extends BaseQuickAdapter<GalleryList.Tngou> {

    public final static String IMAGE_BASE_URL = "http://tnfs.tngou.net/img";

    public GalleryListAdapter(int layoutResId, List<GalleryList.Tngou> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, GalleryList.Tngou tngou) {
        Glide.with(mContext)
                .load(IMAGE_BASE_URL + tngou.getImg())
                .placeholder(R.mipmap.pikachu)
                .into((ImageView) holder.getView(R.id.iv_item_gallery_img));
    }
}
