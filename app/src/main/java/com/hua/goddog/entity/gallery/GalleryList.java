package com.hua.goddog.entity.gallery;

import com.hua.goddog.entity.HttpResult;

import java.util.List;

/**
 * Created by hzw on 2016/8/26.
 */
public class GalleryList extends HttpResult {

    private int total;
    private List<Tngou> tngou;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Tngou> getTngou() {
        return tngou;
    }

    public void setTngou(List<Tngou> tngou) {
        this.tngou = tngou;
    }

    public static class Tngou {

        /**
         * count : 5092
         * fcount : 0
         * galleryclass : 3
         * id : 710
         * img : /ext/160321/e57d5816cb72d7486aa6dbf19a7d0c6c.jpg
         * rcount : 0
         * size : 16
         * time : 1458561029000
         * title : 很诱人的美女翘臀诱惑那超波肉丝腿真长
         */

        /**访问数  */
        private int count;

        /**收藏数 */
        private int fcount;

        /**图片分类 */
        private int galleryclass;

        /**图库ID编码  图片WEB访问地址，可以通过 http://www.tngou.net/tnfs/show/+【id】 的方式拼接。
         * 如： http://www.tngou.net/tnfs/show/18  支持网站和手机跨平台浏览*/
        private int id;

        /**图库封面 */
        private String img;

        /**回复数 */
        private int rcount;

        /**图片张数 */
        private int size;

        /**发布时间 */
        private long time;

        /**标题 */
        private String title;

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

        @Override
        public String toString() {
            return "Tngou{" +
                    "\ncount=" + count +
                    ", \nfcount=" + fcount +
                    ", \ngalleryclass=" + galleryclass +
                    ", \nid=" + id +
                    ", \nimg='" + img + '\'' +
                    ", \nrcount=" + rcount +
                    ", \nsize=" + size +
                    ", \ntime=" + time +
                    ", \ntitle='" + title + '\'' +
                    "\n}";
        }
    }

    @Override
    public String toString() {
        return "GalleryList{" +
                "total=" + total +
                ", tngou=" + tngou +
                '}';
    }
}
