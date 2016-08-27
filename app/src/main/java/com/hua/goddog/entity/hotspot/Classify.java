package com.hua.goddog.entity.hotspot;

import com.hua.goddog.entity.HttpResult;

import java.util.List;

/**
 * 热点分类
 * Created by hzw on 2016/8/23.
 */
public class Classify extends HttpResult {

    /**
     * description : 天狗实时事件:民生热点事件，民生热词排行 做最好的民生热点、热词提取；推送最新的民生实时事件，挖掘最新的民生热词。
     * id : 1
     * keywords : 民生热点事件 民生热词排行 天狗实时事件
     * name : 民生热点
     * seq : 1
     * title : 民生热点事件_民生热词排行-天狗实时事件
     */

    private List<TngouBean> tngou;

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        private String description;
        private int id;
        private String keywords;
        private String name;
        private int seq;
        private String title;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TngouBean{" +
                    "description='" + description + '\'' +
                    ", id=" + id +
                    ", keywords='" + keywords + '\'' +
                    ", name='" + name + '\'' +
                    ", seq=" + seq +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
