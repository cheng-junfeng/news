package com.news.ui.home.presenter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.news.R;
import com.youth.banner.loader.ImageLoader;

public class NewsImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions imgOptions = new RequestOptions()
                .placeholder(R.mipmap.default_error)
                .error(R.mipmap.default_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        //Glide 加载图片简单用法
        Glide.with(context).load(path).apply(imgOptions).into(imageView);
    }
}