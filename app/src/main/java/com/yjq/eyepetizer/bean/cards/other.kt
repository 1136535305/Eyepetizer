package com.yjq.eyepetizer.bean.cards

/**
 * 文件： other
 * 描述：
 * 作者： YangJunQuan   2018-8-22.
 */

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

data class Label(
        val text: String, //广告
        val card: String, //广告
        val detail: Any //null
)


data class Reply(
        val id: Long, //1031731642546257920
        val videoId: Int, //121650
        val videoTitle: String, //日式图纹之歌：森罗万象的圆形美
        val message: String, //家纹可以说是灰常棒的设计概念了，曾经一时兴起，收集了很多相关的素材
        val likeCount: Int, //7
        val showConversationButton: Boolean, //false
        val parentReplyId: Int, //0
        val rootReplyId: Long, //1031731642546257920
        val ifHotReply: Boolean, //true
        val liked: Boolean, //false
        val parentReply: Any, //null
        val user: User
)

data class User(
        val uid: Int, //300026742
        val nickname: String, //CHARMS.Jong
        val avatar: String, //http://img.kaiyanapp.com/231870dcc13e098d31bf551b07b7558f.jpeg?imageMogr2/quality/60/format/jpg
        val userType: String, //NORMAL
        val ifPgc: Boolean, //false
        val description: Any, //null
        val area: Any, //null
        val gender: String, //male
        val registDate: Long, //1475633460000
        val releaseDate: Long, //1530753110000
        val cover: String, //http://img.kaiyanapp.com/2d787fa27b38b885da055e6914e88acc.jpeg?imageMogr2/quality/60/format/jpg
        val actionUrl: String, //eyepetizer://pgc/detail/300026742/?title=CHARMS.Jong&userType=NORMAL&tabIndex=0
        val followed: Boolean, //false
        val limitVideoOpen: Boolean, //true
        val library: String, //BLOCK
        val uploadStatus: String, //NORMAL
        val bannedDate: Any, //null
        val bannedDays: Int //0
)


data class SimpleVideo(
        val id: Int, //121650
        val resourceType: String, //video
        val uid: Int, //0
        val title: String, //日式图纹之歌：森罗万象的圆形美
        val description: String, //本片是 NHK 教育电视节目「设计啊！」的宣传片，旨在将设计概念传递给儿童。该计划通过鼓励儿童观察周围的世界，发现问题并思考如何使其变得更好，来培养构成设计思维的综合视觉和感性能力。片中用电子音乐搭配变幻的设计，看完是不是被惊艳到了？From NHK
        val cover: Cover,
        val category: String, //创意
        val playUrl: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=121650&resourceType=video&editionType=default&source=aliyun
        val duration: Int, //143
        val releaseTime: Long, //1534813203000
        val consumption: Any, //null
        val collected: Boolean, //false
        val actionUrl: String, //eyepetizer://ugcResourceDetail?id=121650&resourceType=video
        val onlineStatus: String, //ONLINE
        val count: Int //0
)


