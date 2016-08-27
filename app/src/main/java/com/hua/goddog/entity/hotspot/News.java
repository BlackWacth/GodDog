package com.hua.goddog.entity.hotspot;

import com.hua.goddog.entity.HttpResult;

/**
 * Created by hzw on 2016/8/25.
 */
public class News extends HttpResult {

    private int count;
    private String description;
    private int fcount;
    private String fromname;
    private String fromurl;
    private int id;
    private String img;
    private String keywords;
    private String message;
    private int rcount;
    private long time;
    private String title;
    private int topclass;
    private String url;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getFromurl() {
        return fromurl;
    }

    public void setFromurl(String fromurl) {
        this.fromurl = fromurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTopclass() {
        return topclass;
    }

    public void setTopclass(int topclass) {
        this.topclass = topclass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "News{" +
                "count=" + count +
                ", description='" + description + '\'' +
                ", fcount=" + fcount +
                ", fromname='" + fromname + '\'' +
                ", fromurl='" + fromurl + '\'' +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", keywords='" + keywords + '\'' +
                ", message='" + message + '\'' +
                ", rcount=" + rcount +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", topclass=" + topclass +
                ", url='" + url + '\'' +
                '}';
    }
}
