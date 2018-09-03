package com.yjq.eyepetizer.ui.focus.mvp

import com.yjq.eyepetizer.base.BaseView
import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Columns
import com.yjq.eyepetizer.bean.notify.MessageInfo
import io.reactivex.Observable

/**
 * 文件： NotifyContract
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */
interface NotifyContract {
    interface View : BaseView

    interface Presenter

    interface Model {
        fun getNotifyTabList(): Observable<Columns>
        fun getNotifyTabInfo(tabUrl: String): Observable<MessageInfo>
    }
}