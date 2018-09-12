package com.yjq.eyepetizer.ui.search.mvp

import android.content.Context
import com.yjq.eyepetizer.bean.cards.ColumnPage
import io.reactivex.Observable

/**
 * 文件： SearchPresenter
 * 描述：
 * 作者： YangJunQuan   2018-8-27.
 */
class SearchPresenter(val mContext: Context) : SearchContract.Presenter {
    private val model = SearchModel()

    fun getSearchHotWord(): Observable<List<String>> {
        return model.getSearchHotWord()
    }

    fun searchByKeyWord(query: String): Observable<ColumnPage> {
        return model.searchByKeyWord(query)
    }

    fun searchMore(nextPageUrl: String): Observable<ColumnPage> {
        return model.searchMore(nextPageUrl)
    }

}