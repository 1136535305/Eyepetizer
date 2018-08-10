package com.yjq.eyepetizer.util.image

import android.content.Context
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.yjq.eyepetizer.GlideApp
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.util.log.LogUtil

/**
 * 文件： ImageLoader
 * 描述： 图片加载工具类
 * 作者： YangJunQuan   2018-8-6.
 */

object ImageLoader {

    private val TAG = "ImageLoader"

    fun loadImage(context: Context, @DrawableRes resId: Int, @DrawableRes placeHolder: Int, imageView: ImageView) {
        GlideApp.with(context)
                .load(resId)
                .placeholder(placeHolder)
                .into(imageView)
    }


    fun loadImage(context: Context,
                  imageView: ImageView,
                  imageUrl: String?,
                  @DrawableRes errorId: Int = R.mipmap.ic_empty_picture,
                  @DrawableRes placeHolderId: Int = R.drawable.ic_image_loading) {


        GlideApp.with(context)
                .load(imageUrl)
                .placeholder(placeHolderId)
                .error(errorId)
                .into(imageView)

    }

    fun loadCircleImage(context: Context,
                        imageView: ImageView,
                        @DrawableRes resId: Int,
                        @DrawableRes errorId: Int = R.mipmap.ic_empty_picture,
                        @DrawableRes placeHolderId: Int = R.drawable.ic_image_loading) {

        GlideApp.with(context)
                .load(resId)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(placeHolderId)
                .error(errorId)
                .into(imageView)
    }


    fun loadCircleImage(context: Context,
                        imageView: ImageView,
                        imageUrl: String?,
                        @DrawableRes errorId: Int = R.mipmap.ic_empty_picture,
                        @DrawableRes placeHolderId: Int = R.drawable.ic_image_loading) {


        GlideApp.with(context)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(placeHolderId)
                .error(errorId)
                .into(imageView)

    }

}
