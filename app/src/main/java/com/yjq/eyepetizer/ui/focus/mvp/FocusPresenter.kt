package com.yjq.eyepetizer.ui.focus.mvp

import android.content.Context
import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Columns
import com.yjq.eyepetizer.bean.cards.Item
import io.reactivex.Observable

/**
 * 文件： NotifyPresenter
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */
class FocusPresenter(val context: Context) : FocusContract.Presenter {

    private val model = FocusModel()


    fun getFocusTabList(): Observable<Columns> {
        return model.getFocusTabList()
    }

    fun getFocusTabInfo(apiUrl: String): Observable<ColumnPage> {
        return model.getFocusTabInfo(apiUrl)
    }

}