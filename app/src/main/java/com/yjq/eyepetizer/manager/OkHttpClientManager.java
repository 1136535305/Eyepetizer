package com.yjq.eyepetizer.manager;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 维护全局唯一一个OkHttpClient，官方推荐做法
 */

public enum OkHttpClientManager {

    INSTANCE;

    private OkHttpClient mOkHttpClient;

    public OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
            synchronized (OkHttpClientManager.class) {
                if (mOkHttpClient == null) {

                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                            .addInterceptor(interceptor)
                            .addInterceptor(chain -> {
                                Request originRequest = chain.request();
                                HttpUrl originUrl = originRequest.url();
                                HttpUrl newUrl = originUrl.newBuilder()
                                        .addQueryParameter("udid", "435865baacfc49499632ea13c5a78f944c2f28aa")
                                        .build();
                                Request newRequest = originRequest.newBuilder().url(newUrl).build();
                                return chain.proceed(newRequest);
                            })
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS);
                    mOkHttpClient = builder.build();
                }
            }
        }

        return mOkHttpClient;
    }


}
