package com.yjq.eyepetizer.ui

import android.Manifest
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.base.BaseActivity
import com.yjq.eyepetizer.ui.focus.FocusFragment
import com.yjq.eyepetizer.ui.home.HomeFragment
import com.yjq.eyepetizer.ui.mine.MineFragment
import com.yjq.eyepetizer.ui.notify.NotifyFragment
import com.yjq.eyepetizer.util.log.LogUtil
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 文件： MainActivity
 * 描述：
 * 作者： YangJunQuan   2018-8-6.
 */
class MainActivity : BaseActivity(), View.OnClickListener {


    //static
    companion object {
        const val TAG = "MainActivity"
    }


    //view
    private var homeFragment: HomeFragment? = null
    private var focusFragment: FocusFragment? = null
    private var notifyFragment: NotifyFragment? = null
    private var mineFragment: MineFragment? = null


    /**
     * ******************************************************** 生命周期  ************************************************************
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        setContentView(R.layout.activity_main)

        initFragment()

        initBottomTab()

        initEvent()

        requestPermission()
    }


    //动态申请 【记录崩溃日志】所需的【外部目录读写权限】
    private fun requestPermission() {
        val rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { granted ->
                    if (granted) { // Always true pre-M
                        LogUtil.d(TAG, "write external_storage permission is granted")
                    } else {
                        LogUtil.d(TAG, "write external_storage permission is denied")
                    }
                }
    }

    private fun initEvent() {
        tabHomePage.setOnClickListener(this)
        tabFocus.setOnClickListener(this)
        tabNotify.setOnClickListener(this)
        tabMine.setOnClickListener(this)
    }


    /**
     * ********************************************************** 其它 ****************************************************************
     */


    private fun initBottomTab() {
        tabHomePage.isChecked = true   //默认首页
        tabHomePage.setTextColor(ContextCompat.getColor(this, R.color.dark))

    }


    private fun initFragment() {
        homeFragment = HomeFragment()
        focusFragment = FocusFragment()
        notifyFragment = NotifyFragment()
        mineFragment = MineFragment()


        with(supportFragmentManager.beginTransaction()) {
            add(R.id.fgContainer, homeFragment)
            add(R.id.fgContainer, focusFragment)
            add(R.id.fgContainer, notifyFragment)
            add(R.id.fgContainer, mineFragment)
            commit()
        }


        supportFragmentManager.beginTransaction()
                .show(homeFragment)
                .hide(focusFragment)
                .hide(notifyFragment)
                .hide(mineFragment)
                .commit()
    }


    /**
     * *************************************************  事件处理 ***********************************************************
     */
    override fun onClick(v: View) {
        when (v.id) {

            R.id.tabHomePage -> {
                clearState()
                tabHomePage.setTextColor(ContextCompat.getColor(this, R.color.dark))
                supportFragmentManager.beginTransaction()
                        .show(homeFragment)
                        .hide(focusFragment)
                        .hide(notifyFragment)
                        .hide(mineFragment)
                        .commit()
            }

            R.id.tabFocus -> {
                clearState()
                tabFocus.setTextColor(ContextCompat.getColor(this, R.color.dark))
                supportFragmentManager.beginTransaction()
                        .show(focusFragment)
                        .hide(homeFragment)
                        .hide(notifyFragment)
                        .hide(mineFragment)
                        .commit()
            }

            R.id.tabNotify -> {
                clearState()
                tabNotify.setTextColor(ContextCompat.getColor(this, R.color.dark))
                supportFragmentManager.beginTransaction()
                        .show(notifyFragment)
                        .hide(homeFragment)
                        .hide(focusFragment)
                        .hide(mineFragment)
                        .commit()

            }
            R.id.tabMine -> {
                clearState()
                tabMine.setTextColor(ContextCompat.getColor(this, R.color.dark))
                supportFragmentManager.beginTransaction()
                        .show(mineFragment)
                        .hide(homeFragment)
                        .hide(focusFragment)
                        .hide(notifyFragment)
                        .commit()
            }
        }

    }


    private fun clearState() {
        val unselectedColor = ContextCompat.getColor(this, R.color.gray)
        tabHomePage.setTextColor(unselectedColor)
        tabNotify.setTextColor(unselectedColor)
        tabFocus.setTextColor(unselectedColor)
        tabMine.setTextColor(unselectedColor)
    }
}