package com.yjq.eyepetizer.ui.video.mvp

import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Data
import com.yjq.eyepetizer.manager.RetrofitManager
import io.reactivex.Observable

/**
 * 文件： VideoPlayModel
 * 描述：
 * 作者： YangJunQuan   2018-9-12.
 */
class VideoPlayModel : VideoPlayContract.Model {

    fun getVideoRelated(videoId: String): Observable<ColumnPage> {
        return RetrofitManager.EyeAPI().getVideoRelated(videoId)
    }

    fun getVideoDetail(videoId: String): Observable<Data> {
        return RetrofitManager.EyeAPI().getVideoDetail(videoId)
    }
}