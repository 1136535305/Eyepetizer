package com.yjq.eyepetizer.bean.cards.item

/**
 * 文件： AutoPlayFollowCard
 * 描述：
 * 作者： YangJunQuan   2018-9-5.
 */

data class AutoPlayFollowCard(
		val dataType: String, //FollowCard
		val header: Header,
		val content: Content,
		val adTrack: Any //null
)

data class Header(
		val id: Int, //124422
		val actionUrl: String, //eyepetizer://pgc/detail/938/?title=æ¬§ç¾å¹¿åç²¾é&userType=PGC&tabIndex=1
		val labelList: Any, //null
		val icon: String, //http://img.kaiyanapp.com/e44ed5fcfa424ba35761ce5f1339bc16.jpeg?imageMogr2/quality/60/format/jpg
		val iconType: String, //round
		val time: Long, //1536105614000
		val showHateVideo: Boolean, //false
		val followType: String, //author
		val tagId: Int, //0
		val tagName: Any, //null
		val issuerName: String //欧美广告精选
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
		val id: Int, //124422
		val title: String, //老妈也要唱嘻哈，原来可以这么酷
		val description: String, //Lazada 是一家东南亚的购物网站，此广告不按套路出牌，点开五秒以为是走温情路线，可没想到尽如此燃爆现场……
		val library: String, //DEFAULT
		val tags: List<Tag>,
		val consumption: Consumption,
		val resourceType: String, //video
		val slogan: Any, //null
		val provider: Provider,
		val category: String, //广告
		val author: Author,
		val cover: Cover,
		val playUrl: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=124422&resourceType=video&editionType=default&source=aliyun
		val thumbPlayUrl: Any, //null
		val duration: Int, //107
		val webUrl: WebUrl,
		val releaseTime: Long, //1536105614000
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
		val date: Long, //1536105614000
		val promotion: Any, //null
		val label: Any, //null
		val labelList: List<Any>,
		val descriptionEditor: String, //Lazada 是一家东南亚的购物网站，此广告不按套路出牌，点开五秒以为是走温情路线，可没想到尽如此燃爆现场……
		val collected: Boolean, //false
		val played: Boolean, //false
		val subtitles: List<Any>,
		val lastViewTime: Any, //null
		val playlists: Any, //null
		val src: Any //null
)

data class Provider(
		val name: String, //YouTube
		val alias: String, //youtube
		val icon: String //http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
)

data class PlayInfo(
		val height: Int, //480
		val width: Int, //854
		val urlList: List<Url>,
		val name: String, //标清
		val type: String, //normal
		val url: String //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=124422&resourceType=video&editionType=normal&source=aliyun
)

data class Url(
		val name: String, //aliyun
		val url: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=124422&resourceType=video&editionType=normal&source=aliyun
		val size: Int //14388723
)

data class Cover(
		val feed: String, //http://img.kaiyanapp.com/8b93065a67fa21c088a316eba1bbcd94.png?imageMogr2/quality/60/format/jpg
		val detail: String, //http://img.kaiyanapp.com/8b93065a67fa21c088a316eba1bbcd94.png?imageMogr2/quality/60/format/jpg
		val blurred: String, //http://img.kaiyanapp.com/eb84510aba6519730c024ef8e91d18ab.png?imageMogr2/quality/60/format/jpg
		val sharing: Any, //null
		val homepage: String //http://img.kaiyanapp.com/8b93065a67fa21c088a316eba1bbcd94.png?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
)

data class WebUrl(
		val raw: String, //http://www.eyepetizer.net/detail.html?vid=124422
		val forWeibo: String //http://www.eyepetizer.net/detail.html?vid=124422
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

data class Consumption(
		val collectionCount: Int, //4
		val shareCount: Int, //0
		val replyCount: Int //0
)

data class Author(
		val id: Int, //938
		val icon: String, //http://img.kaiyanapp.com/e44ed5fcfa424ba35761ce5f1339bc16.jpeg?imageMogr2/quality/60/format/jpg
		val name: String, //欧美广告精选
		val description: String, //持续推送新奇、有趣、大开眼界的欧美创意广告
		val link: String,
		val latestReleaseTime: Long, //1536105615000
		val videoNum: Int, //489
		val adTrack: Any, //null
		val follow: Follow,
		val shield: Shield,
		val approvedNotReadyVideoCount: Int, //0
		val ifPgc: Boolean //true
)

data class Follow(
		val itemType: String, //author
		val itemId: Int, //938
		val followed: Boolean //false
)

data class Shield(
		val itemType: String, //author
		val itemId: Int, //938
		val shielded: Boolean //false
)