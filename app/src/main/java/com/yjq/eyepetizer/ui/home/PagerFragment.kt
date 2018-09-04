package com.yjq.eyepetizer.ui.home

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.constant.Constant
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.ui.home.adapter.HomePagerAdapter
import com.yjq.eyepetizer.ui.home.mvp.HomeContract
import com.yjq.eyepetizer.ui.home.mvp.HomePresenter
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
    private val TAG = "PageFragment"
    private var nextPaeUrl: String? = null
    private var firstPageUrl: String? = null
    private var mItemList: List<Item>? = null


    //state
    private var enableLoadMore = true   //是否允许加载更多，防止重复申请api导致数据重复


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
        searchError.visibility = View.GONE


        //初始化recycleView
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter

            //底部加载
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                //用来标记是否正在向上滑动
                private var isSlidingUpward = false

                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {

                    //当不滑动时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //获取最后一个完全显示的itemPosition
                        val lastItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                        val itemCount = layoutManager.itemCount

                        // 判断是否滑动到了最后一个item，并且是向上滑动
                        if (lastItemPosition == (itemCount - 1) && isSlidingUpward && enableLoadMore)
                            loadData(nextPaeUrl, loadMore = true)

                    }
                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    isSlidingUpward = dy > 0
                }
            })
        }


        //初始化refreshLayout
        with(refresh) {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.dark))
            setOnRefreshListener {
                mAdapter.setNoMore(false) //重置状态
                loadData(firstPageUrl, loadMore = false)
            }
        }


        //加载首页
        loadData(firstPageUrl, loadMore = false)

    }


    private fun loadData(apiUrl: String?, loadMore: Boolean) {

        if (apiUrl == null) {
            mAdapter.setNoMore(true)  //没有更多
            return
        }
        enableLoadMore = false


        mPresenter.getColumnPage(apiUrl)
                .compose(RxUtil.applySchedulers())  //切换线程
                .compose(bindToLifecycle())         //RxLifeCycle2.X 避免fragment被销毁后仍进行网络数据请求导致内存泄漏等错误
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(t: ColumnPage) {
                        //筛选数据
                        mItemList = t.itemList.filter {
                            Constant.ViewTypeList.contains(ViewTypeEnum.getViewTypeEnum(it.type))
                        }

                        //加载下一页所需API接口
                        nextPaeUrl = t.nextPageUrl

                        //展示数据
                        mAdapter.setData(mItemList as ArrayList<Item>, loadMore)

                    }
                })

    }


    /**
     * ****************************************      RxJava 自定义回调处理    **********************************************
     */


    //网络错误处理
    override fun onNetError() {

        if (mItemList?.size ?: 0 == 0)          //在没有数据的前提下，才会提醒用户，避免网络错误提示遮蔽了原有的列表
            searchError.visibility = View.VISIBLE

        //点击重试
        searchError.setOnClickListener {
            searchError.visibility = View.GONE
            loadData(firstPageUrl, loadMore = false)
        }
    }


    //加载进度条处理
    override fun onLoading(isLoad: Boolean) {
        refresh.isRefreshing = isLoad


        if (!isLoad) {
            //上次页数据加载完毕，才允许进行下一页数据的加载
            enableLoadMore = true
        }
    }

}