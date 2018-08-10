package com.yjq.eyepetizer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.adapter.HomeAdapter
import com.yjq.eyepetizer.bean.HomeBean
import com.yjq.eyepetizer.bean.ItemListBean
import com.yjq.eyepetizer.mvp.contract.HomeContract
import com.yjq.eyepetizer.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.regex.Pattern

/**
 * 文件： HomeFragment
 * 描述：
 * 作者： YangJunQuan   2018-8-7.
 */
class HomeFragment : BaseFragment(), HomeContract.View {

    private var mAdapter: HomeAdapter? = null
    private var mPresenter: HomePresenter? = null
    private var mDataList = ArrayList<ItemListBean>()
    private var mIsRefresh = false
    private var data: String? = null

    override fun getLayoutResources(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

        mAdapter = HomeAdapter(context!!, mDataList)
        mPresenter = HomePresenter(context, this)
        mPresenter?.start()


        //init recyclerView
        with(homeRcy) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val lastVisiblePosition = (recyclerView?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    //知道下次加载更多的url，当滑动到最后一项并且停止滑动后加载更多数据
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisiblePosition == mDataList.size - 1) {
                        data?.let { mPresenter?.requestMoreData(data) }
                    }

                }
            })
        }


        //init refreshLayout
        with(refreshLayout) {
            setOnRefreshListener {
                if (!mIsRefresh) {
                    mIsRefresh = true
                    mPresenter?.start()
                }
            }

        }
    }

    override fun setData(bean: HomeBean) {

        //加载下一页所需 参数
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()

        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false

            //清空以前数据
            if (mDataList.size > 0)
                mDataList.clear()
        }

        //筛选，仅保留类型为video的数据项
        bean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mDataList.add(it) }

        mAdapter?.mDataList = mDataList
        mAdapter?.notifyDataSetChanged()


    }

    override fun showTip(message: String) {
    }

}
