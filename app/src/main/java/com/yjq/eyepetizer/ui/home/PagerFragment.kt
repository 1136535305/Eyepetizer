package com.yjq.eyepetizer.ui.home

import android.os.Bundle
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.ui.home.adapter.HomePagerAdapter
import com.yjq.eyepetizer.ui.home.mvp.HomeContract
import com.yjq.eyepetizer.ui.home.mvp.HomePresenter

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

    //view
    private lateinit var mAdapter: HomePagerAdapter


    //presenter
    private lateinit var mPresenter: HomePresenter


    override fun getLayoutResources(): Int {
        return R.layout.fragment_home_pager
    }

    override fun initView() {
        mAdapter = HomePagerAdapter(context!!)
        mPresenter = HomePresenter(context!!, this)
        initData()
    }


    private fun initData() {
        val apiUrl = arguments?.getString("API_URL")
        mPresenter.getColumnPage(apiUrl!!)
                .subscribe {

                }

    }
}