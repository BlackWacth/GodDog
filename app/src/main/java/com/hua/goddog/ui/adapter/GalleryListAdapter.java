package com.hua.goddog.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hua.goddog.R;
import com.hua.goddog.entity.gallery.GalleryList;
import com.hua.goddog.global.C;
import com.hua.goddog.ui.activity.gallery.ShowGalleryActivity;

import java.util.List;

/**
 * Created by hzw on 2016/8/26.
 */
public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.GalleryListHolder> {

    public final static String IMAGE_BASE_URL = "http://tnfs.tngou.net/img";

    private List<GalleryList.Tngou> mList;
    private Context mContext;
    private int mItemHeight;
    private RecyclerView.LayoutParams mItemParams;

    public GalleryListAdapter(Context context, List<GalleryList.Tngou> data) {
        mContext = context;
        mList = data;
    }

    @Override
    public GalleryListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GalleryListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(GalleryListHolder holder, int position) {
        if(holder.img.getLayoutParams().height != mItemHeight) {
            if(mItemParams != null) {
                holder.img.setLayoutParams(mItemParams);
            }
        }
        final GalleryList.Tngou tngou = mList.get(position);
        Glide.with(mContext)
                .load(IMAGE_BASE_URL + tngou.getImg())
                .placeholder(R.mipmap.pikachu)
                .into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowGalleryActivity.class);
                intent.putExtra(C.EXTRA_GALLERY_ID, tngou.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItemHeight(int itemHeight) {
        mItemHeight = itemHeight;
        mItemParams = new RecyclerView.LayoutParams(mItemHeight, mItemHeight);
        notifyDataSetChanged();
    }

    class GalleryListHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public GalleryListHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_item_gallery_img);
        }
    }
}
