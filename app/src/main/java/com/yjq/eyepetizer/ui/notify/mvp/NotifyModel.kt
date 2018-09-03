package com.yjq.eyepetizer.ui.focus.mvp

import com.yjq.eyepetizer.bean.cards.Columns
import com.yjq.eyepetizer.bean.notify.MessageInfo
import com.yjq.eyepetizer.manager.RetrofitManager
import io.reactivex.Observable

/**
 * 文件： NotifyModel
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */
class NotifyModel : NotifyContract.Model {

    override fun getNotifyTabList(): Observable<Columns> {
        return RetrofitManager.EyeAPI().getNotifyTabList()
    }

    override fun getNotifyTabInfo(tabUrl: String): Observable<MessageInfo> {
        return RetrofitManager.EyeAPI().getNotifyTabInfo(tabUrl)
    }

}