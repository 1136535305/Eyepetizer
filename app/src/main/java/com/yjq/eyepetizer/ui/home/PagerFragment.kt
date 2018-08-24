package com.yjq.eyepetizer.ui.home

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.ScrollingTabContainerView
import android.view.View
import android.widget.Toast
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.bean.ColumnPage
import com.yjq.eyepetizer.bean.Item
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.ui.home.adapter.HomePagerAdapter
import com.yjq.eyepetizer.ui.home.mvp.HomeContract
import com.yjq.eyepetizer.ui.home.mvp.HomePresenter
import com.yjq.eyepetizer.util.log.LogUtil
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
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


    //data
    private var nextPaeUrl: String? = null
    private var firstPageUrl: String? = null
    private var mItemList: List<Item>? = null
    private var lastCompleteVisiblePosition = -1


    //允许展示的列表Item项UI类型
    private var viewTypeList = listOf(
            ViewTypeEnum.TextCard, ViewTypeEnum.BriefCard, ViewTypeEnum.DynamicInfoCard,
            ViewTypeEnum.FollowCard, ViewTypeEnum.VideoSmallCard)


    //other
    private lateinit var mAdapter: HomePagerAdapter
    private lateinit var mPresenter: HomePresenter


    override fun getLayoutResources(): Int {
        return R.layout.fragment_home_pager
    }

    override fun initView() {
        mAdapter = HomePagerAdapter(context!!)
        mPresenter = HomePresenter(context!!, this)


        //api接口
        firstPageUrl = arguments?.getString("API_URL")


        //隐藏无网络相关UI
        netError.visibility = View.GONE


        //初始化recycleView
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter

            //底部加载
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    val totalCount = layoutManager.itemCount - 1
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && totalCount == lastCompleteVisiblePosition) {
                        loadData(nextPaeUrl, loadMore = true)
                    }

                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    with(layoutManager as LinearLayoutManager) {
                        lastCompleteVisiblePosition = findLastCompletelyVisibleItemPosition()       //记录底部可见的位置
                    }

                }
            })
        }


        //初始化refreshLayout
        with(refresh) {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.dark))
            setOnRefreshListener { loadData(firstPageUrl, loadMore = false) }
        }


        //加载首页
        loadData(firstPageUrl, loadMore = false)


    }


    private fun loadData(apiUrl: String?, loadMore: Boolean) {

        if (apiUrl == null) return

        mPresenter.getColumnPage(apiUrl)
                .compose(RxUtil.applySchedulers())  //切换线程
                .compose(bindToLifecycle())         //RxLifeCycle2.X 避免fragment被销毁后仍进行网络数据请求导致内存泄漏等错误
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(t: ColumnPage) {
                        //筛选数据
                        mItemList = t.itemList.filter {
                            viewTypeList.contains(ViewTypeEnum.getViewTypeEnum(it.type))
                        }

                        //展示数据
                        mAdapter.setData(mItemList as ArrayList<Item>, loadMore)

                        //加载下一页所需API接口
                        nextPaeUrl = t.nextPageUrl
                    }
                })

    }


    /**
     * ****************************************      自定义回调处理    **********************************************
     */


    //网络错误处理
    override fun onNetError() {

        if (mItemList?.size ?: 0 == 0)          //在没有数据的前提下，才会提醒用户，避免网络错误提示遮蔽了原有的列表
            netError.visibility = View.VISIBLE

        //点击重试
        netError.setOnClickListener {
            netError.visibility = View.GONE
            loadData(firstPageUrl, loadMore = false)
        }
    }


    //加载进度条处理
    override fun showLoading(isLoad: Boolean) {
        refresh.isRefreshing = isLoad
    }

}