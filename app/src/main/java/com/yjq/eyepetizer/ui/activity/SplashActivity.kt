package com.yjq.eyepetizer.ui.activity

import android.graphics.Typeface
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.newIntent
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * 文件： SplashActivity
 * 描述： 启动页
 * 作者： YangJunQuan   2018-8-6.
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        startAnim()
    }


    private fun initView() {
        val font = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tvNameEnglish.typeface = font
        tvEnglishIntro.typeface = font

    }

    private fun startAnim() {
        val alphaAnimation = AlphaAnimation(0.1f, 1.0f).apply { duration = 1000 }
        val scaleAnimation = ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
                .apply { duration = 1000 }

        val animationSet = AnimationSet(true).apply {
            duration = 1000
            addAnimation(alphaAnimation)
            addAnimation(scaleAnimation)
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    newIntent<MainActivity>()
                    finish()
                }
            })
        }

        ivIconSplash.startAnimation(animationSet)

    }


}