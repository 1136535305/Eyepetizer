package com.yjq.eyepetizer.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.yjq.eyepetizer.CommonViewHolder
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.bean.ItemListBean
import com.yjq.eyepetizer.databinding.ItemHomeBinding
import com.yjq.eyepetizer.inflate
import com.yjq.eyepetizer.util.image.ImageLoader

/**
 * 文件： HomeAdapter
 * 描述：
 * 作者： YangJunQuan   2018-8-7.
 */
class HomeAdapter(var mContext: Context, var mDataList: MutableList<ItemListBean>? = null) : RecyclerView.Adapter<CommonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return parent.inflate<ItemHomeBinding>(R.layout.item_home)
    }

    override fun getItemCount(): Int {
        return mDataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val mBinding = DataBindingUtil.getBinding<ItemHomeBinding>(holder.itemView) ?: return

        val bean = mDataList!![position]


        //init data
        val title = bean.data?.title
        val category = bean.data?.category
        val bgUrl = bean.data?.cover?.feed
        val avatarUrl = bean.data?.author?.icon
        val slogan = bean.data?.slogan


        //calculate time
        val minute = bean.data?.duration?.div(60)
        val second = bean.data?.duration?.minus((minute?.times(60)) as Long)
        val realMinute = if (minute!! < 10) "0" + minute else minute.toString()
        val realSecond = if (second!! < 10) "0" + second else second.toString()

        //init view
        with(mBinding) {
            tvTitle.text = title                                            //视频标题
            tvSlogan.text = slogan                                          //视频发布者的签名短语
            tvType.text = category                                          //视频类型
            tvVideoDuration.text = "$realMinute:$realSecond"                //视频时长
            ImageLoader.loadCircleImage(mContext, ivAvatar, avatarUrl)      //视频发布者头像
            ImageLoader.loadImage(mContext, ivBg, bgUrl)                    //视频封面
        }


        //init event
        mBinding.root.setOnClickListener {
            //TODO ①保存记录 ②跳转VideoDetailActivity
        }

    }

}