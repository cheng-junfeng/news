package com.news.ui.home.bean;

import java.io.Serializable;
import java.util.List;

public class NewsListItemBean implements Serializable {
    public String id;
    public String version;
    public String content;
    public String image;
    public String title;
    public List<String> allImages;
}
