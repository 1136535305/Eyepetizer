package com.yjq.eyepetizer.bean.cards

/**
 * 文件： Category
 * 描述： 首页栏目列表，目前发现的栏目有：
 * 【发现】【推荐】【日报】【社区】【广告】【生活】【动画】【搞笑】【开胃】【创意】
 * 【运动】【音乐】【萌宠】【剧情】【科技】【旅行】【影视】【记录】【游戏】【综艺】【时尚】
 * 作者： YangJunQuan   2018-8-17.
 */

data class Columns(
        val tabInfo: TabInfo
)

data class TabInfo(
        val tabList: List<Tab>,
        val defaultIdx: Int //1
)

data class Tab(
        val id: Int, //-1
        val name: String, //发现
        val apiUrl: String //http://baobab.kaiyanapp.com/api/v5/index/tab/discovery
)