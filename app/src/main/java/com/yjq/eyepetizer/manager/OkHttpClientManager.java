package com.yjq.eyepetizer.manager;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS);
                    mOkHttpClient = builder.build();
                }
            }
        }

        return mOkHttpClient;
    }


}
