package com.hua.goddog.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 进度条
 * Created by hzw on 2016/8/24.
 */
public class ProgressDialogUtils {

    public static ProgressDialog showProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("正在加载...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        return progressDialog;
    }

    public static void hideProgressDialog(ProgressDialog progressDialog) {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
