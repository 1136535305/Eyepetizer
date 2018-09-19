package com.yjq.eyepetizer.ui.notify

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment
import com.yjq.eyepetizer.bean.notify.Message
import com.yjq.eyepetizer.bean.notify.MessageInfo
import com.yjq.middleware.IM.ResponseCode
import com.yjq.eyepetizer.ui.focus.mvp.NotifyPresenter
import com.yjq.eyepetizer.ui.notify.adapter.NotifyTabAdapter
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
import kotlinx.android.synthetic.main.tab_notify.*

/**
 * 文件： NotifyTabFragment
 * 描述：
 * 作者： YangJunQuan   2018-9-3.
 */
class NotifyTabFragment : BaseFragment() {


    //static
    companion object {
        fun newInstance(apiUrl: String) = NotifyTabFragment().apply { arguments = Bundle().apply { putString("API_URL", apiUrl) } }
    }


    //data
    private var messageList = mutableListOf<Message>()


    //other
    private lateinit var mPresenter: NotifyPresenter
    private lateinit var mAdapter: NotifyTabAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = NotifyPresenter(context!!)
        mAdapter = NotifyTabAdapter(context!!)
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
        mPresenter.getNotifyTabInfo(apiUrl)
                .compose(RxUtil.applySchedulers())
                .compose(bindToLifecycle())
                .subscribe(object : RxBaseObserver<MessageInfo>(this) {
                    override fun onNext(t: MessageInfo) {
                        if (ResponseCode.RES_ERROR_NOT_LOGIN == t.errorCode) {
                            onNeedLogin()
                            return
                        }
                        messageList = t.messageList as MutableList<Message>
                        mAdapter.setData(messageList)
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