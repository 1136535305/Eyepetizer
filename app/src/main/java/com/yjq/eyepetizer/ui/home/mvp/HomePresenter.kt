package com.yjq.eyepetizer.ui.home.mvp

import android.content.Context
import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Columns
import io.reactivex.Observable

/**
 * 文件： HomePresenter
 * 描述：
 * 作者： YangJunQuan   2018-8-17.
 */
class HomePresenter(val context: Context, val view: HomeContract.View) : HomeContract.Presenter {

    private val model = HomeModel()

    //获取首页栏目列表
    fun getHomeColumns(): Observable<Columns> {
        return model.getColumns()
    }

    fun getColumnPage(url: String): Observable<ColumnPage> {
        return model.getColumnPage(url)
    }
}