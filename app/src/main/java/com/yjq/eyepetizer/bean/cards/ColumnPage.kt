package com.yjq.eyepetizer.bean.cards

import com.google.gson.JsonObject

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
        val data: JsonObject,//不解析该字段，因为字段该字段类型会随着type的变化而变化
        val tag: Any, //null
        val id: Int, //0
        val adIndex: Int //-1
)





