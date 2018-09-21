package com.yjq.eyepetizer.ui.video

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.jzvd.Jzvd
import com.google.gson.Gson
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseActivity
import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Data
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.bean.cards.item.VideoSmallCard
import com.yjq.eyepetizer.ui.video.adapter.VideoPlayAdapter
import com.yjq.eyepetizer.ui.video.mvp.VideoPlayContract
import com.yjq.eyepetizer.ui.video.mvp.VideoPlayPresenter
import com.yjq.eyepetizer.util.image.ImageLoader
import com.yjq.eyepetizer.util.log.LogUtil
import com.yjq.eyepetizer.util.other.ToastUtil
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

    //data from intent
    private lateinit var videoId: String                  //视频标志ID
    private lateinit var videoTitle: String               //视频标题
    private lateinit var videoPlayUrl: String             //视频播放地址Url
    private lateinit var videoFeedUrl: String             //视频封面地址Url
    private lateinit var blurredBackgroundUrl: String     //高斯模糊背景图片Url

    //data from net request
    private var mHeaderData: Data? = null
    private var mRelatedDataList: List<Item>? = null

    //mvp
    private lateinit var mPresenter: VideoPlayPresenter

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

        requestData(videoId)
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
        }


        //列表
        with(videoRecycler) {
            layoutManager = LinearLayoutManager(context)
            visibility = View.GONE

            //列表点击事件
            adapter = mAdapter.apply {
                onItemClick = { position -> changePlayVideo(position) }
            }
        }
    }


    private fun requestData(videoId: String) {

        val videoRelatedObservable = mPresenter.getVideoRelated(videoId)
        val videoDetailObservable = mPresenter.getVideoDetail(videoId)

        Observable.merge(videoDetailObservable, videoRelatedObservable)
                .compose(RxUtil.applySchedulers())
                .compose(bindToLifecycle())
                .subscribe(object : RxBaseObserver<Any>(this) {
                    override fun onNext(resultBean: Any) {
                        when (resultBean) {
                            is Data -> mHeaderData = resultBean
                            is ColumnPage -> mRelatedDataList = resultBean.itemList.filterNot { it.type != "videoSmallCard" }
                        }

                    }

                    override fun onComplete() {
                        videoRecycler.visibility = View.VISIBLE
                        mAdapter.setData(mHeaderData!!, mRelatedDataList!!)
                    }
                })

    }

    private fun changePlayVideo(position: Int) {


        //init data
        val jsonObject = mRelatedDataList!![position].data
        val videoData = Gson().fromJson(jsonObject, VideoSmallCard::class.java)

        ImageLoader.loadNetBitmap(this, videoData.cover.blurred) { root.background = it }      //整个页面背景图片
        ImageLoader.loadNetImage(this, videoPlayer.thumbImageView, videoData.cover.detail)   //视频封面

        //改变播放视频Url
        videoPlayer.changeUrl(videoData.playUrl, videoData.title, 0)
        videoPlayer.startVideo()

        //刷新列表前重置状态
        videoRecycler.visibility = View.GONE
        videoRecycler.scrollToPosition(0)

        //获取数据
        requestData(videoData.id)
    }


    /**
     * ***************************************** 自定义网络数据申请回调  **************************************
     */


    override fun onNetError() {
        ToastUtil.showShortToast(this, TAG + ":onNetError")
    }

    override fun onLoading(isLoad: Boolean) {

    }

}


