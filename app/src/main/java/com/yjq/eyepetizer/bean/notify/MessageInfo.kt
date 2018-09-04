package com.yjq.eyepetizer.bean.notify

import com.yjq.eyepetizer.bean.error.ErrorResult

/**
 * 文件： MessageInfo
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */

data class MessageInfo(
        val messageList: List<Message>,
        val updateTime: Long, //1520841600000
        val nextPageUrl: String //null
) : ErrorResult()

data class Message(
        val id: Int, //454949
        val title: String, //官方通知
        val content: String, //开眼好友功能上线啦~在评论区看到志同道合的朋友，点个「关注」即可收到 TA 最新的评论和喜欢咯~！点击查看功能详情>>
        val date: Long, //1520841600000
        val actionUrl: String, //eyepetizer://webview/?title=%E5%A5%BD%E5%8F%8B%E5%8A%9F%E8%83%BD%E4%B8%8A%E7%BA%BF%E5%95%A6~&url=http%3A%2F%2Fwww.kaiyanapp.com%2Fguide%2Ffollow-guide.html
        val icon: String, //http://img.wdjimg.com/image/video/418d281e65bf010c38c7b07bdd7b6a94_0_0.png
        val ifPush: Boolean, //false
        val pushStatus: Int, //0
        val uid: String //null
)