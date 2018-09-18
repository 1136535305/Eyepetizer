package com.yjq.eyepetizer.ui.video

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cn.jzvd.Jzvd
import com.google.gson.Gson
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseActivity
import com.yjq.eyepetizer.bean.cards.*
import com.yjq.eyepetizer.bean.cards.item.AutoPlayFollowCard
import com.yjq.eyepetizer.bean.cards.item.DynamicInfoCard
import com.yjq.eyepetizer.bean.cards.item.FollowCard
import com.yjq.eyepetizer.bean.cards.item.VideoSmallCard
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.fromJson
import com.yjq.eyepetizer.ui.video.adapter.VideoPlayAdapter
import com.yjq.eyepetizer.ui.video.bean.VideoBeanForClient
import com.yjq.eyepetizer.ui.video.mvp.VideoPlayContract
import com.yjq.eyepetizer.ui.video.mvp.VideoPlayPresenter
import com.yjq.eyepetizer.util.image.ImageLoader
import com.yjq.eyepetizer.util.log.LogUtil
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_video.*

/**
 * 文件： VideoPlayActivity
 * 描述： 视频播放页面
 * 作者： YangJunQuan   2018-9-11.
 */
class VideoPlayActivity : BaseActivity(), VideoPlayContract.View {


    //static
    companion object {
        const val TAG = "VideoPlayActivity"
    }

    //data
    private lateinit var videoId: String                  //视频标志ID
    private lateinit var videoTitle: String               //视频标题
    private lateinit var videoPlayUrl: String             //视频播放地址Url
    private lateinit var videoFeedUrl: String             //视频封面地址Url
    private lateinit var blurredBackgroundUrl: String     //高斯模糊背景图片Url


    //mvp
    private lateinit var mPresenter: VideoPlayPresenter

    //state
    private var onLoadVideoDetailDone = false
    private var onLoadVideoRelatedDone = false

    //other
    private lateinit var mAdapter: VideoPlayAdapter


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
        mAdapter = VideoPlayAdapter(this)
        mPresenter = VideoPlayPresenter(this)

        parseIntent()

        initView()

        requestData()
    }

    private fun requestData() {

        val videoDetailObservable = mPresenter.getVideoDetail(videoId)
        val videoRelatedObservable = mPresenter.getVideoRelated(videoId)

        Observable.merge(videoDetailObservable, videoRelatedObservable)
                .compose(RxUtil.applySchedulers())
                .compose(bindToLifecycle())
                .subscribe(object : RxBaseObserver<Any>(this) {
                    override fun onNext(t: Any) {
                        when (t) {
                            is Data -> {
                                onLoadVideoDetailDone = true
                                mAdapter.mHeaderData = t

                            }
                            is ColumnPage -> {
                                onLoadVideoRelatedDone = true
                                mAdapter.mRelatedDataList = t.itemList.filterNot { it.type != "videoSmallCard" } as ArrayList<Item>
                            }
                        }

                    }

                    override fun onComplete() {
                        if (onLoadVideoDetailDone && onLoadVideoRelatedDone)
                            mAdapter.notifyDataSetChanged()
                    }
                })

    }


    private fun parseIntent() {
        videoId = intent.getStringExtra("VIDEO_ID")
        videoTitle = intent.getStringExtra("VIDEO_TITLE")
        videoFeedUrl = intent.getStringExtra("VIDEO_FEED_URL")
        videoPlayUrl = intent.getStringExtra("VIDEO_PLAY_URL")
        blurredBackgroundUrl = intent.getStringExtra("VIDEO_BG")
    }


    private fun initView() {


        //背景图片
        ImageLoader.loadNetBitmap(this, blurredBackgroundUrl) { root.background = it }

        //视频播放器初始化
        with(videoPlayer) {
            setUp(videoPlayUrl, videoTitle, Jzvd.SCREEN_WINDOW_NORMAL)
            ImageLoader.loadNetImage(context, thumbImageView, videoFeedUrl)
            //  startVideo()
        }


        //列表
        with(videoRecycler) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    /**
     * ***************************************** 自定义网络数据申请回调  **************************************
     */


    override fun onNetError() {


    }

    override fun onLoading(isLoad: Boolean) {

    }

}


