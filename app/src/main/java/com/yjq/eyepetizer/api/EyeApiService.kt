package com.yjq.eyepetizer.api

import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Columns
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.bean.notify.MessageInfo
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

    //获取【关注】下的tabList
    @GET("v5/community/tab/list")
    fun getFocusTabList(): Observable<Columns>


    //获取【关注】下某一个Tab的数据
    @GET
    fun getFocusTabInfo(@Url tabUrl: String): Observable<ColumnPage>


    //获取【通知】下的tabList
    @GET("v3/messages/tabList")
    fun getNotifyTabList(): Observable<Columns>


    //获取【通知】下某一个Tab的数据
    @GET
    fun getNotifyTabInfo(@Url tabUrl: String): Observable<MessageInfo>


    //获取【视频播放页】一些相关信息，如该视频的相关推荐
    @GET("v4/video/related")
    fun getVideoRelated(@Query("id") videoId: String): Observable<ColumnPage>

}