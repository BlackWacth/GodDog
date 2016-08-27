package com.hua.goddog.net.api;


import com.hua.goddog.entity.gallery.Gallery;
import com.hua.goddog.entity.gallery.GalleryClassify;
import com.hua.goddog.entity.gallery.GalleryList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hzw on 2016/8/26.
 */
public interface GalleryApi {

    String BASE_URL = "http://www.tngou.net/tnfs/api/";

    @GET("classify")
    Observable<GalleryClassify> getClassify();

    @GET("list")
    Observable<GalleryList> getGalleryList(@Query("id")int id, @Query("page")int page, @Query("rows")int rows);

    @GET("show")
    Observable<Gallery> getGallery(@Query("id") int id);
}
