package com.yjq.eyepetizer.mvp.model

import android.content.Context
import com.yjq.eyepetizer.api.ApiService
import com.yjq.eyepetizer.bean.HomeBean
import com.yjq.eyepetizer.manager.RetrofitClient
import io.reactivex.Observable

/**
 * 文件： HomeModel
 * 描述：
 * 作者： YangJunQuan   2018-8-7.
 */
class HomeModel {
    fun loadData(context: Context, loadMore: Boolean, data: String?): Observable<HomeBean>? {
        val apiService = RetrofitClient.getInstance(context, ApiService.BASE_URL).create(ApiService::class.java)
        return when (loadMore) {
            false -> apiService?.getHomeData()
            true -> apiService?.getHomeMoreData(data.toString(), "2")
        }
    }
}