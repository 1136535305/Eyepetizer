package com.yjq.eyepetizer.base

/**
 * 文件： BaseView
 * 描述：
 * 作者： YangJunQuan   2018-8-7.
 */
interface BaseView {
    fun onNetError()
    fun showLoading(isLoad: Boolean)
}