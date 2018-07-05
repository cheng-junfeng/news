package com.news.net.service;


import com.news.net.bean.ResponseImagesListEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ImageService {

    @POST("data/imgs")
    Observable<ResponseImagesListEntity> queryArea(@QueryMap HashMap<String, Object> allMap);
}
