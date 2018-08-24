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


    fun loadNetImageWithCorner(context: Context, imageView: ImageView, imageUrl: String?, corner: Int = 20) {
        GlideApp.with(context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterCropRoundCornerTransform(corner))
                .into(imageView)

    }


    fun loadNetCircleImage(context: Context,
                           imageView: ImageView,
                           imageUrl: String?,
                           @DrawableRes errorId: Int = R.mipmap.ic_empty_picture,
                           @DrawableRes placeHolderId: Int = R.mipmap.avatar_default) {


        GlideApp.with(context)
                .load(imageUrl)
                .placeholder(placeHolderId)
                .error(errorId)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

    }

}
