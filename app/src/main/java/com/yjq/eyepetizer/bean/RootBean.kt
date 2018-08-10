package com.yjq.eyepetizer.bean

/**
 * 文件： RootBean
 * 描述： 返回结果统一封装类
 * 作者： YangJunQuan   2018-8-6.
 */
data class RootBean<T>(var code: String, var msg: String, var result: T)