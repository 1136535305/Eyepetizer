package com.yjq.eyepetizer.ui.home.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.yjq.eyepetizer.CommonViewHolder
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.bean.cards.item.*
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.databinding.*
import com.yjq.eyepetizer.inflate
import com.yjq.eyepetizer.util.image.ImageLoader
import com.yjq.eyepetizer.util.time.TimeUtil


/**
 * 文件： HomePagerAdapter
 * 描述：
 * 作者： YangJunQuan   2018-8-20.
 */
class HomePagerAdapter(private val mContext: Context) : RecyclerView.Adapter<CommonViewHolder>() {


    private var mDataList = ArrayList<Item>()


    fun setData(data: ArrayList<Item>, loadMore: Boolean) {

        if (loadMore)
            mDataList.addAll(data)  //显示更多
        else
            mDataList = data        //刷新或者显示首页

        notifyDataSetChanged()
    }


    override fun getItemCount() = mDataList.size


    override fun getItemViewType(position: Int): Int {
        val type = mDataList[position].type
        return ViewTypeEnum.getViewTypeEnum(type).value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {

        val viewHolder =
                when (viewType) {
                    ViewTypeEnum.TextCard.value -> parent.inflate<ItemTextCardBinding>(R.layout.item_text_card)
                    ViewTypeEnum.BriefCard.value -> parent.inflate<ItemBriefCardBinding>(R.layout.item_brief_card)
                    ViewTypeEnum.FollowCard.value -> parent.inflate<ItemFollowCardBinding>(R.layout.item_follow_card)
                    ViewTypeEnum.VideoSmallCard.value -> parent.inflate<ItemVideoSmallCardBinding>(R.layout.item_video_small_card)
                    ViewTypeEnum.DynamicInfoCard.value -> parent.inflate<ItemDynamicInfoCardBinding>(R.layout.item_dynamic_info_card)
                    ViewTypeEnum.SquareCardCollection.value -> parent.inflate<ItemSquareCardCollectionBinding>(R.layout.item_square_card_collection)
                    else -> throw Exception("invalid view type")
                }

        return CommonViewHolder(viewHolder.itemView)
    }


    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ViewTypeEnum.TextCard.value -> initTextCardView(holder, position)
            ViewTypeEnum.BriefCard.value -> initBriefCardView(holder, position)
            ViewTypeEnum.FollowCard.value -> initFollowCardView(holder, position)
            ViewTypeEnum.VideoSmallCard.value -> initVideoSmallCardView(holder, position)
            ViewTypeEnum.DynamicInfoCard.value -> initDynamicInfoCardView(holder, position)
            ViewTypeEnum.SquareCardCollection.value -> initSquareCardCollectionView(holder, position)
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
        val description = "${followCard.header.title}  /  #${followCard.content.data.category}"          //描述
        val avatarUrl = followCard.header.icon                                                           //发布者头像
        val feedUrl = followCard.content.data.cover.detail                                               //发布内容对应封面
        val duration = TimeUtil.getFormatHMS(followCard.content.data.duration * 1000.toLong())        //视频时长

        //init view
        with(itemFollowCardBinding!!) {
            tvTitle.text = title
            tvSlogan.text = description
            tvVideoDuration.text = duration
            ImageLoader.loadNetCircleImage(mContext, ivAvatar, avatarUrl, placeHolderId = R.mipmap.avatar_default)
            ImageLoader.loadNetImageWithCorner(mContext, ivBg, feedUrl)
        }
    }


    private fun initTextCardView(holder: CommonViewHolder, position: Int) {
        val itemTextCardBinding = DataBindingUtil.getBinding<ItemTextCardBinding>(holder.itemView)


        //init data
        val jsonObject = mDataList[position].data
        val textCard = Gson().fromJson(jsonObject, TextCard::class.java)
        val textType = textCard.type

        //init view
        with(itemTextCardBinding!!) {
            when (textType) {
                "header5" -> {
                    footer2Text.visibility = View.GONE
                    header5Text.visibility = View.VISIBLE
                    header5Text.text = textCard.text
                }
                "footer2" -> {
                    header5Text.visibility = View.GONE
                    footer2Text.visibility = View.VISIBLE
                    footer2Text.text = textCard.text
                }

                else -> Toast.makeText(mContext, "unknown type$textType", Toast.LENGTH_LONG).show()
            }
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
            ImageLoader.loadNetImageWithCorner(mContext, ivFeed, videoFeedUrl)
        }

    }


