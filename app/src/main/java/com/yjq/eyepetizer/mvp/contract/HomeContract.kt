package com.yjq.eyepetizer.mvp.contract

import com.yjq.eyepetizer.base.BasePresenter
import com.yjq.eyepetizer.base.BaseView
import com.yjq.eyepetizer.bean.HomeBean

/**
 * 文件： HomeContract
 * 描述：
 * 作者： YangJunQuan   2018-8-7.
 */
interface HomeContract {

    interface View : BaseView<Presenter> {
        fun setData(bean: HomeBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}