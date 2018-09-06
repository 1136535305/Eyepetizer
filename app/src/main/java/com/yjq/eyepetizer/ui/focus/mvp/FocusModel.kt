package com.yjq.eyepetizer.ui.focus.mvp

import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Columns
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.manager.RetrofitManager
import io.reactivex.Observable

/**
 * 文件： NotifyModel
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */
class FocusModel : FocusContract.Model {

    override fun getFocusTabList(): Observable<Columns> {
        return RetrofitManager.EyeAPI().getFocusTabList()
    }

    override fun getFocusTabInfo(apiUrl:String):Observable<ColumnPage>{
        return RetrofitManager.EyeAPI().getFocusTabInfo(apiUrl)
    }

}