package com.yjq.eyepetizer.constant

/**
 * 文件： ViewTypeEnum
 * 描述： 列表UI视图类型枚举类
 * 作者： YangJunQuan   2018-8-20.
 */
enum class ViewTypeEnum(var value: Int) {

    TheEnd(-2),
    Invalid(-1),
    Banner2(0),
    TextCard(1),
    BriefCard(2),
    FollowCard(3),
    VideoSmallCard(4),
    AutoPlayVideoAd(5),
    DynamicInfoCard(6),
    PictureFollowCard(7),
    AutoPlayFollowCard(8),
    VideoBeanForClient(9),
    SquareCardCollection(10),
    HorizontalScrollCard(11),
    VideoCollectionWithBrief(12);

    companion object {
        fun getViewTypeEnum(type: String): ViewTypeEnum {
            return when (type) {
                "theEnd" -> TheEnd
                "banner2" -> Banner2
                "textCard" -> TextCard
                "briefCard" -> BriefCard
                "followCard" -> FollowCard
                "videoSmallCard" -> VideoSmallCard
                "autoPlayVideoAd" -> AutoPlayVideoAd
                "DynamicInfoCard" -> DynamicInfoCard
                "pictureFollowCard" -> PictureFollowCard
                "VideoBeanForClient" -> VideoBeanForClient
                "autoPlayFollowCard" -> AutoPlayFollowCard
                "squareCardCollection" -> SquareCardCollection
                "horizontalScrollCard" -> HorizontalScrollCard
                "videoCollectionWithBrief" -> VideoCollectionWithBrief
                else -> Invalid
            }

        }
    }

}