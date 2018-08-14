# Eyepetizer
低仿【开眼】

注意：

①不考虑用户登录情况

②Fidder抓包的开眼APK版本:4.3.381





0.【接口非必须参数】

绝大部分接口都包含以下非必须参数，可省略

参数解析：

udid=435865baacfc49499632ea13c5a78f944c2f28aa          【非必须参数】 游客id，任意值，怀疑只要不登录就可以是任意随机长度的字符串

vc=381                                                 【非必须参数】应用VersionCode。 跟apk版本有关

vn=4.3                                                 【非必须参数】应用VersionName。跟apk版本有关

deviceModel=DUK-AL20                                   【非必须参数】手机信息

first_channel=eyepetizer_360_market                    【非必须参数】???，固定值：eyepetizer_360_market。怀疑跟apk发布渠道有关

last_channel=eyepetizer_360_market                     【非必须参数】???。固定值：eyepetizer_360_market。怀疑跟apk发布渠道有关

system_version_code=26                                 【非必须参数】客户端手机系统版本


1.【热搜关键词】

精简的Url：http://baobab.kaiyanapp.com/api/v3/queries/hot





2.【根据用户输入的关键字进行搜索】

精简Url:      http://baobab.kaiyanapp.com/api/v3/search?query=%E6%B5%8B%E8%AF%95

参数解析：query： 关键词

返回结果：搜索前10项


3.【搜索更多】

精简Url:      http://baobab.kaiyanapp.com/api/v3/search?start=20&num=10&query=%E6%B5%8B%E8%AF%95

返回结果：搜索 第 start~start+num 项 

参数解析：
               query： 关键词
               start  :    搜索开始项
               num :     搜索数目项



4.底部【首页】

① 【获取所有栏目】

精简Url:   http://baobab.kaiyanapp.com/api/v5/index/tab/list


②【推荐】栏目

精简Url:    http://baobab.kaiyanapp.com/api/v5/index/tab/feed

以此类推

【发现】【推荐】【日报】【社区】【广告】【生活】【动画】【搞笑】【开胃】【创意】

【运动】【音乐】【萌宠】【剧情】【科技】【旅行】【影视】【记录】【游戏】【综艺】【时尚】


5.底部【关注】

① 【关注】包含内容列表

精简Url：http://baobab.kaiyanapp.com/api/v5/community/tab/list

注：作为游客，默认关注了【作品】和【动态】

②【作品】页

精简Url:   http://baobab.kaiyanapp.com/api/v6/community/tab/follow


③【动态】页

精简Url:   http://baobab.kaiyanapp.com/api/v6/community/tab/dynamicFeeds


6.底部【通知】

①【通知】包含内容列表

精简Url:  http://baobab.kaiyanapp.com/api/v3/messages/tabList
 
②【官方】页

精简Url:  http://baobab.kaiyanapp.com/api/v3/messages


7.【置頂分类排序】

①列表

精简Url：http://baobab.kaiyanapp.com/api/v5/category/list





