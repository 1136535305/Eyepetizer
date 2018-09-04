package com.yjq.eyepetizer.ui.home

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.view.View
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.bean.cards.Columns
import com.yjq.eyepetizer.ui.home.mvp.HomeContract
import com.yjq.eyepetizer.ui.home.mvp.HomePresenter
import com.yjq.eyepetizer.ui.search.SearchFragment
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 文件： HomeFragment
 * 描述： 【首页】
 * 作者： YangJunQuan   2018-8-16.
 */
class HomeFragment : BaseFragment(), HomeContract.View {


    //data
    private lateinit var mData: Columns


    //other
    private lateinit var mPresenter: HomePresenter


    override fun getLayoutResources(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

        mPresenter = HomePresenter(context!!, this)

        initData()

        initEvent()
    }

    private fun initEvent() {
        ivSearch.setOnClickListener { SearchFragment().show(childFragmentManager, "searchFragment") }
    }

    private fun initData() {
        mPresenter.getHomeColumns()
                .compose(bindToLifecycle())
                .compose(RxUtil.applySchedulers())
                .subscribe(object : RxBaseObserver<Columns>(this) {
                    override fun onNext(t: Columns) {
                        mData = t
                        initTabLayout()
                    }
                })
    }


    private fun initTabLayout() {

        //viewPager 初始化
        with(viewPager) {
            adapter = object : FragmentPagerAdapter(childFragmentManager) {
                override fun getItem(position: Int): Fragment {
                    val apiUrl = mData.tabInfo.tabList[position].apiUrl
                    return PagerFragment.newInstance(apiUrl)
                }

                override fun getCount(): Int {
                    return mData.tabInfo.tabList.size
                }

                override fun getPageTitle(position: Int): CharSequence? {
                    return mData.tabInfo.tabList[position].name
                }
            }
        }


        //tabLayout 初始化
        with(tabLayout) {
            tabMode = TabLayout.MODE_SCROLLABLE
            setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.dark))
            setupWithViewPager(viewPager)
        }


    }


    //网络错误处理
    override fun onNetError() {
        searchError.visibility = View.VISIBLE

        //点击重试
        searchError.setOnClickListener {
            searchError.visibility = View.GONE
            initData()
        }
    }


    //加载进度条处理
    override fun onLoading(isLoad: Boolean) {

    }
}
