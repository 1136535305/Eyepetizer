package com.yjq.eyepetizer.mvp.presenter

import android.content.Context
import com.yjq.eyepetizer.base.BasePresenter
import com.yjq.eyepetizer.mvp.contract.HomeContract
import com.yjq.eyepetizer.mvp.model.HomeModel
import com.yjq.eyepetizer.ui.activity.BaseActivity
import com.yjq.eyepetizer.util.rx.RxUtil

/**
 * 文件： HomePresenter
 * 描述：
 * 作者： YangJunQuan   2018-8-7.
 */
class HomePresenter(context: Context?, view: HomeContract.View) : HomeContract.Presenter {

    private val mView = view
    private val mContext = context
    private val mModel: HomeModel by lazy {
        HomeModel()
    }


    override fun start() {
        requestData()
    }

    override fun requestData() {
        mContext as BaseActivity
        mModel.loadData(mContext, false, "0")
                ?.compose(RxUtil.applySchedulers())          //线程切换
                ?.compose(mContext.bindToLifecycle())        //RxLife 2.X 防止内存泄漏
                ?.subscribe { mView.setData(it) }
    }

    fun requestMoreData(data: String?) {
        mContext as BaseActivity
        mModel.loadData(mContext, true, data)
                ?.compose(RxUtil.applySchedulers())
                ?.compose(mContext.bindToLifecycle())
                ?.subscribe { mView.setData(it) }
    }


}