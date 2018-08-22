package com.yjq.eyepetizer.bean

/**
 * 文件： ColumnPage
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */

data class ColumnPage(
        val itemList: List<Item>,
        val count: Int, //16
        val total: Int, //0
        val nextPageUrl: String, //http://baobab.kaiyanapp.com/api/v5/index/tab/discovery?start=0
        val adExist: Boolean //false
)

data class Item(
        val type: String, //horizontalScrollCard
        val data: Any,
        val tag: Any, //null
        val id: Int, //0
        val adIndex: Int //-1
)





