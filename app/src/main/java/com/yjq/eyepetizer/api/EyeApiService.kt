package com.yjq.eyepetizer.api

import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Columns
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
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


    //搜索热词
    @GET("v3/queries/hot")
    fun getSearchHotWord(): Observable<List<String>>


    //根据用户输入进行搜索首页,例： http://baobab.kaiyanapp.com/api/v3/search?query=关键字
    @GET("v3/search")
    fun searchByKeyWord(@Query("query") query: String): Observable<ColumnPage>


    //搜索更多
    @GET
    fun searchMore(@Url nextPageUrl: String): Observable<ColumnPage>

}