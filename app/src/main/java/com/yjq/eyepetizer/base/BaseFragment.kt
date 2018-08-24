package com.yjq.eyepetizer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment


/**
 * 文件： BaseFragment
 * 描述： Fragment基类
 * 作者： YangJunQuan   2018-8-6.
 */
abstract class BaseFragment : RxFragment() {

    companion object {
        const val TAG = "BaseFragment"
    }

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //  LogUtil.d(TAG, "onCreateView()")
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResources(), container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // LogUtil.d(TAG, "onViewCreate()")
        initView()
    }

    abstract fun getLayoutResources(): Int

    abstract fun initView()
}