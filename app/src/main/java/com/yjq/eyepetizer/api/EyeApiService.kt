package com.yjq.eyepetizer.api

import com.yjq.eyepetizer.bean.ColumnPage
import com.yjq.eyepetizer.bean.Columns
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * 文件： EyeApiService
 * 描述： 【开眼API】
 * 作者： YangJunQuan   2018/8/6.
 */
interface EyeApiService {


    //获取【首页】栏目列表
    @GET("v5/index/tab/list")
    fun getColumnList(): Observable<Columns>


    //获取某栏目详情
    @GET
    fun getColumnHomePage(@Url url: String): Observable<ColumnPage>


//
//    //获取首页第一页数据
//    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
//    fun getHomeData(): Observable<HomeBean>
//
//
//    //获取首页第一页之后的数据  ?date=1499043600000&num=2
//    @GET("v2/feed")
//    fun getHomeMoreData(@Query("date") date: String, @Query("num") num: String): Observable<HomeBean>
//
//    //获取发现频道信息
//    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
//    fun getFindData(): Observable<MutableList<FindBean>>
//
//
//    //获取热门排行信息
//    @GET("v3/ranklist")
//    fun getHotData(@Query("num") num: Int, @Query("strategy") strategy: String,
//                   @Query("udid") udid: String, @Query("vc") vc: Int): Observable<HotBean>
//
//    //获取发现频道详情信息
//    @GET("v3/videos")
//    fun getFindDetailData(@Query("categoryName") categoryName: String, @Query("strategy") strategy: String,
//                          @Query("udid") udid: String, @Query("vc") vc: Int): Observable<HotBean>
//
//    //获取发现详情加载更多消息
//    @GET("v3/videos")
//    fun getFindDetailMoreData(@Query("start") start: Int, @Query("num") num: Int,
//                              @Query("categoryName") categoryName: String, @Query("strategy") strategy: String): Observable<HotBean>
//
//    //获取关键词搜索相关信息
//    @GET("v1/search")
//    fun getSearchData(@Query("num") num: Int, @Query("query") query: String,
//                      @Query("start") start: Int): Observable<HotBean>
}