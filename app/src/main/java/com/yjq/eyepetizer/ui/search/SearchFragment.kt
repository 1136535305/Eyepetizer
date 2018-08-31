package com.yjq.eyepetizer.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.*
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
import com.yjq.eyepetizer.ui.search.adapter.SearchHelpAdapter
import com.yjq.eyepetizer.ui.search.mvp.SearchContact
import com.yjq.eyepetizer.ui.search.mvp.SearchPresenter
import com.yjq.eyepetizer.util.log.LogUtil
import com.yjq.eyepetizer.util.rx.RxBaseObserver
import com.yjq.eyepetizer.util.rx.RxUtil
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_help.*

/**
 * 文件： SearchFragment
 * 描述： 搜索Dialog     需求：①键盘弹出时不会让整个视图往上平移 ②实现【搜索历史】 ③实现【搜索关键词】
 * 作者： YangJunQuan   2018-8-24.
 */
class SearchFragment : RxDialogFragment(), SearchContact.View {

    //TAG
    private val TAG = "SearchFragment"


    //data
    private var mItemList: List<Item>? = null
    private var nextPaeUrl: String? = null
    private var mHotWordList: ArrayList<String>? = null


    //state
    private var enableLoadMore = true

    //UI status  根据状态显示不同的界面
    private var state: UIState = UIState.SHOW_SEARCH_HELP

    enum class UIState {
        SHOW_SEARCH_HELP,           //显示搜索帮助列表，包括搜索热词、搜索历史
        SHOW_SEARCH_RESULT,         //显示搜索结果
        SHOW_SEARCH_NET_ERROR,      //显示网络错误提示
        SHOW_SEARCH_EMPTY_RESULT;   //搜索不到相应内容
    }


    //允许展示的列表Item项UI类型
    private var viewTypeList = listOf(
            ViewTypeEnum.TextCard, ViewTypeEnum.BriefCard, ViewTypeEnum.DynamicInfoCard,
            ViewTypeEnum.FollowCard, ViewTypeEnum.VideoSmallCard)


    //other
    private lateinit var mPresenter: SearchPresenter
    private lateinit var mHelpAdapter: SearchHelpAdapter
    private lateinit var mResultAdapter: HomePagerAdapter

    /**
     * **************************************************  界面状态改变的处理 *********************************************
     */


    private fun setUIState(newState: UIState) {
        state = newState

        searchHelp.visibility = View.GONE
        searchError.visibility = View.GONE
        searchEmpty.visibility = View.GONE
        searchResult.visibility = View.GONE

        when (state) {
            UIState.SHOW_SEARCH_HELP -> searchHelp.visibility = View.VISIBLE
            UIState.SHOW_SEARCH_RESULT -> searchResult.visibility = View.VISIBLE
            UIState.SHOW_SEARCH_NET_ERROR -> searchError.visibility = View.VISIBLE
            UIState.SHOW_SEARCH_EMPTY_RESULT -> searchEmpty.visibility = View.VISIBLE
        }
    }

