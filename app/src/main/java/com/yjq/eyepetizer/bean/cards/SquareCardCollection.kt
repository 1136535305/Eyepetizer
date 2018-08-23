package com.yjq.eyepetizer.bean.cards

import com.yjq.eyepetizer.bean.Header
import com.yjq.eyepetizer.bean.Item

/**
 * 文件： SquareCardCollection
 * 描述：
 * 作者： YangJunQuan   2018-8-22.
 */

data class SquareCardCollection(
        val dataType: String, //ItemCollection
        val header: Header,
        val itemList: List<Item>,
        val count: Int, //5
        val adTrack: Any //null
)


