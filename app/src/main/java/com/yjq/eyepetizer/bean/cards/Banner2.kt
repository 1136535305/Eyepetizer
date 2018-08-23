package com.yjq.eyepetizer.bean.cards

import com.yjq.eyepetizer.bean.Header
import com.yjq.eyepetizer.bean.Label

/**
 * 文件： Banner2
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */

data class Banner2(
        val dataType: String, //Banner
        val id: Int, //984
        val title: String,
        val description: String,
        val image: String, //http://img.kaiyanapp.com/57f6c68ac7ee0ae6cc6c4add679313b4.jpeg?imageMogr2/quality/60/format/jpg
        val actionUrl: String, //eyepetizer://detail/122710
        val adTrack: Any, //null
        val shade: Boolean, //false
        val label: Label,
        val labelList: List<Label>,
        val header: Header
)


