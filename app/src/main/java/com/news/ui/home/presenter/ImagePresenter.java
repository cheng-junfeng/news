package com.news.ui.home.presenter;

import android.content.Context;

import com.library.net.helper.HttpHelper;
import com.library.utils.TLog;
import com.news.net.api.ApiConstants;
import com.news.net.bean.ResponseImagesListEntity;
import com.news.net.control.RetrofitHelper;
import com.news.net.service.ImageService;
import com.news.ui.home.contract.ImageContract;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


public class ImagePresenter implements ImageContract.Presenter {
    private final static String TAG = ImagePresenter.class.getSimpleName();
    public static final int PAGE_LIMIT = 20;

    private ImageContract.View mMainView;

    public ImagePresenter(Context context, ImageContract.View view) {
        mMainView = view;
    }

    @Override
    public void getNetData(int pageNum, final boolean loadMore) {
        HashMap<String, Object> allData = new HashMap<>();
        allData.put("col", "美女");
        allData.put("tag", "美女");
        allData.put("sort", 0);
        allData.put("pn", pageNum * PAGE_LIMIT);
        allData.put("rn", PAGE_LIMIT);
        allData.put("p", "channel");
        allData.put("from", 1);

        Observable<ResponseImagesListEntity> deviceObservable = RetrofitHelper.tokenCreate(ImageService.class, ApiConstants.Urls.BAIDU_IMAGES_URLS).queryArea(allData);
        HttpHelper.getObservable(deviceObservable)
                .subscribe(new Consumer<ResponseImagesListEntity>() {
                    @Override
                    public void accept(ResponseImagesListEntity value) {
                        TLog.d(TAG, "onSucceed:" + value);
                        if (loadMore) {
                            mMainView.addMoreListData(value);
                        } else {
                            mMainView.refreshListData(value);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        TLog.e(TAG, "onFailed:" + throwable.getMessage());
                    }
                });
    }
}
