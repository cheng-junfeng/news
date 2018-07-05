package com.news.ui.home.contract;

import com.news.app.mvp.BaseView;
import com.news.net.bean.ImagesListEntity;
import com.news.net.bean.ResponseImagesListEntity;


public interface ImageContract {
    interface View extends BaseView {

        void refreshListData(ResponseImagesListEntity responseImagesListEntity);

        void addMoreListData(ResponseImagesListEntity responseImagesListEntity);

        void navigateToImagesDetail(int position, ImagesListEntity entity, int x, int y, int width, int height);
    }

    interface Presenter {
        void getNetData(int page, boolean loadMore);
    }
}
