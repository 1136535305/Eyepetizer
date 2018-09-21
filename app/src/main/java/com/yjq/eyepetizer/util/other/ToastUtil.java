package com.yjq.eyepetizer.util.other;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;


/**
 * 文件： ToastUtil
 * 描述： Toast 封装,同时只允许出现一个Toast
 * 作者： YangJunQuan   2018-7-30.
 */

public class ToastUtil {
    private static Toast mToast;

    public static void showLongToast(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        } else {
            mToast.setText(content);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void showLongToast(Context context, @StringRes int stringRes) {
        if (mToast == null) {
            mToast = Toast.makeText(context, stringRes, Toast.LENGTH_LONG);
        } else {
            mToast.setText(stringRes);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }


    public static void showShortToast(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }


    public static void showShortToast(Context context, @StringRes int stringRes) {
        if (mToast == null) {
            mToast = Toast.makeText(context, stringRes, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(stringRes);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}