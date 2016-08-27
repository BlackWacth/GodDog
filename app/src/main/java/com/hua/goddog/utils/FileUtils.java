package com.hua.goddog.utils;

import android.text.TextUtils;

import com.hua.goddog.global.GApplication;

import java.io.File;

/**
 * 文件工具类
 * Created by hzw on 2016/8/23.
 */
public class FileUtils {

    public static File createCacheFile(String name) {
        if(TextUtils.isEmpty(name)) {
            return null;
        }
        return new File(GApplication.INSTANCE.getCacheDir(), name);
    }

}
