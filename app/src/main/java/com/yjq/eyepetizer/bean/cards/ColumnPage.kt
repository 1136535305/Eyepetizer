package com.yjq.eyepetizer.bean.cards

import com.google.gson.JsonObject

/**
 * 文件： ColumnPage
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */

data class ColumnPage(
        val itemList: List<Item>,
        val count: Int, // 16
        val total: Int, // 0
        val nextPageUrl: String, //http://baobab.kaiyanapp.com/api/v5/index/tab/discovery?start=0
        val adExist: Boolean //false
)

data class Item(
        var type: String, //horizontalScrollCard
        var data: JsonObject,//不解析该字段，因为该字段代表的数据结构会随着type的变化而变化，我们保留原始数据，在具体解析时根据type的不同值进行相应类的类型转换
        var tag: Any, //null
        var id: Int, //0
        var adIndex: Int //-1
)





