package com.yjq.eyepetizer.ui.video

import android.os.Bundle
import cn.jzvd.Jzvd
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseActivity
import com.yjq.eyepetizer.util.image.ImageLoader
import kotlinx.android.synthetic.main.activity_video.*

/**
 * 文件： VideoPlayActivity
 * 描述：
 * 作者： YangJunQuan   2018-9-11.
 */
class VideoPlayActivity : BaseActivity() {

    companion object {
        const val TAG = "VideoPlayActivity"
    }

    //data
    private lateinit var videoPlayUrl: String        //视频播放地址Url
    private lateinit var videoFeedUrl: String        //视频封面地址Url
    private lateinit var videoDuration: String        //视频播放时长
    private lateinit var videoTitle: String           //视频标题


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        parseIntent()
        initView()
    }


    private fun parseIntent() {

        videoTitle = intent.getStringExtra("VIDEO_TITLE")
        videoFeedUrl = intent.getStringExtra("VIDEO_FEED_URL")
        videoPlayUrl = intent.getStringExtra("VIDEO_PLAY_URL")
        videoDuration = intent.getStringExtra("VIDEO_DURATION")
    }

    private fun initView() {
        with(videoPlayer) {
            setUp(videoPlayUrl, videoTitle, Jzvd.SCREEN_WINDOW_NORMAL)
            ImageLoader.loadNetImageWithCorner(context, thumbImageView, videoFeedUrl)
        }
    }


    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }


    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()     //释放资源
    }

}