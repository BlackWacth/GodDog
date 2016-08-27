package com.hua.goddog.entity.gallery;

import com.hua.goddog.entity.HttpResult;

import java.util.List;

/**
 * Created by hzw on 2016/8/26.
 */
public class Gallery extends HttpResult {


    /**
     * count : 2236
     * fcount : 0
     * galleryclass : 4
     * id : 100
     * img : /ext/150728/d937d0a43006fbda2dc9615b5b538716.jpg
     * list : [{"gallery":100,"id":1435,"src":"/ext/150728/d937d0a43006fbda2dc9615b5b538716.jpg"},{"gallery":100,"id":1436,"src":"/ext/150728/620b0a20feb1357ad6ea20cd1c2afe7e.jpg"},{"gallery":100,"id":1437,"src":"/ext/150728/21a5f7a8c5a9d9a433a58a18e37b3884.jpg"},{"gallery":100,"id":1438,"src":"/ext/150728/9c06dd8c5c8f5c087d77b1abbf236f10.jpg"},{"gallery":100,"id":1439,"src":"/ext/150728/42ecea5064e41df136df25949f67f5d4.jpg"},{"gallery":100,"id":1440,"src":"/ext/150728/20eda1f23699a11285122e022dc599e4.jpg"},{"gallery":100,"id":1441,"src":"/ext/150728/646b2df4e333bb953e1e27ecf83630db.jpg"},{"gallery":100,"id":1442,"src":"/ext/150728/f8447cb62a5858e95e752ef7a2e66de5.jpg"},{"gallery":100,"id":1443,"src":"/ext/150728/1a4128b78411a4499515ccc71b9f2238.jpg"},{"gallery":100,"id":1444,"src":"/ext/150728/3b33d07f5e29579b3703da861586c828.jpg"},{"gallery":100,"id":1445,"src":"/ext/150728/a7a69a1fa664c4fa1e63744b661b67d0.jpg"},{"gallery":100,"id":1446,"src":"/ext/150728/211440dd6ab4f3bd8bf6e5bd1981b40d.jpg"},{"gallery":100,"id":1447,"src":"/ext/150728/9351a645001a4ee6e3c333a77676cb6f.jpg"}]
     * rcount : 0
     * size : 13
     * time : 1438080571000
     * title : 美貌苗条的身材那短裙包臀
     * url : http://www.tngou.net/tnfs/show/100
     */

    private int count;
    private int fcount;
    private int galleryclass;
    private int id;
    private String img;
    private int rcount;
    private int size;
    private long time;
    private String title;
    private String url;
    /**
     * gallery : 100
     * id : 1435
     * src : /ext/150728/d937d0a43006fbda2dc9615b5b538716.jpg
     */

    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
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

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int gallery;
        private int id;
        private String src;

        public int getGallery() {
            return gallery;
        }

        public void setGallery(int gallery) {
            this.gallery = gallery;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "gallery=" + gallery +
                    ", id=" + id +
                    ", src='" + src + '\'' +
                    '}';
        }
    }
}
