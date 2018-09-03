package com.yjq.eyepetizer.manager;

import com.yjq.eyepetizer.api.EyeApiService;
import com.yjq.eyepetizer.constant.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文件： RetrofitManager
 * 描述： Retrofit封装管理类
 * 作者： YangJunQuan   2018-7-30.
 */

public class RetrofitManager {
    private static EyeApiService mEyeAPI;
    private Retrofit.Builder mBuilder;
    private static RetrofitManager mInstance;

    private RetrofitManager() {
        mBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClientManager.INSTANCE.getOkHttpClient());
    }

    private static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) mInstance = new RetrofitManager();
            }
        }
        return mInstance;
    }


    public static EyeApiService EyeAPI() {
        if (mEyeAPI == null) {
            synchronized (EyeApiService.class) {
                if (mEyeAPI == null) {
                    mEyeAPI = getInstance().initEyeAPI();
                }
            }
        }
        return mEyeAPI;
    }

    private EyeApiService initEyeAPI() {
        Retrofit retrofit = mBuilder.baseUrl(Constant.INSTANCE.getBASE_URL()).build();
        return retrofit.create(EyeApiService.class);
    }

}
