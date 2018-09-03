package com.yjq.eyepetizer.ui.notify

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.base.BaseView
import com.yjq.eyepetizer.bean.cards.Columns
import com.yjq.eyepetizer.ui.focus.mvp.NotifyPresenter
import com.yjq.eyepetizer.ui.search.SearchFragment
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
import kotlinx.android.synthetic.main.fragment_notify.*

/**
 * 文件： NotifyFragment
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */
class NotifyFragment : BaseFragment() {


    //data
    private var tabInfo: Columns? = null


    //other
    private lateinit var mPresenter: NotifyPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = NotifyPresenter(context!!)
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_notify
    }

    override fun initView() {
        initData()

        initToolbar()

    }

    private fun initData() {
        mPresenter.getNotifyTabList()
                .compose(RxUtil.applySchedulers())
                .compose(bindToLifecycle())
                .subscribe(object : RxBaseObserver<Columns>(this) {
                    override fun onNext(t: Columns) {
                        tabInfo = t
                        initViewPager()
                        initTabLayout()
                    }
                })
    }

    private fun initToolbar() {
        tvTitle.typeface = Typeface.createFromAsset(context!!.assets, "fonts/Lobster-1.4.otf")
        ivSearch.setOnClickListener { SearchFragment().show(fragmentManager, "SearchFragment") }
    }


    private fun initViewPager() {
        with(viewPagerNotify) {
            adapter = object : FragmentPagerAdapter(childFragmentManager) {
                override fun getItem(position: Int): Fragment {
                    val apiUrl = tabInfo!!.tabInfo.tabList[1].apiUrl
                    return NotifyTabFragment.newInstance(apiUrl)
                }

                override fun getCount(): Int {
                    return tabInfo?.tabInfo?.tabList?.size ?: 0
                }


                override fun getPageTitle(position: Int): CharSequence? {
                    return tabInfo!!.tabInfo.tabList[position].name
                }

            }
        }

    }


    private fun initTabLayout() {
        with(tabNotify) {
            setSelectedTabIndicatorHeight(0)
            tabGravity = TabLayout.GRAVITY_CENTER
            tabMode = TabLayout.MODE_FIXED
            setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.dark))
            setupWithViewPager(viewPagerNotify)

        }
    }

    /**
     * ****************************************      RxJava 自定义回调处理    **********************************************
     */


    override fun onNetError() {

    }

    override fun showLoading(isLoad: Boolean) {

    }

}
