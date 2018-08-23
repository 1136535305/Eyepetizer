package com.yjq.eyepetizer.ui.home.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.google.gson.Gson
import com.yjq.eyepetizer.CommonViewHolder
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.bean.Item
import com.yjq.eyepetizer.bean.cards.FollowCard
import com.yjq.eyepetizer.bean.cards.TextCard
import com.yjq.eyepetizer.bean.cards.VideoSmallCard
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.databinding.ItemFollowCardBinding
import com.yjq.eyepetizer.databinding.ItemTextCardBinding
import com.yjq.eyepetizer.databinding.ItemVideoSmallCardBinding
import com.yjq.eyepetizer.inflate
import com.yjq.eyepetizer.util.image.ImageLoader
import com.yjq.eyepetizer.util.time.TimeUtil


/**
 * 文件： HomePagerAdapter
 * 描述：
 * 作者： YangJunQuan   2018-8-20.
 */
class HomePagerAdapter(val mContext: Context) : RecyclerView.Adapter<CommonViewHolder>() {


    public var mDataList = ArrayList<Item>()

    override fun getItemCount() = mDataList.size

    override fun getItemViewType(position: Int): Int {
        val type = mDataList[position].type
        return ViewTypeEnum.getViewTypeEnum(type).value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {

        val viewHolder =
                when (viewType) {
                    ViewTypeEnum.TextCard.value -> parent.inflate<ItemTextCardBinding>(R.layout.item_text_card)
                    ViewTypeEnum.FollowCard.value -> parent.inflate<ItemFollowCardBinding>(R.layout.item_follow_card)
                    ViewTypeEnum.VideoSmallCard.value->parent.inflate<ItemVideoSmallCardBinding>(R.layout.item_video_small_card)
                    else -> throw Exception("invalid view type")
                }

        return CommonViewHolder(viewHolder.itemView)
    }


    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ViewTypeEnum.TextCard.value -> initTextCardView(holder, position)
            ViewTypeEnum.FollowCard.value -> initFollowCardView(holder, position)
            ViewTypeEnum.VideoSmallCard.value -> initVideoSmallCardView(holder, position)
            else -> throw Exception("invalid view type")
        }
    }


    /**
     * **********************************************   下面是各种类型的ItemView 初始化渲染方法   *******************************************************
     */

    private fun initFollowCardView(holder: CommonViewHolder, position: Int) {
        val itemFollowCardBinding = DataBindingUtil.getBinding<ItemFollowCardBinding>(holder.itemView)

        //init data
        val jsonObject = mDataList[position].data
        val followCard = Gson().fromJson(jsonObject, FollowCard::class.java)


        val title = followCard.content.data.title                                                        //标题
        val description = followCard.header.title + "  /  #" + followCard.content.data.category          //描述
        val avatarUrl = followCard.header.icon                                                           //发布者头像
        val feedUrl = followCard.content.data.cover.detail                                               //发布内容对应封面
        val duration = TimeUtil.getFormatHMS(followCard.content.data.duration * 1000.toLong())

        //init view
        with(itemFollowCardBinding!!) {
            tvTitle.text = title
            tvSlogan.text = description
            tvVideoDuration.text = duration
            ImageLoader.loadCircleImage(mContext, ivAvatar, avatarUrl, placeHolderId = R.mipmap.avatar_default)
            ImageLoader.loadImage(mContext, ivBg, feedUrl)
        }
    }


    private fun initTextCardView(holder: CommonViewHolder, position: Int) {
        val itemTextCardBinding = DataBindingUtil.getBinding<ItemTextCardBinding>(holder.itemView)


        //init data
        val jsonObject = mDataList[position].data
        val textCard = Gson().fromJson(jsonObject, TextCard::class.java)


        //init view
        with(itemTextCardBinding!!) {
            tvType.text = textCard.text
        }

    }


    private fun initVideoSmallCardView(holder: CommonViewHolder, position: Int) {
        val itemVideoSmallCardBinding = DataBindingUtil.getBinding<ItemVideoSmallCardBinding>(holder.itemView)


        //init data
        val jsonObject = mDataList[position].data
        val videoSmallCard = Gson().fromJson(jsonObject, VideoSmallCard::class.java)


        val videoTitle = videoSmallCard.title                                                       //视频标题
        val videoFeedUrl = videoSmallCard.cover.detail                                              //视频封面Url
        val videoCategory = "#" + videoSmallCard.category                                           //视频类别
        val videoDuration = TimeUtil.getFormatHMS(videoSmallCard.duration * 1000.toLong())       //视频时长


        //init view
        with(itemVideoSmallCardBinding!!) {
            tvTitle.text = videoTitle
            tvCatogory.text = videoCategory
            tvVideoDuration.text = videoDuration
            ImageLoader.loadImage(mContext, ivFeed, videoFeedUrl)
        }

    }

}