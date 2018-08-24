package com.yjq.eyepetizer.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import com.yjq.eyepetizer.R
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * 文件： SearchFragment
 * 描述：
 * 作者： YangJunQuan   2018-8-24.
 */
class SearchFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setLayout()
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCancelSearch.setOnClickListener { dismiss() }  //退出搜索
    }

    private fun setLayout() {
        with(dialog.window) {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            decorView.setPadding(0, 0, 0, 0)  //去掉padding，让dialog等同于屏幕宽度
            attributes = attributes.apply {
                windowAnimations = R.style.animSearchFragment
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
                gravity = Gravity.BOTTOM
            }
        }

    }

}