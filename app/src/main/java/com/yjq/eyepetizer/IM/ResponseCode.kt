package com.yjq.eyepetizer.IM

/**
 * 文件： ResponseCode
 * 描述： 自定义结果返回状态码
 * 作者： YangJunQuan   2018-9-4.
 */
object ResponseCode {

    const val RES_SUCCESS = 200              //成功
    const val RES_ERROR_TIMEOUT = 400        //超时错误
    const val RES_ERROR_UNKNOWN = 500        //未知错误
    const val RES_ERROR_NOT_LOGIN = -2       //未登录
    const val RES_ERROR_CONNECTION = 300     //连接错误
    const val RES_ERROR_CAST_CLASS = 600     //类型装换异常
}