package com.news.ui.home.contract;


import android.content.Context;
import android.view.animation.Animation;

import com.news.app.mvp.BasePresenter;

public interface SplashContract {
    interface View {
        void animateBackgroundImage(Animation animation);

        void initViews(String versionName, String copyright, int backgroundResId);

        void toHomePage();
    }

    interface Presenter extends BasePresenter {

        int getBackgroundImageResID();

        String getCopyright(Context context);

        String getVersionName(Context context);

        Animation getBackgroundImageAnimation(Context context);
    }
}
