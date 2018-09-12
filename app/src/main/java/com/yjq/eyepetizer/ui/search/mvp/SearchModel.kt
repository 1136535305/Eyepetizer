package com.yjq.eyepetizer.ui.search.mvp

import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.manager.RetrofitManager
import io.reactivex.Observable


/**
 * 文件： SearchModel
 * 描述：
 * 作者： YangJunQuan   2018-8-27.
 */
class SearchModel : SearchContract.Model {

    fun getSearchHotWord(): Observable<List<String>> {
        return RetrofitManager.EyeAPI().getSearchHotWord()
    }

    fun searchByKeyWord(query: String): Observable<ColumnPage> {
        return RetrofitManager.EyeAPI().searchByKeyWord(query)

    }

    fun searchMore(nextPageUrl: String): Observable<ColumnPage> {
        return RetrofitManager.EyeAPI().searchMore(nextPageUrl)
    }

}