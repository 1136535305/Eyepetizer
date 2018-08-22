package com.yjq.eyepetizer.constant

/**
 * 文件： ViewTypeEnum
 * 描述： 列表UI视图类型枚举类
 * 作者： YangJunQuan   2018-8-20.
 */
enum class ViewTypeEnum(var value: Int) {
    Invalid(-1),
    HorizontalScrollCard(0),
    TextCard(1),
    FollowCard(2),
    VideoSmallCard(3),
    BriefCard(4),
    SquareCardCollection(5),
    VideoCollectionWithBrief(6),
    AutoPlayFollowCard(7);

    companion object {
        fun getViewTypeEnum(type: String): Int {
            return when (type) {
                "textCard" -> TextCard.value
                "briefCard" -> BriefCard.value
                "followCard" -> FollowCard.value
                "videoSmallCard" -> VideoSmallCard.value
                "autoPlayFollowCard" -> AutoPlayFollowCard.value
                "horizontalScrollCard" -> HorizontalScrollCard.value
                "squareCardCollection" -> SquareCardCollection.value
                "videoCollectionWithBrief" -> VideoCollectionWithBrief.value
                else -> Invalid.value
            }

        }
    }

}