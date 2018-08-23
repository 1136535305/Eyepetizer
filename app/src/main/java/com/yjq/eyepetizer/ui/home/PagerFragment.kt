package com.yjq.eyepetizer.ui.home

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.bean.ColumnPage
import com.yjq.eyepetizer.bean.Item
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.ui.home.adapter.HomePagerAdapter
import com.yjq.eyepetizer.ui.home.mvp.HomeContract
import com.yjq.eyepetizer.ui.home.mvp.HomePresenter
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import io.reactivex.disposables.Disposable
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
    private var viewTypeList = listOf(ViewTypeEnum.TextCard, ViewTypeEnum.FollowCard, ViewTypeEnum.VideoSmallCard)       //允许展示的列表Item项UI类型

    override fun getLayoutResources(): Int {
        return R.layout.fragment_home_pager
    }

    override fun initView() {
        mAdapter = HomePagerAdapter(context!!)
        mPresenter = HomePresenter(context!!, this)


        //初始化recycleView
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }


        //初始化refreshLayout
        with(refresh) {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.blueLight))
            isEnabled = false
        }

        initData()


    }


    private fun initData() {
        val apiUrl = arguments?.getString("API_URL")
        mPresenter.getColumnPage(apiUrl!!)
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(columnPage: ColumnPage) {

                        //筛选数据
                        mItemList = columnPage.itemList.filter {
                            viewTypeList.contains(ViewTypeEnum.getViewTypeEnum(it.type))
                        }

                        //展示数据
                        mAdapter.mDataList = mItemList as ArrayList<Item>
                        mAdapter.notifyDataSetChanged()

                    }
                })


    }

    /**
     * ****************************************      自定义回调处理    **********************************************
     */


    //网络错误处理
    override fun onNetError() {
        netError.visibility = View.VISIBLE

        //点击重试
        netError.setOnClickListener {
            netError.visibility = View.GONE
            initData()
        }
    }


    //加载进度条处理
    override fun showLoading(isLoad: Boolean) {
        refresh.isRefreshing = isLoad
    }

}