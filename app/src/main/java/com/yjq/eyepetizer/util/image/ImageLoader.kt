package com.yjq.eyepetizer.util.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.yjq.eyepetizer.GlideApp
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.util.sys.ScreenUtil

/**
 * 文件： ImageLoader
 * 描述： 图片加载工具类
 * 作者： YangJunQuan   2018-8-6.
 */

object ImageLoader {

    private val TAG = "ImageLoader"


    fun loadNetImageWithCorner(context: Context, imageView: ImageView, imageUrl: String?, corner: Int = 4, @DrawableRes placeHolderId: Int = R.drawable.corner_4_solid_gray) {

        GlideApp.with(context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())  //渐入动画效果
                .transform(CenterCropRoundCornerTransform(ScreenUtil.dip2px(corner.toFloat()), false))      //centerCrop +  cornerCrop 效果
                .placeholder(placeHolderId)                             //占位图片
                .into(imageView)

    }

    fun loadNetImageWithCornerAndShade(context: Context, imageView: ImageView, imageUrl: String?, corner: Int = 4, @DrawableRes placeHolderId: Int = R.drawable.corner_4_solid_gray) {

        GlideApp.with(context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())  //渐入动画效果
                .transform(CenterCropRoundCornerTransform(ScreenUtil.dip2px(corner.toFloat()), true))      //centerCrop +  cornerCrop 效果
                .placeholder(placeHolderId)                             //占位图片
                .into(imageView)

    }


    fun loadNetImage(context: Context, imageView: ImageView, imageUrl: String?) {

        GlideApp.with(context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())  //渐入动画效果
                .into(imageView)
    }


    fun loadNetBitmap(context: Context, imageUrl: String?, body: (drawable: Drawable) -> Unit) {

        Glide.with(context)
                .asDrawable()
                .load(imageUrl)
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        body.invoke(resource)
                    }
                })
    }


    fun loadNetCircleImage(context: Context, imageView: ImageView, imageUrl: String?, @DrawableRes placeHolderId: Int = R.mipmap.avatar_default) {

        GlideApp.with(context)
                .load(imageUrl)
                .placeholder(placeHolderId)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

    }

}
