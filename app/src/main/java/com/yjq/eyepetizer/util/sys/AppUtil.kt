package com.yjq.eyepetizer.util.sys

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

/**
 * 文件： AppUtil
 * 描述： App工具类
 * 作者： YangJunQuan   2018-5-18.
 */

object AppUtil {


    //获取版本号
    fun versionCode(context: Context): String {
        val manager = context.packageManager
        var code = 0
        try {
            val info = manager.getPackageInfo(context.packageName, 0)
            code = info.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return code.toString()
    }


    //获取版本名称
    fun versionName(context: Context): String {
        val manager = context.packageManager
        var name: String? = null
        try {
            val info = manager.getPackageInfo(context.packageName, 0)
            name = info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return "V" + name!!
    }


    //判断当前应用是否在后台
    fun isBackground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses
        for (appProcess in appProcesses) {
            if (appProcess.processName == context.packageName) {
                return appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }

}
