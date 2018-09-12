package com.yjq.eyepetizer.ui.video.mvp

import android.content.Context
import com.yjq.eyepetizer.bean.cards.ColumnPage
import io.reactivex.Observable

/**
 * 文件： VideoPlayPresenter
 * 描述：
 * 作者： YangJunQuan   2018-9-12.
 */
class VideoPlayPresenter(val context: Context) : VideoPlayContract.Presenter {

    private val model = VideoPlayModel()

    fun getVideoRelated(videoId: String): Observable<ColumnPage> {
        return model.getVideoRelated(videoId)
    }

}