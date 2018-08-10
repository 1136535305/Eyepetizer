package com.yjq.eyepetizer.util.other

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast


/**
 * 文件： ToastUtil
 * 描述： Toast 封装,同时只允许出现一个Toast
 * 作者： YangJunQuan   2018-7-30.
 */

object ToastUtil {

    private lateinit var mToast: Toast

    fun showLongToast(context: Context, content: String) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_LONG)
        } else {
            mToast.setText(content)
            mToast.duration = Toast.LENGTH_LONG
        }
        mToast.show()
    }

    fun showLongToast(context: Context, @StringRes stringRes: Int) {
        if (mToast == null) {
            mToast = Toast.makeText(context, stringRes, Toast.LENGTH_LONG)
        } else {
            mToast.setText(stringRes)
            mToast.duration = Toast.LENGTH_LONG
        }
        mToast.show()
    }


    fun showShortToast(context: Context, content: String) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT)
        } else {
            mToast.setText(content)
            mToast.duration = Toast.LENGTH_SHORT
        }
        mToast.show()
    }


    fun showShortToast(context: Context, @StringRes stringRes: Int) {
        if (mToast == null) {
            mToast = Toast.makeText(context, stringRes, Toast.LENGTH_SHORT)
        } else {
            mToast.setText(stringRes)
            mToast.duration = Toast.LENGTH_SHORT
        }
        mToast.show()
    }
}