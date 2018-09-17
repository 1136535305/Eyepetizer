package com.yjq.eyepetizer.bean.cards.item

import com.yjq.eyepetizer.bean.cards.*

/**
 * 文件： AutoPlayFollowCard
 * 描述：
 * 作者： YangJunQuan   2018-9-5.
 */

data class AutoPlayFollowCard(
        val dataType: String, //FollowCard
        val header: Header,
        val content: Content,
        val adTrack: Any //null
)

data class Header(
        val id: Int, //124422
        val actionUrl: String, //eyepetizer://pgc/detail/938/?title=æ¬§ç¾å¹¿åç²¾é&userType=PGC&tabIndex=1
        val labelList: Any, //null
        val icon: String, //http://img.kaiyanapp.com/e44ed5fcfa424ba35761ce5f1339bc16.jpeg?imageMogr2/quality/60/format/jpg
        val iconType: String, //round
        val time: Long, //1536105614000
        val showHateVideo: Boolean, //false
        val followType: String, //author
        val tagId: Int, //0
        val tagName: Any, //null
        val issuerName: String //欧美广告精选
)



