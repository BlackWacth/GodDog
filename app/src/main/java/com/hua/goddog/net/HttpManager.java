package com.hua.goddog.net;

import com.hua.goddog.entity.HttpResult;
import com.hua.goddog.global.C;
import com.hua.goddog.net.api.GalleryApi;
import com.hua.goddog.net.api.HotspotApi;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * HTTP请求管理类。
 * Created by hzw on 2016/8/24.
 */
public class HttpManager {

    private HttpManager() {
    }

    private static final class HttpManagerHolder {
        private static final HttpManager HTTP_MANAGER = new HttpManager();
    }

    public static HttpManager getInstance() {
        return HttpManagerHolder.HTTP_MANAGER;
    }

    public <T extends HttpResult> Observable<T>  createObservable(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public HotspotApi loadHotspot() {
        return ServiceFactory.getInstance().createService(HotspotApi.class);
    }

    public GalleryApi loadGallery() {
        return ServiceFactory.getInstance().createService(GalleryApi.class);
    }

    public void getClassify(Subscriber subscriber) {
        createObservable(loadHotspot().getClassify()).subscribe(subscriber);
    }

    public void getNewsList(int id, int page, int rows, Subscriber subscriber) {
        createObservable(loadHotspot().getNews(id, page, rows)).subscribe(subscriber);
    }

    public void getNewsList(int id, int page, Subscriber subscriber) {
        getNewsList(id, page, C.DEFAULT_ROWS, subscriber);
    }

    public void getNews(int id, Subscriber subscriber) {
        createObservable(loadHotspot().getNew(id)).subscribe(subscriber);
    }

    public void getGalleryClassify(Subscriber subscriber) {
        createObservable(loadGallery().getClassify()).subscribe(subscriber);
    }

    public void getGalleryList(int id, int page, int rows, Subscriber subscriber) {
        createObservable(loadGallery().getGalleryList(id, page, rows)).subscribe(subscriber);
    }

    public void getGalleryList(int id, int page, Subscriber subscriber) {
        getGalleryList(id, page, C.DEFAULT_ROWS, subscriber);
    }
}
