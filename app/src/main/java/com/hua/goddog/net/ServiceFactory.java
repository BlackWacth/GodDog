package com.hua.goddog.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hua.goddog.global.C;
import com.hua.goddog.utils.FileUtils;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Service工厂
 * Created by hzw on 2016/8/23.
 */
public class ServiceFactory {

    private final Gson mGson;

    private ServiceFactory() {
        mGson = new GsonBuilder().setDateFormat(C.DATE_FORMAT).create();
    }

    private static class ServiceFactoryHolder {
        private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.SERVICE_FACTORY;
    }

    public <T> T createService(Class<T> cls) {
        String baseUrl = "";
        try {
            Field field = cls.getField("BASE_URL");
            baseUrl = (String) field.get(cls);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(MOkHttpClient.getInstance())
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(cls);
    }

    final static class MOkHttpClient {

        private MOkHttpClient() {
        }

        private static class ClientHolder {
            private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                    .connectTimeout(C.DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(C.DEFAULT_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(C.DEFAULT_READ_TIMEOUT, TimeUnit.MILLISECONDS)
//                    .cache(new Cache(FileUtils.createCacheFile("OkHttpCache"), C.DEFAULT_CACHE_SIZE))
                    .build();
        }

        public static OkHttpClient getInstance() {
            return ClientHolder.OK_HTTP_CLIENT;
        }
    }
}
