package com.news.net.control;


import com.library.net.control.LogInterceptor;
import com.library.net.control.ToByteConvertFactory;
import com.news.config.NetConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private RetrofitHelper() {
    }

    public static <T> T tokenCreate(final Class<T> service) {
        return new Retrofit.Builder()
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetConfig.IP_ADDRESS).build().create(service);
    }

    public static <T> T tokenCreate(final Class<T> service, String ipAddress) {
        return new Retrofit.Builder()
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ipAddress).build().create(service);
    }

    public static <T> T byteCreate(final Class<T> service) {
        return new Retrofit.Builder()
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ToByteConvertFactory.create())
                .baseUrl(NetConfig.IP_ADDRESS).build().create(service);
    }

    public static <T> T byteCreate(final Class<T> service, String ipAddress) {
        return new Retrofit.Builder()
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ToByteConvertFactory.create())
                .baseUrl(ipAddress).build().create(service);
    }

    private static OkHttpClient getClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .build();
        return okHttpClient;
    }
}
