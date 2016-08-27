package com.hua.goddog.net.Subscriber;

import android.app.ProgressDialog;

import com.hua.goddog.entity.HttpResult;
import com.hua.goddog.utils.ProgressDialogUtils;

import rx.Subscriber;

/**
 * Created by hzw on 2016/8/24.
 */
public abstract class MSubscriber<T extends HttpResult> extends Subscriber<T> {

    private ProgressDialog mProgressDialog;

    public MSubscriber(ProgressDialog progressDialog) {
        this.mProgressDialog = progressDialog;
    }

    @Override
    public void onCompleted() {
        ProgressDialogUtils.hideProgressDialog(mProgressDialog);
    }

    @Override
    public void onError(Throwable e) {
        ProgressDialogUtils.hideProgressDialog(mProgressDialog);
        onFailed(e);
    }

    @Override
    public void onNext(T t) {
        if(t.isStatus()) {
            onSuccess(t);
        } else {
            onFailed(new Throwable("status = " + t.isStatus()));
        }
    }

    protected abstract void onSuccess(T t);

    protected void onFailed(Throwable e) {

    }
}
