package com.yjq.eyepetizer.util.sys

import android.content.Context
import android.util.Log
import com.yjq.eyepetizer.App

object ScreenUtil {
    private val TAG = "Demo.ScreenUtil"

    private val RATIO = 0.85

    var screenWidth: Int = 0
    var screenHeight: Int = 0
    var screenMin: Int = 0// 宽高中，小的一边
    var screenMax: Int = 0// 宽高中，较大的值

    var density: Float = 0.toFloat()
    var scaleDensity: Float = 0.toFloat()
    var xdpi: Float = 0.toFloat()
    var ydpi: Float = 0.toFloat()
    var densityDpi: Int = 0

    var dialogWidth: Int = 0
    var statusbarheight: Int = 0
    var navbarheight: Int = 0

    val displayWidth: Int
        get() {
            if (screenWidth == 0) {
                GetInfo(App.INSTANCE)
            }
            return screenWidth
        }

    val displayHeight: Int
        get() {
            if (screenHeight == 0) {
                GetInfo(App.INSTANCE)
            }
            return screenHeight
        }

    init {
        init(App.INSTANCE)
    }

    fun dip2px(dipValue: Float): Int {
        return (dipValue * density + 0.5f).toInt()
    }

    fun px2dip(pxValue: Float): Int {
        return (pxValue / density + 0.5f).toInt()
    }

    fun sp2px(spValue: Float): Int {
        return (spValue * scaleDensity + 0.5f).toInt()
    }


    fun init(context: Context?) {
        if (null == context) {
            return
        }
        val dm = context.applicationContext.resources.displayMetrics
        screenWidth = dm.widthPixels
        screenHeight = dm.heightPixels
        screenMin = if (screenWidth > screenHeight) screenHeight else screenWidth
        density = dm.density
        scaleDensity = dm.scaledDensity
        xdpi = dm.xdpi
        ydpi = dm.ydpi
        densityDpi = dm.densityDpi

        Log.d(TAG, "screenWidth=$screenWidth screenHeight=$screenHeight density=$density")
    }

    fun GetInfo(context: Context?) {
        if (null == context) {
            return
        }
        val dm = context.applicationContext.resources.displayMetrics
        screenWidth = dm.widthPixels
        screenHeight = dm.heightPixels
        screenMin = if (screenWidth > screenHeight) screenHeight else screenWidth
        screenMax = if (screenWidth < screenHeight) screenHeight else screenWidth
        density = dm.density
        scaleDensity = dm.scaledDensity
        xdpi = dm.xdpi
        ydpi = dm.ydpi
        densityDpi = dm.densityDpi
        statusbarheight = getStatusBarHeight(context)
        navbarheight = getNavBarHeight(context)
        Log.d(TAG, "screenWidth=$screenWidth screenHeight=$screenHeight density=$density")
    }

    fun getStatusBarHeight(context: Context): Int {
        if (statusbarheight == 0) {
            try {
                val c = Class.forName("com.android.internal.R\$dimen")
                val o = c.newInstance()
                val field = c.getField("status_bar_height")
                val x = field.get(o) as Int
                statusbarheight = context.resources.getDimensionPixelSize(x)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        if (statusbarheight == 0) {
            statusbarheight = ScreenUtil.dip2px(25f)
        }
        return statusbarheight
    }

    fun getNavBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }
}
