package com.yjq.eyepetizer

import android.app.Application
import com.yjq.eyepetizer.util.log.LogUtil

/**
 * 文件： App
 * 描述：
 * 作者： YangJunQuan   2018-8-6.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        //Logger日志工具初始化
        LogUtil.init()
    }

    companion object {
        lateinit var INSTANCE: Application
    }
}