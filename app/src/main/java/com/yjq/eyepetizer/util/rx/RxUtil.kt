package com.yjq.eyepetizer.util.rx

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 文件： RxUtil
 * 描述： RxJava 2.X 工具封装
 * 作者： YangJunQuan   2018-8-6.
 */
object RxUtil {


    /**
     * RxJava线程切换
     * ObservableTransformer:将函数应用于上游Observable并返回具有可选的具有不同类型的ObservableSource
     */
    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun <T> createData(t: T): Observable<T> {
        return Observable.create { emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

}

