package com.yjq.eyepetizer.ui.home

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.bean.Columns
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.ui.home.mvp.HomeContract
import com.yjq.eyepetizer.ui.home.mvp.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 文件： HomeFragment
 * 描述： 【首页】
 * 作者： YangJunQuan   2018-8-16.
 */
class HomeFragment : BaseFragment(), HomeContract.View {

    //data
    private lateinit var mData: Columns


    //state


    //other
    private lateinit var mPresenter: HomePresenter

    override fun getLayoutResources(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

        mPresenter = HomePresenter(context!!, this)

        initData()
    }


    private fun initData() {
        mPresenter.getHomeColumns()
                .compose((activity as RxAppCompatActivity).bindToLifecycle())
                .subscribe {
                    mData = it
                    initTabLayout()
                }
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
}
