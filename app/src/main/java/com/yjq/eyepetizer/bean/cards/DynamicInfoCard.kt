package com.yjq.eyepetizer.bean.cards

import com.yjq.eyepetizer.bean.Cover
import com.yjq.eyepetizer.bean.Reply
import com.yjq.eyepetizer.bean.SimpleVideo
import com.yjq.eyepetizer.bean.User

/**
 * 文件： DynamicInfoCard
 * 描述：
 * 作者： YangJunQuan   2018-8-23.
 */

data class DynamicInfoCard(
        val dataType: String, //DynamicReplyCard
        val dynamicType: String, //reply
        val text: String, //评论:
        val actionUrl: String, //eyepetizer://replies/video?videoId=121650&top=1031731642546257920&videoTitle=æ¥å¼å¾çº¹ä¹æ­ï¼æ£®ç½ä¸è±¡çåå½¢ç¾
        val user: User,
        val mergeNickName: Any, //null
        val mergeSubTitle: Any, //null
        val merge: Boolean, //false
        val createDate: Long, //1534818967000
        val simpleVideo: SimpleVideo,
        val reply: Reply
)

