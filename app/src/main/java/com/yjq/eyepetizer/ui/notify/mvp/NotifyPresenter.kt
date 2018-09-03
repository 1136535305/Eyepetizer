package com.yjq.eyepetizer.ui.focus.mvp

import android.content.Context
import com.yjq.eyepetizer.bean.cards.Columns
import com.yjq.eyepetizer.bean.notify.MessageInfo
import io.reactivex.Observable

/**
 * 文件： NotifyPresenter
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */
class NotifyPresenter(val context: Context) : NotifyContract.Presenter {

    private val model = NotifyModel()


    fun getNotifyTabList(): Observable<Columns> {
        return model.getNotifyTabList()
    }

    fun getNotifyTabInfo(tabUrl: String): Observable<MessageInfo> {
        return model.getNotifyTabInfo(tabUrl)
    }

}