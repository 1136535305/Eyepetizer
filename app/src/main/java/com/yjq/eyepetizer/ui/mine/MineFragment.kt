package com.yjq.eyepetizer.ui.mine

import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseFragment

/**
 * 文件： MineFragment
 * 描述：
 * 作者： YangJunQuan   2018-8-21.
 */
class MineFragment : BaseFragment() {
    override fun getLayoutResources(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {

    }

    /**
     * ***************************** 使用  RxBaseObserver 用到的自定义回调  *********************************
     */

    override fun onNetError() {

    }

    override fun onLoading(isLoad: Boolean) {
    }


}
