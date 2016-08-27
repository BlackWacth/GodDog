package com.hua.goddog.utils;

import android.os.Build;

/**
 * 代码版本检测工具
 * Created by hzw on 2016/8/23.
 */
public class BuildVersionUtils {

    /**
     *  当前版本是否6.0以上
     * @return
     */
    public static boolean isAndroid6_0() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        }
        return false;
    }

    /**
     *  当前版本是否5.1.1以上
     * @return
     */
    public static boolean isAndroid5_1_1() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return true;
        }
        return false;
    }

    /**
     *  当前版本是否5.0.1以上
     * @return
     */
    public static boolean isAndroid5_0_1() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        }
        return false;
    }

    /**
     *  当前版本是否4.4W以上
     * @return
     */
    public static boolean isAndroid4_4W() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            return true;
        }
        return false;
    }

    /**
     *  当前版本是否4.4.2以上
     * @return
     */
    public static boolean isAndroid4_4_2() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return true;
        }
        return false;
    }

}
