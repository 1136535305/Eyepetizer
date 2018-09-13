package com.yjq.eyepetizer.ui.video

import android.os.Bundle
import cn.jzvd.Jzvd
import com.google.gson.Gson
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseActivity
import com.yjq.eyepetizer.bean.cards.Author
import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Consumption
import com.yjq.eyepetizer.bean.cards.Cover
import com.yjq.eyepetizer.bean.cards.item.AutoPlayFollowCard
import com.yjq.eyepetizer.bean.cards.item.DynamicInfoCard
import com.yjq.eyepetizer.bean.cards.item.FollowCard
import com.yjq.eyepetizer.bean.cards.item.VideoSmallCard
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.fromJson
import com.yjq.eyepetizer.ui.video.mvp.VideoPlayContract
import com.yjq.eyepetizer.ui.video.mvp.VideoPlayPresenter
import com.yjq.eyepetizer.util.image.ImageLoader
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
import kotlinx.android.synthetic.main.activity_video.*

/**
 * 文件： VideoPlayActivity
 * 描述： 视频播放页面
 * 作者： YangJunQuan   2018-9-11.
 */
class VideoPlayActivity : BaseActivity(), VideoPlayContract.View {
    companion object {

        const val TAG = "VideoPlayActivity"
    }

    //raw data
    private var jsonType: Int = -1
    private lateinit var videoJson: String


    //data
    private lateinit var videoId: String                  //视频标志ID
    private lateinit var videoTitle: String               //视频标题
    private lateinit var videoPlayUrl: String             //视频播放地址Url
    private lateinit var videoFeedUrl: String             //视频封面地址Url
    private lateinit var videoCatogory: String            //视频类别
    private lateinit var videoLikeCount: String           //视频点赞数
    private lateinit var videoShareCount: String          //视频分享数
    private lateinit var videoReplyCount: String          //视频评论数
    private lateinit var videoDescription: String         //视频内容
    private lateinit var blurredBackgroundUrl: String     //高斯模糊背景图片Url


    private var mCover: Cover? = null
    private var mAuthor: Author? = null
    private var mConsumption: Consumption? = null


    //mvp
    private lateinit var mPresenter: VideoPlayPresenter

    /**
     * **************************************************************   生命周期回调方法   *********************************************
     */

    override fun onBackPressed() {
        if (Jzvd.backPress()) return  //大屏
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()      //释放资源
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        mPresenter = VideoPlayPresenter(this)

        parseIntent()

        analyzeData()

        initView()

        requestVideoRelatedData()
    }


    private fun parseIntent() {
        videoJson = intent.getStringExtra("VIDEO_JSON")
        jsonType = intent.getIntExtra("JSON_TYPE", -1)
    }


    //根据类型解析不同的json原始数据 -> 转换成相应的数据结构 -> 获取所需数据
    private fun analyzeData() {
        when (jsonType) {
            ViewTypeEnum.FollowCard.value -> {
                val followCard = Gson().fromJson<FollowCard>(videoJson)
                videoTitle = followCard.content.data.title
                videoPlayUrl = followCard.content.data.playUrl
                videoId = followCard.content.data.id.toString()
                videoCatogory = followCard.content.data.category
                videoDescription = followCard.content.data.description


                mCover = followCard.content.data.cover
                mAuthor = followCard.content.data.author
                mConsumption = followCard.content.data.consumption
            }
            ViewTypeEnum.VideoSmallCard.value -> {
                val videoSmallCard = Gson().fromJson<VideoSmallCard>(videoJson)
                videoTitle = videoSmallCard.title
                videoPlayUrl = videoSmallCard.playUrl
                videoId = videoSmallCard.id.toString()
                videoDescription = videoSmallCard.description

                mCover = videoSmallCard.cover
                mAuthor = videoSmallCard.author
                mConsumption = videoSmallCard.consumption
            }
            ViewTypeEnum.DynamicInfoCard.value -> {
                val dynamicInfoCard = Gson().fromJson<DynamicInfoCard>(videoJson)
                videoTitle = dynamicInfoCard.simpleVideo.title
                videoPlayUrl = dynamicInfoCard.simpleVideo.playUrl
                videoId = dynamicInfoCard.simpleVideo.id.toString()
                videoDescription = dynamicInfoCard.simpleVideo.description

                mCover = dynamicInfoCard.simpleVideo.cover
                //mAuthor = dynamicInfoCard.simpleVideo.
                mConsumption = dynamicInfoCard.simpleVideo.consumption
            }
            ViewTypeEnum.AutoPlayFollowCard.value -> {
                val autoPlayFollowCard = Gson().fromJson<AutoPlayFollowCard>(videoJson)
                videoTitle = autoPlayFollowCard.content.data.title
                videoPlayUrl = autoPlayFollowCard.content.data.playUrl
                videoId = autoPlayFollowCard.content.data.id.toString()
                videoDescription = autoPlayFollowCard.content.data.description

                mCover = autoPlayFollowCard.content.data.cover
                mAuthor = autoPlayFollowCard.content.data.author
                mConsumption = autoPlayFollowCard.content.data.consumption
            }
        }


        //从关键信息类获取详细
        videoShareCount = mConsumption?.shareCount.toString()
        videoReplyCount = mConsumption?.replyCount.toString()
        videoLikeCount = mConsumption?.collectionCount.toString()

        blurredBackgroundUrl = mCover!!.blurred
        videoFeedUrl = mCover!!.detail


    }


    private fun initView() {


        //背景图片
        ImageLoader.loadNetBitmap(this, blurredBackgroundUrl) { root.background = it }

        //视频标题、内容
        tvVideoTitle.text = videoTitle
        tvVideoDescription.text = videoDescription


        //视频播放器初始化
        with(videoPlayer) {
            setUp(videoPlayUrl, videoTitle, Jzvd.SCREEN_WINDOW_NORMAL)
            ImageLoader.loadNetImageWithCorner(context, thumbImageView, videoFeedUrl)
        }
    }


    private fun requestVideoRelatedData() {
        mPresenter.getVideoRelated(videoId)
                .compose(RxUtil.applySchedulers())
                .compose(bindToLifecycle())
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(t: ColumnPage) {
                        super.onNext(t)
                    }
                })
    }


    /**
     * ***************************************** 自定义网络数据申请回调  **************************************
     */


    override fun onNetError() {


    }

    override fun onLoading(isLoad: Boolean) {

    }

}

/**
 * it is the reason why so many people could not understand the price of being idle.
 * it seems that no one can escape the fate.
 * do whatever you want to do ,and I promise you that you will be paid pack.
 * it is the price why so many reason would not like to find you ,
 */



