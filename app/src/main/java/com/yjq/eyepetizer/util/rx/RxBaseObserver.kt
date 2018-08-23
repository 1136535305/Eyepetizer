package com.yjq.eyepetizer.util.rx

import com.yjq.eyepetizer.base.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

/**
 * 文件： RxBaseObserver
 * 描述： 对返回错误、加载进度做统一处理
 * 作者： YangJunQuan   2018-8-23.
 */
abstract class RxBaseObserver<T>(view: BaseView) : Observer<T> {

    private val mView: BaseView = view


    override fun onNext(t: T) {

    }

    override fun onComplete() {
        mView.showLoading(false)
    }

    override fun onSubscribe(d: Disposable) {
        mView.showLoading(true)
    }

    override fun onError(e: Throwable) {
        mView.showLoading(false)

        when (e) {
            is HttpException -> mView.onNetError()
            else -> mView.onNetError()
        }
    }


}