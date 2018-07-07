package com.news;

import android.app.Application;

import com.library.base.BaseAppManager;
import com.news.net.api.ApiConstants;
import com.news.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(ImageLoaderHelper.getInstance(this).getImageLoaderConfiguration(ApiConstants.Paths.IMAGE_LOADER_CACHE_PATH));

        new DensityHelper(this, 750).activate();
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    public void exitApp() {
        BaseAppManager.getInstance().clear();
        System.gc();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
