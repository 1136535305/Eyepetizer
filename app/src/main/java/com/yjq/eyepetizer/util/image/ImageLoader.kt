package com.yjq.eyepetizer.util.image

import android.content.Context
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.yjq.eyepetizer.GlideApp
import com.yjq.eyepetizer.R

/**
 * 文件： ImageLoader
 * 描述： 图片加载工具类
 * 作者： YangJunQuan   2018-8-6.
 */

object ImageLoader {

    private val TAG = "ImageLoader"


    fun loadNetImageWithCorner(context: Context, imageView: ImageView, imageUrl: String?, corner: Int = 20, @DrawableRes placeHolderId: Int = R.drawable.corner_3_gray) {

        GlideApp.with(context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())  //渐入动画效果
                .transform(CenterCropRoundCornerTransform(corner))      //centerCrop +  cornerCrop 效果
                .placeholder(placeHolderId)                             //占位图片
                .into(imageView)

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
