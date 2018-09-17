package com.yjq.eyepetizer.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.flexbox.FlexboxLayout
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.yjq.eyepetizer.CommonViewHolder
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.bean.cards.item.*
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.databinding.*
import com.yjq.eyepetizer.inflate
import com.yjq.eyepetizer.ui.video.VideoPlayActivity
import com.yjq.eyepetizer.util.image.ImageLoader
import com.yjq.eyepetizer.util.time.TimeUtil


/**
 * 文件： HomePagerAdapter
 * 描述：
 * 作者： YangJunQuan   2018-8-20.
 */
class HomePagerAdapter(val mContext: Context) : RecyclerView.Adapter<CommonViewHolder>() {

    //data
    private var mDataList = ArrayList<Item>()

    //state
    private var ifShowNoMore = false


    /**
     * **************************************************  声明分割线   ***********************************************************
     */

    fun setData(data: ArrayList<Item>, loadMore: Boolean = false) {

        if (loadMore)
            mDataList.addAll(data)  //显示更多
        else
            mDataList = data        //刷新或者显示首页

        notifyDataSetChanged()
    }


    fun setNoMore(noMoreData: Boolean) {

        if (ifShowNoMore && noMoreData) return

        if (!ifShowNoMore && noMoreData) {

            //构造NoMore时的数据项供解析
            mDataList.add(Item("theEnd", JsonObject(), -1, -1, -1))
            notifyDataSetChanged()
            ifShowNoMore = true
        }

        if (!noMoreData)
            ifShowNoMore = false
    }


    override fun getItemCount() = mDataList.size


