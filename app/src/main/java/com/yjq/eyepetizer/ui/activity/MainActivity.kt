package com.yjq.eyepetizer.ui.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.view.View
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.showToast
import com.yjq.eyepetizer.ui.fragment.DiscoverFragment
import com.yjq.eyepetizer.ui.fragment.HomeFragment
import com.yjq.eyepetizer.ui.fragment.HotFragment
import com.yjq.eyepetizer.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * 文件： MainActivity
 * 描述：
 * 作者： YangJunQuan   2018-8-6.
 */
class MainActivity : BaseActivity(), View.OnClickListener {


    //static
    companion object {
        val TAG = "MainActivity"
    }


    //view
    private var homeFragment: HomeFragment? = null
    private var discoverFragment: DiscoverFragment? = null
    private var hotFragment: HotFragment? = null
    private var mineFragment: MineFragment? = null


    //state
    private var mExitTime: Long = 0

    /**
     * ******************************************************** 生命周期  ************************************************************
     */


    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initBottomTab()
        initFragment()
    }

    /**
     * ********************************************************** 其它 ****************************************************************
     */


    private fun initToolbar() {
        tvBarTitle.text = getToday()
        tvBarTitle.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")

        //点击搜索图标
        ivSearch.setOnClickListener {
            //TODO 主页搜索功能
        }

    }


    private fun initBottomTab() {
        tabHomePage.isChecked = true   //默认首页
        tabHomePage.setTextColor(ContextCompat.getColor(this, R.color.dark))
        tabHomePage.setOnClickListener(this)
        tabDiscover.setOnClickListener(this)
        tabHot.setOnClickListener(this)
        tabMine.setOnClickListener(this)
    }


    private fun initFragment() {
        homeFragment = HomeFragment()
        discoverFragment = DiscoverFragment()
        hotFragment = HotFragment()
        mineFragment = MineFragment()

        with(supportFragmentManager.beginTransaction()) {
            add(R.id.fragmentContainer, homeFragment)
            add(R.id.fragmentContainer, discoverFragment)
            add(R.id.fragmentContainer, hotFragment)
            add(R.id.fragmentContainer, mineFragment)
            commit()
        }


        //显示首页
        supportFragmentManager.beginTransaction()
                .show(homeFragment)
                .hide(discoverFragment)
                .hide(hotFragment)
                .hide(mineFragment)
                .commit()
    }


    private fun getToday(): String {
        val list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val calendar = Calendar.getInstance().apply { time = Date() }

        var index = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0) index = 0

        return list[index]
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 3000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast(getString(R.string.exitTip))
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    /**
     * *************************************************  事件处理 ***********************************************************
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tabHomePage -> {

            }
            R.id.tabDiscover -> {

            }
            R.id.tabHot -> {

            }
            R.id.tabMine -> {

            }
        }

    }
}