package com.yjq.eyepetizer.util.rx

import com.yjq.eyepetizer.bean.RootBean
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


    /**
     * 我们只对数据感兴趣，所以用此方法转换Observable拿取result，其余异常情况统一返回ApiException 统一在onError方法中处理
     */
    fun <T> applyResult(): ObservableTransformer<RootBean<T>, T> {
        return ObservableTransformer { upstream ->
            upstream.flatMap { rootBean ->
                if (rootBean.code == "200")
                    createData(rootBean.result)
                else
                    Observable.error(ApiException(rootBean.msg, rootBean.code))
            }
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


data class ApiException(var msg: String, var code: String) : Exception()