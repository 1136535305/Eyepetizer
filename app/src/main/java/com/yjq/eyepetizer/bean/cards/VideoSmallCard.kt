package com.yjq.eyepetizer.bean.cards

import com.yjq.eyepetizer.bean.*

/**
 * 文件： VideoSmallCard
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */

data class VideoSmallCard(
        val dataType: String, //VideoBeanForClient
        val id: Int, //120945
        val title: String, //回忆杀！5 分钟听完 20 年欧美金曲
        val description: String, //这是 3 位歌手 Nikita Afonso、Stephen Scaccia 和 Randy C 联手合作的超强混音单曲，20 年间的劲歌金曲一次性听个够。有后街男孩、小甜甜、牛姐等等。比拼曲库的时刻到了，每年的代表作你都听出来了吗？From TheSafehouseProject
        val library: String, //DAILY
        val tags: List<Tag>,
        val consumption: Consumption,
        val resourceType: String, //video
        val slogan: String, //暴露年龄的时候到了
        val provider: Provider,
        val category: String, //音乐
        val author: Author,
        val cover: Cover,
        val playUrl: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=120945&resourceType=video&editionType=default&source=aliyun
        val thumbPlayUrl: Any, //null
        val duration: Int, //317
        val webUrl: WebUrl,
        val releaseTime: Long, //1533949200000
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
        val date: Long, //1533949200000
        val promotion: Any, //null
        val label: Any, //null
        val labelList: List<Any>,
        val descriptionEditor: String, //这是 3 位歌手 Nikita Afonso、Stephen Scaccia 和 Randy C 联手合作的超强混音单曲，20 年间的劲歌金曲一次性听个够。有后街男孩、小甜甜、牛姐等等。比拼曲库的时刻到了，每年的代表作你都听出来了吗？From TheSafehouseProject
        val collected: Boolean, //false
        val played: Boolean, //false
        val subtitles: List<Any>,
        val lastViewTime: Any, //null
        val playlists: Any, //null
        val src: Any //null
)

