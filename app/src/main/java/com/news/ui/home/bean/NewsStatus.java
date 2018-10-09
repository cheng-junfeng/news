package com.news.ui.home.bean;

import java.io.Serializable;

public class NewsStatus implements Serializable{
    private final int index;
    private int size;
    private String title;
    private String category;
    private String clazz;

    private NewsStatus(Builder builder){
        this.index = builder.index;
        this.size = builder.size;
        this.title = builder.title;
        this.category = builder.category;
        this.clazz = builder.clazz;
    }

    public int getIndex(){
        return this.index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle(){
        return this.title;
    }

    public String getCategory(){
        return this.category;
    }

    public String getClazz(){
        return this.clazz;
    }

    public static class Builder {
        private final int index;
        private int size;
        private String title;
        private String category;
        private String clazz;

        public Builder(int inde) {
            this.index = inde;
        }

        public Builder size(int siz) {
            this.size = siz;
            return this;
        }

        public Builder title(String mTitle) {
            this.title = mTitle;
            return this;
        }

        public Builder category(String categor) {
            this.category = categor;
            return this;
        }

        public Builder clazz(String claz) {
            this.clazz = claz;
            return this;
        }

        public NewsStatus build() {
            return new NewsStatus(this);
        }
    }
}
