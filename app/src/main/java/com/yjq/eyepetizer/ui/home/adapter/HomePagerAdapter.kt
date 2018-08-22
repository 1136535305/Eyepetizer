package com.yjq.eyepetizer.ui.home.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.yjq.eyepetizer.CommonViewHolder
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.bean.Item
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.databinding.ItemFollowCardBinding
import com.yjq.eyepetizer.databinding.ItemTextCardBinding
import com.yjq.eyepetizer.inflate

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
        return ViewTypeEnum.getViewTypeEnum(type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val viewHolder =
                when (viewType) {
                    ViewTypeEnum.TextCard.value -> parent.inflate<ItemTextCardBinding>(R.layout.item_text_card)
                    ViewTypeEnum.FollowCard.value -> parent.inflate<ItemFollowCardBinding>(R.layout.item_follow_card)
                    else -> throw Exception("invalid view type")
                }

        return CommonViewHolder(viewHolder.itemView)
    }


    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ViewTypeEnum.TextCard.value -> initTextCardView(holder)
            ViewTypeEnum.FollowCard.value -> initFollowCardView(holder)
            else -> throw Exception("invalid view type")
        }
    }


    /**
     * **********************************************   下面是各种类型的ItemView 初始化渲染方法   *******************************************************
     */

    private fun initFollowCardView(holder: CommonViewHolder) {
        val itemFollowCardBinding = DataBindingUtil.getBinding<ItemFollowCardBinding>(holder.itemView)


        //init view
        with(itemFollowCardBinding) {

        }
    }

    private fun initTextCardView(holder: CommonViewHolder) {
        val itemTextCardBinding = DataBindingUtil.getBinding<ItemTextCardBinding>(holder.itemView)

        //init view
        with(itemTextCardBinding) {

        }

    }
}