package com.yjq.eyepetizer.bean.cards

/**
 * 文件： FollowCard
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */
data class FollowCard(
        val dataType: String, //FollowCard
        val header: Header,
        val content: Content,
        val adTrack: Any //null
)

data class Content(
        val type: String, //video
        val data: Data,
        val tag: Any, //null
        val id: Int, //0
        val adIndex: Int //-1
)

data class Data(
        val dataType: String, //VideoBeanForClient
        val id: Int, //119506
        val title: String, //日本反转广告：活在社交网络上的女生
        val description: String, //女生们总喜欢自拍，还有人用「拍照 1 分钟，修图半小时」来调侃她们。本片的女主也不例外，但她们为了拍出满意的照片，竟然不顾安全……一开始以为是运动鞋的广告，没想到最后来了个大反转，不得不佩服创意的脑洞。From キンカンチャンネル
        val library: String, //DAILY
        val tags: List<Tag>,
        val consumption: Consumption,
        val resourceType: String, //video
        val slogan: String, //为了拍出好照片，拔山又涉水
        val provider: Provider,
        val category: String, //广告
        val author: Author,
        val cover: Cover,
        val playUrl: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=119506&resourceType=video&editionType=default&source=aliyun
        val thumbPlayUrl: Any, //null
        val duration: Int, //136
        val webUrl: WebUrl,
        val releaseTime: Long, //1534122012000
        val playInfo: List<PlayInfo>,
        val campaign: Any, //null
        val waterMarks: Any, //null
        val adTrack: Any, //null
        val type: String, //NORMAL
        val titlePgc: Any, //null
        val descriptionPgc: Any, //null
        val remark: Any, //null
        val ifLimitVideo: Boolean, //false
        val searchWeight: Int, //0
        val idx: Int, //0
        val shareAdTrack: Any, //null
        val favoriteAdTrack: Any, //null
        val webAdTrack: Any, //null
        val date: Long, //1534122012000
        val promotion: Any, //null
        val label: Any, //null
        val labelList: List<Any>,
        val descriptionEditor: String, //女生们总喜欢自拍，还有人用「拍照 1 分钟，修图半小时」来调侃她们。本片的女主也不例外，但她们为了拍出满意的照片，竟然不顾安全……一开始以为是运动鞋的广告，没想到最后来了个大反转，不得不佩服创意的脑洞。From キンカンチャンネル
        val collected: Boolean, //false
        val played: Boolean, //false
        val subtitles: List<Any>,
        val lastViewTime: Any, //null
        val playlists: Any, //null
        val src: Any //null
)

data class Author(
        val id: Int, //2162
        val icon: String, //http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
        val name: String, //开眼广告精选
        val description: String, //为广告人的精彩创意点赞
        val link: String,
        val latestReleaseTime: Long, //1534726808000
        val videoNum: Int, //1001
        val adTrack: Any, //null
        val follow: Follow,
        val shield: Shield,
        val approvedNotReadyVideoCount: Int, //0
        val ifPgc: Boolean //true
)

data class Shield(
        val itemType: String, //author
        val itemId: Int, //2162
        val shielded: Boolean //false
)

data class Follow(
        val itemType: String, //author
        val itemId: Int, //2162
        val followed: Boolean //false
)

data class Provider(
        val name: String, //YouTube
        val alias: String, //youtube
        val icon: String //http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
)

data class Consumption(
        val collectionCount: Int, //614
        val shareCount: Int, //464
        val replyCount: Int //34
)

data class PlayInfo(
        val height: Int, //480
        val width: Int, //854
        val urlList: List<Url>,
        val name: String, //标清
        val type: String, //normal
        val url: String //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=119506&resourceType=video&editionType=normal&source=aliyun
)

data class Url(
        val name: String, //aliyun
        val url: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=119506&resourceType=video&editionType=normal&source=aliyun
        val size: Int //19716332
)

data class Tag(
        val id: Int, //748
        val name: String, //广告精选
        val actionUrl: String, //eyepetizer://tag/748/?title=å¹¿åç²¾é
        val adTrack: Any, //null
        val desc: Any, //null
        val bgPicture: String, //http://img.kaiyanapp.com/431177a6b2177788aa4d8eff8073d9a7.jpeg?imageMogr2/quality/60/format/jpg
        val headerImage: String, //http://img.kaiyanapp.com/431177a6b2177788aa4d8eff8073d9a7.jpeg?imageMogr2/quality/60/format/jpg
        val tagRecType: String, //IMPORTANT
        val childTagList: Any, //null
        val childTagIdList: Any //null
)

data class WebUrl(
        val raw: String, //http://www.eyepetizer.net/detail.html?vid=119506
        val forWeibo: String //http://www.eyepetizer.net/detail.html?vid=119506&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
)

data class Cover(
        val feed: String, //http://img.kaiyanapp.com/4df0a0806c64ddb3e3f25872922467ee.jpeg?imageMogr2/quality/60/format/jpg
        val detail: String, //http://img.kaiyanapp.com/4df0a0806c64ddb3e3f25872922467ee.jpeg?imageMogr2/quality/60/format/jpg
        val blurred: String, //http://img.kaiyanapp.com/00f80e916e13134f24924bfffe0ea469.jpeg?imageMogr2/quality/60/format/jpg
        val sharing: Any, //null
        val homepage: String //http://img.kaiyanapp.com/4df0a0806c64ddb3e3f25872922467ee.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
)

data class Header(
        val id: Int, //119506
        val title: String, //开眼广告精选
        val font: Any, //null
        val subTitle: Any, //null
        val subTitleFont: Any, //null
        val textAlign: String, //left
        val cover: Any, //null
        val label: Any, //null
        val actionUrl: String, //eyepetizer://pgc/detail/2162/?title=å¼ç¼å¹¿åç²¾é&userType=PGC&tabIndex=1
        val labelList: Any, //null
        val icon: String, //http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
        val iconType: String, //round
        val description: String, //#广告 / 收录于 每日编辑精选
        val time: Long, //1534122012000
        val showHateVideo: Boolean //false
)
