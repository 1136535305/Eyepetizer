package com.yjq.eyepetizer.bean.cards.item

/**
 * 文件： TextCard
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */

data class TextCard(
		val dataType: String, //TextCard
		val id: Int, //0
		val type: String, //header5
		val text: String, //本周排行
		val subTitle: Any, //null
		val actionUrl: String, //eyepetizer://ranklist/
		val adTrack: Any, //null
		val follow: Any //null
)