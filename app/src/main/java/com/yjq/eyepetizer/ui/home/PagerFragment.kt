package com.yjq.eyepetizer.ui.home

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.bean.Item
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.ui.home.adapter.HomePagerAdapter
import com.yjq.eyepetizer.ui.home.mvp.HomeContract
import com.yjq.eyepetizer.ui.home.mvp.HomePresenter
import com.yjq.eyepetizer.util.log.LogUtil
import kotlinx.android.synthetic.main.fragment_home_pager.*

/**
 * 文件： PagerFragment
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */
class PagerFragment : BaseFragment(), HomeContract.View {


    //static
    companion object {
        fun newInstance(apiUrl: String) = PagerFragment().apply { arguments = Bundle().apply { putString("API_URL", apiUrl) } }
    }

    private lateinit var mAdapter: HomePagerAdapter
    private lateinit var mPresenter: HomePresenter
    private var mItemList: List<Item>? = null
    private var viewTypeList = listOf(ViewTypeEnum.TextCard, ViewTypeEnum.FollowCard, ViewTypeEnum.VideoSmallCard)       //允许展示的Item项UI类型

    override fun getLayoutResources(): Int {
        return R.layout.fragment_home_pager
    }

    override fun initView() {
        mAdapter = HomePagerAdapter(context!!)
        mPresenter = HomePresenter(context!!, this)

        initRecycleView()
        initData()
    }


    private fun initData() {
        val apiUrl = arguments?.getString("API_URL")
        mPresenter.getColumnPage(apiUrl!!)
                .subscribe {
                    mItemList = it.itemList.filter {
                        viewTypeList.contains(ViewTypeEnum.getViewTypeEnum(it.type))
                    }
                    mAdapter.mDataList = mItemList as ArrayList<Item>
                    mAdapter.notifyDataSetChanged()
                }
    }

    private fun initRecycleView() {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

}