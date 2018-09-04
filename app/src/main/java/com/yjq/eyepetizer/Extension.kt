package com.yjq.eyepetizer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.gson.Gson

/**
 * 文件： Extension
 * 描述： Kotlin扩展函数
 * 作者： YangJunQuan   2018-8-6.
 */


/**
 * 启动新的Activity简化
 */
inline fun <reified T : Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}


/**
 * Toast简化
 */
fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


/**
 * 通用的RecyclerViewHolder
 */
class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


/**
 * 返回ViewHolder的简便扩展方法(kotlin+dataBinding)
 */
fun <T : ViewDataBinding> ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToParent: Boolean = false): CommonViewHolder {
    return CommonViewHolder(DataBindingUtil.inflate<T>(LayoutInflater.from(context), layoutRes, this, attachToParent).root)
}


/**
 * Gson 解析
 */
inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)


/**
 * Activity中隐藏键盘
 */
fun Activity.hideKeyBoard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(this.currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}


/**
 * Fragment中隐藏键盘
 */
fun Fragment.hideKeyBoard(view: View) {
    (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
}