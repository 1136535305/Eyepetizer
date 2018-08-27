package com.yjq.eyepetizer.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.trello.rxlifecycle2.components.support.RxDialogFragment
import com.yjq.eyepetizer.R
import com.yjq.eyepetizer.bean.cards.ColumnPage
import com.yjq.eyepetizer.bean.cards.Item
import com.yjq.eyepetizer.constant.ViewTypeEnum
import com.yjq.eyepetizer.hideKeyBoard
import com.yjq.eyepetizer.showToast
import com.yjq.eyepetizer.ui.home.adapter.HomePagerAdapter
import com.yjq.eyepetizer.ui.search.mvp.SearchContact
import com.yjq.eyepetizer.ui.search.mvp.SearchPresenter
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * 文件： SearchFragment
 * 描述： 搜索Dialog     需求：①键盘弹出时不会让整个视图往上平移 ②实现【搜索历史】 ③实现【搜索关键词】
 * 作者： YangJunQuan   2018-8-24.
 */
class SearchFragment : RxDialogFragment(), SearchContact.View {


    //data
    private var mHotWordList = ArrayList<String>()
    private var mItemList: List<Item>? = null
    private var lastCompleteVisiblePosition = -1
    private var nextPaeUrl: String? = null


    //允许展示的列表Item项UI类型
    private var viewTypeList = listOf(
            ViewTypeEnum.TextCard, ViewTypeEnum.BriefCard, ViewTypeEnum.DynamicInfoCard,
            ViewTypeEnum.FollowCard, ViewTypeEnum.VideoSmallCard)


    //other
    private lateinit var mPresenter: SearchPresenter
    private lateinit var mAdapter: HomePagerAdapter

    /**
     * ******************************   DialogFragment 声明周期方法  ****************************************
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = SearchPresenter(context!!)
        mAdapter = HomePagerAdapter(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //Dialog Window调整
        with(dialog.window) {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            decorView.setPadding(0, 0, 0, 0)  //去掉padding，让dialog等同于屏幕宽度
            attributes = attributes.apply {
                windowAnimations = R.style.animSearchFragment        //window进入退出动画效果
                width = WindowManager.LayoutParams.MATCH_PARENT      //window的宽
                height = WindowManager.LayoutParams.MATCH_PARENT     //window的高
            }
        }
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCancelSearch.setOnClickListener { dismiss() }  //退出搜索


        //搜索框初始化
        with(etInput) {
            setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchByKeyWord()
                    hideKeyBoard(this)
                    return@OnEditorActionListener true
                }
                false
            })

            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    ivActionCancel.visibility = if (s.isNotEmpty()) View.VISIBLE else View.GONE
                }

            })

            ivActionCancel.setOnClickListener { etInput.setText("") }  //清空图标
        }


        //搜索结果列表初始化
        with(searchRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter

            //底部加载
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    val totalCount = layoutManager.itemCount - 1
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && totalCount == lastCompleteVisiblePosition) {
                        searchMore()
                    }

                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    with(layoutManager as LinearLayoutManager) {
                        lastCompleteVisiblePosition = findLastCompletelyVisibleItemPosition()       //记录底部可见的位置
                    }

                }
            })
        }


        //获取搜索热词
        //getSearchKeyHotWord()
    }


    //获取搜索热词数据
    private fun getSearchKeyHotWord() {
        mPresenter.getSearchHotWord()
                .compose(bindToLifecycle())
                .compose(RxUtil.applySchedulers())
                .subscribe(object : RxBaseObserver<List<String>>(this) {
                    override fun onNext(t: List<String>) {
                        mHotWordList = t as ArrayList<String>
                        context?.showToast(mHotWordList.toString())
                    }
                })
    }


    //根据用户输入搜索相应内容
    private fun searchByKeyWord() {
        val query = etInput.text.toString().trim()
        mPresenter.searchByKeyWord(query)
                .compose(RxUtil.applySchedulers())
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(t: ColumnPage) {
                        //筛选数据
                        mItemList = t.itemList.filter {
                            viewTypeList.contains(ViewTypeEnum.getViewTypeEnum(it.type))
                        }

                        //检查搜索结果是否为空
                        checkIsEmpty()

                        //展示数据
                        mAdapter.setData(mItemList as ArrayList<Item>, loadMore = false)

                        //加载下一页所需API接口
                        nextPaeUrl = t.nextPageUrl

                    }
                })
    }

    private fun searchMore() {

        if (nextPaeUrl == null) {
            context?.showToast("没有更多了")
            return
        }

        mPresenter.searchMore(nextPaeUrl!!)
                .compose(RxUtil.applySchedulers())
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(t: ColumnPage) {
                        //筛选数据
                        mItemList = t.itemList.filter {
                            viewTypeList.contains(ViewTypeEnum.getViewTypeEnum(it.type))
                        }

                        //展示数据
                        mAdapter.setData(mItemList as ArrayList<Item>, loadMore = false)

                        //加载下一页所需API接口
                        nextPaeUrl = t.nextPageUrl

                    }
                })
    }


    private fun checkIsEmpty() {
        resultEmpty.visibility = if (mItemList!!.isNotEmpty()) View.GONE else View.VISIBLE
    }


    /**
     * ***************************************** 自定义网络数据申请回调  **************************************
     */


    override fun onNetError() {
        netError.visibility = View.VISIBLE

        //点击重试
        netError.setOnClickListener {
            netError.visibility = View.GONE
            getSearchKeyHotWord()
        }
    }

    override fun showLoading(isLoad: Boolean) {


    }

}
