package com.yjq.eyepetizer.ui.focus.mvp

import com.yjq.eyepetizer.base.BaseView
import com.yjq.eyepetizer.bean.cards.Columns
import io.reactivex.Observable

/**
 * 文件： NotifyContract
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */
interface FocusContract {
    interface View : BaseView

    interface Presenter

    interface Model {
        fun getFocusTabList(): Observable<Columns>
    }
}