    /**
     * ******************************   DialogFragment 生命周期方法  ****************************************
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = SearchPresenter(context!!)
        mHelpAdapter = SearchHelpAdapter(context!!)
        mResultAdapter = HomePagerAdapter(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //Dialog Window调整
        with(dialog.window) {
            requestFeature(Window.FEATURE_NO_TITLE)                              //去掉Dialog自带的顶部标题栏
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            decorView.setPadding(0, 0, 0, 0)              //去掉padding，让dialog等同于屏幕宽度
            attributes = attributes.apply {
                windowAnimations = R.style.animSearchFragment                    //window进入退出动画效果
                width = WindowManager.LayoutParams.MATCH_PARENT                  //window的宽
                height = WindowManager.LayoutParams.MATCH_PARENT                 //window的高
            }
        }

        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCancelSearch.setOnClickListener { dismiss() }  //退出搜索


        //搜索框初始化
        with(etInput) {
            //触发搜索功能
            setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchByKeyWord(this.text.toString().trim())            //执行搜索
                    return@OnEditorActionListener true
                }
                false
            })

            //控制清空图标的显隐
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                    //根据输入框是否有内容 ->决定是否显示清空图标
                    ivActionCancel.visibility = if (s.isNotEmpty()) View.VISIBLE else View.GONE

                    //一旦输入框没有内容 -> 仅显示搜索帮助列表
                    if (s.isEmpty()) setUIState(UIState.SHOW_SEARCH_HELP)

                }

            })

            //清空图标点击事件
            ivActionCancel.setOnClickListener { etInput.setText("") }

        }


        //搜索结果列表初始化
        with(searchResult) {
            layoutManager = LinearLayoutManager(context)
            adapter = mResultAdapter

            //底部加载更多
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                private var isSlideUpward = false
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val lastItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                        val itemCount = layoutManager.itemCount
                        if (itemCount - 1 == lastItemPosition && isSlideUpward && enableLoadMore) {
                            LogUtil.d(TAG, "load url:$nextPaeUrl")
                            searchMore()
                        }
                    }


                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    isSlideUpward = dy > 0

                }
            })
        }


        //搜索帮助列表初始化 （包括【搜索历史】【搜索热词】 ）
        with(searchHelpRecyclerView) {
            adapter = mHelpAdapter.apply {
                onItemClick = { position ->
                    run {
                        //搜索帮助列表Item项点击事件
                        val hotWord = mHotWordList!![position]
                        etInput.setText(hotWord)
                        etInput.setSelection(hotWord.length)
                        searchByKeyWord(hotWord)
                    }
                }
            }

            layoutManager = LinearLayoutManager(context)
            getSearchKeyHotWord()  //获取搜索热词
        }


        setUIState(UIState.SHOW_SEARCH_HELP)   //一开始显示搜索帮助列表
    }


    /**
     * **********************************************     搜索网络相关   ****************************************************
     */

    //获取搜索热词数据
    private fun getSearchKeyHotWord() {
        mPresenter.getSearchHotWord()
                .compose(bindToLifecycle())
                .compose(RxUtil.applySchedulers())
                .subscribe(object : RxBaseObserver<List<String>>(this) {
                    override fun onNext(t: List<String>) {
                        mHotWordList = t as ArrayList<String>
                        mHelpAdapter.setData(mHotWordList)
                    }
                })
    }


    //根据用户输入搜索相应内容
    private fun searchByKeyWord(query: String) {


        setUIState(UIState.SHOW_SEARCH_RESULT)
        hideKeyBoard(etInput)

        mPresenter.searchByKeyWord(query)
                .compose(RxUtil.applySchedulers())
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(t: ColumnPage) {
                        //筛选数据
                        mItemList = t.itemList.filter {
                            viewTypeList.contains(ViewTypeEnum.getViewTypeEnum(it.type))
                        }

                        //检查搜索结果是否为空
                        if (mItemList!!.isEmpty())
                            setUIState(UIState.SHOW_SEARCH_EMPTY_RESULT)

                        //展示数据
                        mResultAdapter.setData(mItemList as ArrayList<Item>, loadMore = false)

                        //加载下一页所需API接口
                        nextPaeUrl = t.nextPageUrl

                    }
                })
    }


    //在已有的搜索结果上搜索更多
    private fun searchMore() {

        if (nextPaeUrl == null) {
            context?.showToast("没有更多了")
            return
        }

        enableLoadMore = false
        mPresenter.searchMore(nextPaeUrl!!)
                .compose(RxUtil.applySchedulers())
                .subscribe(object : RxBaseObserver<ColumnPage>(this) {
                    override fun onNext(t: ColumnPage) {
                        //筛选数据
                        mItemList = t.itemList.filter {
                            viewTypeList.contains(ViewTypeEnum.getViewTypeEnum(it.type))
                        }

                        //展示数据
                        mResultAdapter.setData(mItemList as ArrayList<Item>, loadMore = false)

                        //加载下一页所需API接口
                        nextPaeUrl = t.nextPageUrl

                    }
                })
    }


    /**
     * ***************************************** 自定义网络数据申请回调  **************************************
     */


    override fun onNetError() {
        setUIState(UIState.SHOW_SEARCH_NET_ERROR)
    }


    override fun showLoading(isLoad: Boolean) {
        if (!isLoad) {
            enableLoadMore = true
        }
    }

}
