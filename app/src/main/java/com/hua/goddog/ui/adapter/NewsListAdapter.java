package com.hua.goddog.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hua.goddog.R;
import com.hua.goddog.entity.hotspot.NewsList;
import com.hua.goddog.global.C;
import com.hua.goddog.ui.activity.hotspot.ShowNewsActivity;

import java.util.Date;
import java.util.List;

/**
 * 新闻列表适配器
 * Created by hzw on 2016/8/24.
 */
public class NewsListAdapter extends BaseQuickAdapter<NewsList.TngouBean> {

    public final static String IMAGE_BASE_URL = "http://tnfs.tngou.net/img";

    public NewsListAdapter(int layoutResId, List<NewsList.TngouBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, final NewsList.TngouBean tngou) {
        Glide.with(mContext).load(IMAGE_BASE_URL + tngou.getImg()).placeholder(R.mipmap.pikachu).into((ImageView) holder.getView(R.id.iv_item_news_icon));
        holder.setText(R.id.tv_item_news_title, tngou.getTitle());
        holder.setText(R.id.tv_item_news_address, tngou.getFromname());
        holder.setText(R.id.tv_item_news_time, C.simpleDataFormat.format(new Date(tngou.getTime())));
    }
}
