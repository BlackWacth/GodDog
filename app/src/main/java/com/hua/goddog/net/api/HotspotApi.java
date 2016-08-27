package com.hua.goddog.net.api;

import com.hua.goddog.entity.hotspot.Classify;
import com.hua.goddog.entity.hotspot.News;
import com.hua.goddog.entity.hotspot.NewsList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 热点API接口
 * Created by hzw on 2016/8/23.
 */
public interface HotspotApi {

    String BASE_URL = "http://www.tngou.net/api/top/";

    @GET("classify")
    Observable<Classify> getClassify();

    @GET("list")
    Observable<NewsList> getNews(@Query("id") int id, @Query("page") int page, @Query("rows") int rows);

    @GET("show")
    Observable<News> getNew(@Query("id") int id);

}
