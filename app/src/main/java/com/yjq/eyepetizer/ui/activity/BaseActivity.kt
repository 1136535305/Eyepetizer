package com.yjq.eyepetizer.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yjq.eyepetizer.util.log.LogUtil

/**
 * 文件： BaseActivity
 * 描述： 自定义Activity基类
 * 作者： YangJunQuan   2018-8-6.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    companion object {
        const val TAG = "BaseActivity"
    }

    /**
     * ***************************************************** 生命周期 ***************************************************************
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbarStyleInit()    //沉浸式体验
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d(TAG, "onStop")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.d(TAG, "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtil.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.d(TAG, "onResume")
    }

    override fun recreate() {
        super.recreate()
        LogUtil.d(TAG, "recreate")
    }


    /**
     * ***************************************************** 其它 ***************************************************************
     */

    private fun toolbarStyleInit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {                                // 系统版本5.0以上
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    /*| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION*/
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            //window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {                   //系统版本4.2 API 17
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    /*| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION*/
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

}