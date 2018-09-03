package com.yjq.eyepetizer.ui.focus.mvp

import android.content.Context
import com.yjq.eyepetizer.bean.cards.Columns
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

}