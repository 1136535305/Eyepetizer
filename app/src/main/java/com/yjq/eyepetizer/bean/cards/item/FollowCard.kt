package com.yjq.eyepetizer.bean.cards.item

import com.yjq.eyepetizer.bean.cards.Content
import com.yjq.eyepetizer.bean.cards.Header

/**
 * 文件： FollowCard
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */
data class FollowCard(
        val dataType: String, //FollowCard
        val header: Header,
        val content: Content,
        val adTrack: Any //null
)
