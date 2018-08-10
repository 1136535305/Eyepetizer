package com.yjq.eyepetizer.util.other

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.yjq.eyepetizer.R

/**
 * 文件： CustomDialog
 * 描述： 自定义Dialog工具类
 * 作者： YangJunQuan   2018-8-3.
 */

class CustomDialog private constructor(builder: Builder) : Dialog(builder.context, builder.themeResId) {

    private lateinit var rootView: View
    private var canceledable = true            //点击Dialog外部区域或物理回退键  是否使Dialog消失
    private var canceledTouchOutside = true  //点击Dialog外部区域
    private var location = CustomDialogLocation.BOTTOM


    enum class CustomDialogLocation {
        BOTTOM,  //底部弹出，带动画效果
        CENTER;  //中间显示
    }


    init {
        this.canceledable = builder.cancelable
        this.canceledTouchOutside = builder.canceledTouchOutSite
        this.rootView = builder.rootView
        this.location = builder.location
    }


    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)

        setContentView(rootView)
        setCanceledOnTouchOutside(canceledTouchOutside)
        setCancelable(canceledable)

        when (location) {
            CustomDialog.CustomDialogLocation.BOTTOM -> initBottomLayoutParams()
            CustomDialog.CustomDialogLocation.CENTER -> initCenterLayoutParams()
        }


    }

    //设置中间Dialog的LayoutParams属性
    private fun initCenterLayoutParams() {

        window.attributes = window.attributes.apply {
            gravity = Gravity.CENTER
        }
    }


    //设置底部Dialog的LayoutParams属性
    private fun initBottomLayoutParams() {

        window.decorView.setPadding(0, 0, 0, 0)  //去掉padding，让dialog等同于屏幕宽度
        window.attributes = window.attributes.apply {
            windowAnimations = R.style.animTranslate
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.BOTTOM
        }


    }


    class Builder(val context: Context) {
        var location: CustomDialogLocation = CustomDialogLocation.CENTER
        var canceledTouchOutSite = true                                //点击Dialog外部区域
        var cancelable = true                                          //点击Dialog外部区域或物理回退键  是否使Dialog消失
        var themeResId = R.style.customDialog
        lateinit var rootView: View

        fun view(@LayoutRes resView: Int): Builder {
            rootView = LayoutInflater.from(context).inflate(resView, null)
            return this
        }

        fun textViewContent(@IdRes viewRes: Int, content: String): Builder {
            (rootView.findViewById<View>(viewRes) as TextView).text = content
            return this
        }

        fun addViewOnClick(@IdRes viewRes: Int, listener: View.OnClickListener): Builder {
            rootView.findViewById<View>(viewRes).setOnClickListener(listener)
            return this
        }

        fun themeResId(@StyleRes themeResId: Int): Builder {
            this.themeResId = themeResId
            return this
        }

        fun cancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        fun canceledTouchOutside(canceledTouchOutside: Boolean): Builder {
            this.canceledTouchOutSite = canceledTouchOutside
            return this
        }

        fun location(location: CustomDialogLocation): Builder {
            this.location = location
            return this
        }

        fun build(): CustomDialog {
            return CustomDialog(this)
        }
    }
}
