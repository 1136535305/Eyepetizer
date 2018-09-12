package com.yjq.eyepetizer.util.rx

import com.yjq.eyepetizer.base.BaseView
import com.yjq.eyepetizer.util.log.LogUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


/**
 * 文件： RxBaseObserver
 * 描述： 对返回错误、加载进度做统一处理
 * 作者： YangJunQuan   2018-8-23.
 */
abstract class RxBaseObserver<T>(view: BaseView) : Observer<T> {

    private val TAG = "RxBaseObserver"

    private val mView: BaseView = view


    override fun onNext(t: T) {

    }

    override fun onComplete() {
        mView.onLoading(false)
    }

    override fun onSubscribe(d: Disposable) {
        mView.onLoading(true)
    }

    override fun onError(e: Throwable) {
        LogUtil.d(TAG, e.toString())

        mView.onLoading(false)
        when (e) {
            is HttpException -> mView.onNetError()
            is ConnectException -> mView.onNetError()
            is TimeoutException -> mView.onNetError()
            is UnknownHostException -> mView.onNetError()
            is SocketTimeoutException -> mView.onNetError()
        }
    }


}
