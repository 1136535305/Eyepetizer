package com.yjq.eyepetizer.ui.focus

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.ui.focus.mvp.FocusPresenter
import com.yjq.eyepetizer.ui.home.adapter.HomePagerAdapter
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
import kotlinx.android.synthetic.main.tab_notify.*

/**
 * 文件： NotifyTabFragment
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */
class FocusTabFragment : BaseFragment() {


    //static
    companion object {
        fun newInstance(apiUrl: String) = FocusTabFragment().apply { arguments = Bundle().apply { putString("API_URL", apiUrl) } }
    }

    //state
    private var enableLoadMore = true

    //data
    private var messageList = ArrayList<Item>()
    private lateinit var firstPageUrl: String
    private var nextPageUrl: String? = null

    //other
    private lateinit var mPresenter: FocusPresenter
    private lateinit var mAdapter: HomePagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = FocusPresenter(context!!)
        mAdapter = HomePagerAdapter(context!!)
    }

    override fun getLayoutResources(): Int {
        return R.layout.tab_notify
    }

    override fun initView() {
        initRecyclerView()

        firstPageUrl = arguments!!.getString("API_URL")

        loadData(firstPageUrl, false)
    }

    private fun loadData(apiUrl: String?, ifLoadMore: Boolean) {

        if (apiUrl == null) {
            mAdapter.setNoMore(true)
            return
        }

        enableLoadMore = false

        mPresenter.getFocusTabInfo(apiUrl)
                .compose(RxUtil.applySchedulers())
                .compose(bindToLifecycle())
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(t: ColumnPage) {
                        messageList = t.itemList as ArrayList<Item>
                        mAdapter.setData(messageList, ifLoadMore)

                        nextPageUrl = t.nextPageUrl
                    }
                })
    }


    private fun initRecyclerView() {
        with(messageRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter

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
                            loadData(nextPageUrl, ifLoadMore = true)

                    }
                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    isSlidingUpward = dy > 0
                }
            })
        }

    }

    /**
     * ****************************************      RxJava 自定义回调处理    **********************************************
     */

    override fun onNetError() {

    }

    override fun onLoading(isLoad: Boolean) {

        if (!isLoad) {
            //上次页数据加载完毕，才允许进行下一页数据的加载
            enableLoadMore = true
        }
    }


}