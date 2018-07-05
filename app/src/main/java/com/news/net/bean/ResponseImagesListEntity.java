package com.news.net.bean;

import java.util.List;

public class ResponseImagesListEntity {

    private String col;
    private String tag;
    private String tag3;
    private String sort;
    private int totalNum;
    private int startIndex;
    private int returnNumber;
    private List<ImagesListEntity> imgs;

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(int returnNumber) {
        this.returnNumber = returnNumber;
    }

    public List<ImagesListEntity> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImagesListEntity> imgs) {
        this.imgs = imgs;
    }
}
