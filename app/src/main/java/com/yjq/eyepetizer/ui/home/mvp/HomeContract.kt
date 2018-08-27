package com.yjq.eyepetizer.ui.home.mvp

import com.yjq.eyepetizer.base.BaseView
import com.yjq.eyepetizer.bean.cards.Columns
import io.reactivex.Observable

/**
 * 文件： HomeContract
 * 描述：
 * 作者： YangJunQuan   2018-8-17.
 */

interface HomeContract {
    interface View : BaseView

    interface Presenter

    interface Model {
        fun getColumns(): Observable<Columns>
    }
}
