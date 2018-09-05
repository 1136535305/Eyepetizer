package com.yjq.eyepetizer.ui.notify.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.yjq.eyepetizer.CommonViewHolder
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.bean.notify.Message
import com.yjq.eyepetizer.databinding.ItemNotifyMessageBinding
import com.yjq.eyepetizer.inflate
import com.yjq.eyepetizer.util.image.ImageLoader

/**
 * 文件： NotifyTabAdapter
 * 描述： 【通知】->【官方】 列表适配器
 * 作者： YangJunQuan   2018-9-3.
 */
class NotifyTabAdapter(private val mContext: Context) : RecyclerView.Adapter<CommonViewHolder>() {


    private var mDataList = mutableListOf<Message>()


    fun setData(data: MutableList<Message>) {
        mDataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            parent.inflate<ItemNotifyMessageBinding>(R.layout.item_notify_message)

    override fun getItemCount() = mDataList.size

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val itemNotifyMessageBinding = DataBindingUtil.getBinding<ItemNotifyMessageBinding>(holder.itemView)!!
        val message = mDataList[position]


        //init item view
        with(itemNotifyMessageBinding) {
            // tvTime.text = message.date.toString()
            tvAuthor.text = message.title
            tvNotifyContent.text = message.content
            ImageLoader.loadNetCircleImage(mContext, ivAvatar, message.icon)
        }
    }

}