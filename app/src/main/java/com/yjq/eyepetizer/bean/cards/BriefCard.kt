package com.yjq.eyepetizer.bean.cards

/**
 * 文件： BriefCard
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */

data class BriefCard(
		val dataType: String, //BriefCard
		val id: Int, //28
		val icon: String, //http://img.kaiyanapp.com/cd74ae49d45ab6999bcd55dbae6d550f.jpeg
		val iconType: String, //square
		val title: String, //#搞笑
		val subTitle: Any, //null
		val description: String, //哈哈哈哈哈哈哈哈
		val actionUrl: String, //eyepetizer://category/28/?title=æç¬
		val adTrack: Any, //null
		val follow: Follow,
		val ifPgc: Boolean //false
)