    private fun initBriefCardView(holder: CommonViewHolder, position: Int) {
        val itemBriefCardBinding = DataBindingUtil.getBinding<ItemBriefCardBinding>(holder.itemView)

        //init data
        val jsonObject = mDataList[position].data
        val briefCard = Gson().fromJson(jsonObject, BriefCard::class.java)

        val description = briefCard.description
        val title = briefCard.title
        val iconUrl = briefCard.icon
        val iconType = briefCard.iconType

        //init view
        with(itemBriefCardBinding!!) {
            tvTitle.text = title
            tvDescription.text = description

            if (iconType == "square")
                ImageLoader.loadNetImageWithCorner(mContext, ivFeed, iconUrl)
            else
                ImageLoader.loadNetCircleImage(mContext, ivFeed, iconUrl)
        }


    }


    private fun initDynamicInfoCardView(holder: CommonViewHolder, position: Int) {

        val itemDynamicInfoCardBinding = DataBindingUtil.getBinding<ItemDynamicInfoCardBinding>(holder.itemView)

        //init data
        val jsonObject = mDataList[position].data
        val dynamicInfoCard = Gson().fromJson(jsonObject, DynamicInfoCard::class.java)


        val avatarUrl = dynamicInfoCard.user.avatar                   //评论者头像Url
        val authorName = dynamicInfoCard.user.nickname                //评论者昵称
        val text = dynamicInfoCard.text                               //。。。
        val replyMessage = dynamicInfoCard.reply.message              //评论内容
        val videoFeedUrl = dynamicInfoCard.simpleVideo.cover.detail   //视频封面图片
        val videoTitle = dynamicInfoCard.simpleVideo.title            //视频标题
        val videoType = "#${dynamicInfoCard.simpleVideo.category}"    //视频类型
        val likeCount = dynamicInfoCard.reply.likeCount.toString()    //评论点赞数
        val timeStamp = TimeUtil.timeStamp2Date(dynamicInfoCard.createDate, "yyyy/MM/dd")             //评论时间
        val videoDuration = TimeUtil.getFormatHMS(dynamicInfoCard.simpleVideo.duration * 1000.toLong())       //视频时长


        //init view
        with(itemDynamicInfoCardBinding!!) {
            ImageLoader.loadNetCircleImage(mContext, ivAvatar, avatarUrl)
            tvAuthor.text = authorName
            tvReplyMessage.text = replyMessage
            tvText.text = text

            ImageLoader.loadNetImageWithCorner(mContext, ivFeed, videoFeedUrl, corner = 12)
            tvVideoName.text = videoTitle
            tvVieoType.text = videoType
            tvVideoDuration.text = videoDuration
            tvDate.text = timeStamp
            tvLikeCount.text = likeCount

        }
    }


    private fun initSquareCardCollectionView(holder: CommonViewHolder, position: Int) {
//        val itemSquareCardCollectionBinding = DataBindingUtil.getBinding<ItemSquareCardCollectionBinding>(holder.itemView)
//
//        //init data
//        val jsonObject = mDataList[position].data
//        val squareCardCollection = Gson().fromJson(jsonObject, SquareCardCollection::class.java)
//
//
//        val imageList = ArrayList<String>()
//        val title = squareCardCollection.header.title
//
//        squareCardCollection.itemList.forEach {
//            val banner2 = Gson().fromJson(it.data, FollowCard::class.java)
//            imageList.add(banner2.header.icon)
//            LogUtil.json(Gson().toJson(squareCardCollection))
//        }
//
//
//
//        with(itemSquareCardCollectionBinding!!) {
//            tvTitle.text = title
//
//
//            banner.setImages(imageList)
//            banner.setImageLoader(object : com.youth.banner.loader.ImageLoader() {
//                override fun displayImage(context: Context?, path: Any?, imageView: ImageView) {
//                    // ImageLoader.loadNetImageWithCorner(mContext, imageView, path as String)
//                    LogUtil.d(path.toString())
//                }
//            })
//            banner.start()
//        }
//
//
    }

}
