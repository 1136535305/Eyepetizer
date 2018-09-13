package com.yjq.eyepetizer.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.yjq.eyepetizer.R;
import com.yjq.eyepetizer.util.sys.ScreenUtil;

import cn.jzvd.JZDataSource;
import cn.jzvd.JzvdStd;

/**
 * 文件： CustomJzvdStd
 * 描述： 自定义Jzvd播放器UI
 * 作者： YangJunQuan   2018-9-13.
 */

public class CustomJzvdStd extends JzvdStd {

    private Context context;
    private ImageView videoShare;
    private ImageView videoLike;
    private ImageView videoMoreAction;

    public CustomJzvdStd(Context context) {
        super(context);
        this.context = context;
    }

    public CustomJzvdStd(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_jz_layout;
    }


    //布局里新增加的UI初始化
    @Override
    public void init(Context context) {
        super.init(context);
        videoLike = findViewById(R.id.video_like);
        videoShare = findViewById(R.id.video_share);
        videoMoreAction = findViewById(R.id.video_more_action);

        videoLike.setOnClickListener(this);
        videoShare.setOnClickListener(this);
        videoMoreAction.setOnClickListener(this);
    }


    @Override
    public void setUp(JZDataSource jzDataSource, int screen) {
        super.setUp(jzDataSource, screen);


        //修改了FULLSCREEN_ORIENTATION 参数为ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        //使播放器点击全屏后强制全屏并且是横屏的，
        //默认情况点击全屏后是竖屏的，并且根据重力感应调整屏幕方向。
        FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;


        //非全屏状态下的变化
        if (currentScreen == SCREEN_WINDOW_NORMAL || currentScreen == SCREEN_WINDOW_LIST) {
            backButton.setVisibility(View.VISIBLE);                            //回退按钮R.id.back 可见
            titleTextView.setVisibility(View.GONE);                            //不显示视频标题R.id.title
            fullscreenButton.setImageResource(R.mipmap.ic_action_full_screen); //修改图标背景图片R.id.fullscreen
        }


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        //非全屏状态点击 回退按钮R.id.back 退出当前播放Activity
        if (currentScreen == SCREEN_WINDOW_NORMAL && v.getId() == R.id.back) {
            ((Activity) context).finish();
        }


        switch (v.getId()) {
            case R.id.video_like:
                Toast.makeText(getContext(), "like", Toast.LENGTH_SHORT).show();
                break;
            case R.id.video_share:
                Toast.makeText(getContext(), "share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.video_more_action:
                Toast.makeText(getContext(), "more_action", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //修改播放、暂停、重播 R.id.start 图标
    @Override
    public void updateStartImage() {
        if (currentState == CURRENT_STATE_PLAYING) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.mipmap.ic_player_pause);
            replayTextView.setVisibility(INVISIBLE);
        } else if (currentState == CURRENT_STATE_ERROR) {
            startButton.setVisibility(INVISIBLE);
            replayTextView.setVisibility(INVISIBLE);
        } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.mipmap.ic_player_reload);
            replayTextView.setVisibility(VISIBLE);
        } else {
            startButton.setImageResource(R.mipmap.ic_player_play);
            replayTextView.setVisibility(INVISIBLE);
        }
    }


    //修改播放按钮大小
    @Override
    public void changeStartButtonSize(int size) {
        size = ScreenUtil.INSTANCE.dip2px(80);
        ViewGroup.LayoutParams lp = startButton.getLayoutParams();
        lp.height = size;
        lp.width = size;
    }


}
