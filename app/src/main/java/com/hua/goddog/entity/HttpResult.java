package com.hua.goddog.entity;

import java.io.Serializable;

/**
 * 访问结果基类，共同结构只有status
 * Created by hzw on 2016/8/23.
 */
public class HttpResult implements Serializable {

    protected boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
