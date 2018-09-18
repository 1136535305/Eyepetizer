package com.yjq.eyepetizer.ui.video.bean

import com.yjq.eyepetizer.bean.cards.Author
import com.yjq.eyepetizer.bean.cards.Consumption
import com.yjq.eyepetizer.bean.cards.Tag

/**
 * 文件： VideoBeanForClient
 * 描述：
 * 作者： YangJunQuan   2018-9-17.
 */
data class VideoBeanForClient(
        val title: String?,
        val category: String?,
        val description: String?,
        val consumption: Consumption?,
        val tags: List<Tag>?,
        val author: Author?
)