    override fun getItemViewType(position: Int): Int {
        val type = mDataList[position].type
        return ViewTypeEnum.getViewTypeEnum(type).value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {

        val viewHolder =
                when (viewType) {
                    ViewTypeEnum.TheEnd.value -> parent.inflate<ItemTheEndBinding>(R.layout.item_the_end)
                    ViewTypeEnum.TextCard.value -> parent.inflate<ItemTextCardBinding>(R.layout.item_text_card)
                    ViewTypeEnum.BriefCard.value -> parent.inflate<ItemBriefCardBinding>(R.layout.item_brief_card)
                    ViewTypeEnum.FollowCard.value -> parent.inflate<ItemFollowCardBinding>(R.layout.item_follow_card)
                    ViewTypeEnum.VideoSmallCard.value -> parent.inflate<ItemVideoSmallCardBinding>(R.layout.item_video_small_card)
                    ViewTypeEnum.DynamicInfoCard.value -> parent.inflate<ItemDynamicInfoCardBinding>(R.layout.item_dynamic_info_card)
                    ViewTypeEnum.AutoPlayFollowCard.value -> parent.inflate<ItemAutoPlayFollowCardBinding>(R.layout.item_auto_play_follow_card)
                    ViewTypeEnum.SquareCardCollection.value -> parent.inflate<ItemSquareCardCollectionBinding>(R.layout.item_square_card_collection)
                    else -> throw Exception("invalid view type")
                }

        return CommonViewHolder(viewHolder.itemView)
    }


    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ViewTypeEnum.TheEnd.value -> initTheEndView(holder, position)
            ViewTypeEnum.TextCard.value -> initTextCardView(holder, position)
            ViewTypeEnum.BriefCard.value -> initBriefCardView(holder, position)
            ViewTypeEnum.FollowCard.value -> initFollowCardView(holder, position)
            ViewTypeEnum.VideoSmallCard.value -> initVideoSmallCardView(holder, position)
            ViewTypeEnum.DynamicInfoCard.value -> initDynamicInfoCardView(holder, position)
            ViewTypeEnum.AutoPlayFollowCard.value -> initAutoPlayFollowCardView(holder, position)
            ViewTypeEnum.SquareCardCollection.value -> initSquareCardCollectionView(holder, position)
        }
    }


    /**
     * **********************************************   下面是各种类型的ItemView 初始化渲染方法   *******************************************************
     */

    //没有更多数据，到底部的提示ItemView
    private fun initTheEndView(holder: CommonViewHolder, position: Int) {
        val itemTheEndBinding = DataBindingUtil.getBinding<ItemTheEndBinding>(holder.itemView)!!

        with(itemTheEndBinding) {
            tvEnd.typeface = Typeface.createFromAsset(mContext.assets, "fonts/Lobster-1.4.otf")
        }
    }


    private fun initFollowCardView(holder: CommonViewHolder, position: Int) {
        val itemFollowCardBinding = DataBindingUtil.getBinding<ItemFollowCardBinding>(holder.itemView)

        //init data
        val jsonObject = mDataList[position].data
        val followCard = Gson().fromJson(jsonObject, FollowCard::class.java)
        val videoJson = Gson().toJson(jsonObject)

        val avatarUrl = followCard.header.icon                                                           //发布者头像
        val title = followCard.content.data.title                                                        //标题
        val playUrl = followCard.content.data.playUrl                                                    //视频播放地址
        val feedUrl = followCard.content.data.cover.detail                                               //发布内容对应封面
        val description = "${followCard.header.title}  /  #${followCard.content.data.category}"          //描述
        val duration = TimeUtil.getFormatHMS(followCard.content.data.duration * 1000.toLong())        //视频时长

        with(itemFollowCardBinding!!) {

            //init view
            tvTitle.text = title
            tvSlogan.text = description
            tvVideoDuration.text = duration

            ImageLoader.loadNetImageWithCorner(mContext, ivBg, feedUrl)
            ImageLoader.loadNetCircleImage(mContext, ivAvatar, avatarUrl, placeHolderId = R.mipmap.avatar_default)

            //init Event
            ivBg.setOnClickListener { startVideoActivity(videoJson, position) }
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
        val videoJson = Gson().toJson(jsonObject)

        val videoTitle = videoSmallCard.title                                                       //视频标题
        val videoPlayUrl = videoSmallCard.playUrl                                                   //视频播放地址
        val videoFeedUrl = videoSmallCard.cover.detail                                              //视频封面Url
        val videoCategory = "#" + videoSmallCard.category                                           //视频类别
        val videoDuration = TimeUtil.getFormatHMS(videoSmallCard.duration * 1000.toLong())       //视频时长



        with(itemVideoSmallCardBinding!!) {

            //init view
            tvTitle.text = videoTitle
            tvCatogory.text = videoCategory
            tvVideoDuration.text = videoDuration
            ImageLoader.loadNetImageWithCorner(mContext, ivFeed, videoFeedUrl)

            //init Event
            holder.itemView.setOnClickListener { startVideoActivity(videoJson, position) }
        }

    }


    private fun initBriefCardView(holder: CommonViewHolder, position: Int) {
        val itemBriefCardBinding = DataBindingUtil.getBinding<ItemBriefCardBinding>(holder.itemView)

        //init data
        val jsonObject = mDataList[position].data
        val briefCard = Gson().fromJson(jsonObject, BriefCard::class.java)

        val title = briefCard.title
        val iconUrl = briefCard.icon
        val iconType = briefCard.iconType
        val description = briefCard.description

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
        val videoJson = Gson().toJson(jsonObject)
        val dynamicInfoCard = Gson().fromJson(jsonObject, DynamicInfoCard::class.java)


        val text = dynamicInfoCard.text                               //。。。
        val avatarUrl = dynamicInfoCard.user.avatar                   //评论者头像Url
        val authorName = dynamicInfoCard.user.nickname                //评论者昵称
        val replyMessage = dynamicInfoCard.reply.message              //评论内容
        val ifHotReply = dynamicInfoCard.reply.ifHotReply             //是否是热评
        val videoTitle = dynamicInfoCard.simpleVideo.title            //视频标题
        val videoPlayUrl = dynamicInfoCard.simpleVideo.playUrl        //视频播放地址
        val videoType = "#${dynamicInfoCard.simpleVideo.category}"    //视频类型
        val likeCount = dynamicInfoCard.reply.likeCount.toString()    //评论点赞数
        val videoFeedUrl = dynamicInfoCard.simpleVideo.cover.detail   //视频封面图片
        val timeStamp = TimeUtil.timeStamp2Date(dynamicInfoCard.createDate, "yyyy/MM/dd")             //评论时间
        val videoDuration = TimeUtil.getFormatHMS(dynamicInfoCard.simpleVideo.duration * 1000.toLong())       //视频时长



        with(itemDynamicInfoCardBinding!!) {


            //init view
            ImageLoader.loadNetCircleImage(mContext, ivAvatar, avatarUrl)
            tvText.text = text
            tvAuthor.text = authorName
            tvReplyMessage.text = replyMessage


            ImageLoader.loadNetImageWithCorner(mContext, ivFeed, videoFeedUrl, corner = 12)
            tvDate.text = timeStamp
            tvVieoType.text = videoType
            tvLikeCount.text = likeCount
            tvVideoName.text = videoTitle
            tvVideoDuration.text = videoDuration


            //如果是【热评】，相关UI可见
            labelHotComment.visibility = if (ifHotReply) View.VISIBLE else View.GONE
            ivGoHotComment.visibility = if (ifHotReply) View.VISIBLE else View.GONE


            //init Event
            bg.setOnClickListener { startVideoActivity(videoJson, position) }

        }
    }


    private fun initAutoPlayFollowCardView(holder: CommonViewHolder, position: Int) {
        val itemAutoPlayFollowCardBinding = DataBindingUtil.getBinding<ItemAutoPlayFollowCardBinding>(holder.itemView)

        //init data
        val jsonObject = mDataList[position].data
        val videoJson = Gson().toJson(jsonObject)

        val autoPlayFollowCard = Gson().fromJson(jsonObject, AutoPlayFollowCard::class.java)
        val iconUrl = autoPlayFollowCard.header.icon                                           //头像Url
        val tags = autoPlayFollowCard.content.data.tags                                        //标签列表
        val title = autoPlayFollowCard.content.data.title                                      //标题
        val issueName = autoPlayFollowCard.header.issuerName                                   //头像代表名称
        val playUrl = autoPlayFollowCard.content.data.playUrl                                  //视频播放地址
        val videoDuration = autoPlayFollowCard.content.data.duration                           //视频时长
        val description = autoPlayFollowCard.content.data.description                          //内容
        val videoCoverUrl = autoPlayFollowCard.content.data.cover.detail                       //视频封面Url
        val replyCount = autoPlayFollowCard.content.data.consumption.replyCount                //评论数
        val collectionCount = autoPlayFollowCard.content.data.consumption.collectionCount      //点赞数


        with(itemAutoPlayFollowCardBinding!!) {


            //initView
            ImageLoader.loadNetCircleImage(mContext, ivAvatar, iconUrl)
            ImageLoader.loadNetImageWithCorner(mContext, ivVideoCover, videoCoverUrl)

            tvTitle.text = title
            tvIssueName.text = issueName
            tvDescription.text = description
            tvReply.text = replyCount.toString()
            tvCollectionCount.text = collectionCount.toString()
            tvDuration.text = TimeUtil.getFormatHMS(videoDuration * 1000.toLong())       //视频时长


            flexLayout.removeAllViews()
            val layoutParams = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)




            tags?.forEach { itemTag ->
                val itemView = LayoutInflater.from(mContext).inflate(R.layout.textview_tag, flexLayout, false)
                itemView.findViewById<TextView>(R.id.tvTag).text = itemTag.name
                flexLayout.addView(itemView, layoutParams)
            }


            //init  Event
            ivVideoCover.setOnClickListener { startVideoActivity(videoJson, position) }

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


    /**
     * **************************************************************    功能性页面跳转   *********************************************
     */


    //启动视频播放页面
    private fun startVideoActivity(videoJson: String, position: Int) {
        mContext.startActivity(
                Intent(mContext, VideoPlayActivity::class.java).apply {
                    putExtra("VIDEO_JSON", videoJson)
                    putExtra("JSON_TYPE", getItemViewType(position))
                }
        )
    }

}
