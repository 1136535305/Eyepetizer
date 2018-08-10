package com.yjq.eyepetizer.util.sys

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * 文件： NetUtil
 * 描述： 网络工具类
 * 作者： YangJunQuan   2018-6-13.
 */

object NetUtil {

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm == null) {
        } else {
            val info = cm.allNetworkInfo
            if (info != null) {
                for (anInfo in info) {
                    if (anInfo.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        }
        return false
    }
}
