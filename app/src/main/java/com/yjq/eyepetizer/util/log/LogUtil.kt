package com.yjq.eyepetizer.util.log

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * 文件： LogUtil
 * 描述： 对logger框架进行了一层封装,使用前需执行init方法进行初始化
 * 作者： YangJunQuan   2018-7-31.
 */

object LogUtil {

    private val TAG = "LogUtil"

    fun init() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)   //是否显示线程信息，默认为true
                .methodCount(2)         //显示的方法的行数，默认2
                .methodOffset(5)        //隐藏内部方法调用到偏移量，默认5
                .tag(TAG)               //每个日志的全局标记前缀，默认为PRETTY_LOGGER
                .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {   //打印日志的条件:ERROR级别日志或是Debug模式
                return true
            }
        })
    }


    fun d(message: String, vararg args: Any) {
        Logger.d(message, *args)
    }

    fun d(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).d(message, *args)
    }

    fun d(`object`: Any?) {
        Logger.d(`object`)
    }

    fun e(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).e(message, *args)
    }

    fun e(message: String, vararg args: Any) {
        Logger.e(null, message, *args)
    }

    fun e(throwable: Throwable?, message: String, vararg args: Any) {
        Logger.e(throwable, message, *args)
    }

    fun i(message: String, vararg args: Any) {
        Logger.i(message, *args)
    }

    fun i(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).i(message, *args)
    }

    fun v(message: String, vararg args: Any) {
        Logger.v(message, *args)
    }

    fun v(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).v(message, *args)
    }

    fun w(message: String, vararg args: Any) {
        Logger.w(message, *args)
    }

    fun w(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).w(message, *args)
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    fun wtf(message: String, vararg args: Any) {
        Logger.wtf(message, *args)
    }

    /**
     * Formats the given json content and print it
     */
    fun json(json: String?) {
        Logger.json(json)
    }

    /**
     * Formats the given xml content and print it
     */
    fun xml(xml: String?) {
        Logger.xml(xml)
    }
}
