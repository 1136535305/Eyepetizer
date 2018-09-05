package com.yjq.eyepetizer.ui.focus

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.bean.notify.Message
import com.yjq.eyepetizer.bean.notify.MessageInfo
import com.yjq.eyepetizer.constant.ResponseCode
import com.yjq.eyepetizer.ui.focus.mvp.FocusContract
import com.yjq.eyepetizer.ui.focus.mvp.FocusPresenter
import com.yjq.eyepetizer.ui.focus.mvp.NotifyPresenter
import com.yjq.eyepetizer.ui.home.adapter.HomePagerAdapter
import com.yjq.eyepetizer.ui.notify.adapter.NotifyTabAdapter
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


    //data
    private var messageList = ArrayList<Item>()

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

        initData()
    }

    private fun initData() {
        val apiUrl = arguments!!.getString("API_URL")
        mPresenter.getFocusTabInfo(apiUrl)
                .compose(RxUtil.applySchedulers())
                .compose(bindToLifecycle())
                .subscribe(object : RxBaseObserver<List<Item>>(this) {
                    override fun onNext(t: List<Item>) {

                        messageList = t as ArrayList<Item>
                        mAdapter.setData(messageList, false)
                    }
                })
    }


    private fun initRecyclerView() {
        with(messageRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

    }


    private fun onNeedLogin() {
        noAccountView.visibility = View.VISIBLE
    }


    /**
     * ****************************************      RxJava 自定义回调处理    **********************************************
     */


    override fun onNetError() {

    }

    override fun onLoading(isLoad: Boolean) {

    }


}