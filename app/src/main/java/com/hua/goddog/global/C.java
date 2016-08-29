package com.hua.goddog.global;

import java.text.SimpleDateFormat;

/**
 * 全局常量
 * Created by hzw on 2016/8/23.
 */
public class C {

    public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final SimpleDateFormat simpleDataFormat = new SimpleDateFormat(DATE_FORMAT);

    public static final int DEFAULT_CONNECT_TIMEOUT = 10000;
    public static final int DEFAULT_WRITE_TIMEOUT = 10000;
    public static final int DEFAULT_READ_TIMEOUT = 10000;
    public static final long DEFAULT_CACHE_SIZE = 1024 * 1024 * 10;

    public static final int DEFAULT_ROWS = 10;

    public static final String EXTRA_NEWS_ID = "EXTRA_NEWS_ID";
    public static final String EXTRA_GALLERY_ID = "EXTRA_NEWS_ID";

    public final static String IMAGE_BASE_URL = "http://tnfs.tngou.net/img";

}
