# Eyepetizer
低仿【开眼】

# 声明

以下所有 API 均由 __开眼__ 提供，本人采取非正常手段获取。获取与共享之行为或有侵犯 __开眼__ 权益的嫌疑。若被告知需停止共享与使用，本人会及时删除此页面与整个项目。请您暸解相关情况，并遵守相关协议。

# 开眼API 说明

* 不考虑用户登录情况

* Fidder抓包的开眼APK版本:4.3.381

* 由于API接口相当庞大，无法对每一个参数做具体的分析

* 绝大部分接口都包含以下非必须参数，可省略，例：热搜关键词 http://baobab.kaiyanapp.com/api/v3/queries/hot?udid=435865baacfc49499632ea13c5a78f944c2f28aa&vc=381&vn=4.3&deviceModel=DUK-AL20&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=26
    * `udid` : 游客或登录id，任意值，怀疑只要不登录就可以是任意随机长度的字符串
    * `vc` : 应用VersionCode。 跟apk版本有关
    * `vn` : 应用VersionName。跟apk版本有关
    * `deviceModel` : 手机信息
    * `first_channel` : ???。固定值：eyepetizer_360_market。怀疑跟apk发布渠道有关
    * `last_channel`  : ???。固定值：eyepetizer_360_market。怀疑跟apk发布渠道有关
    * `system_version_code` : 客户端手机系统版本


# API 分析

### 1.热搜关键词
* 精简后的Url：http://baobab.kaiyanapp.com/api/v3/queries/hot

* 响应实例:
```
["开眼世界杯", "世界杯", "阅后即瞎", "日食记", "谷阿莫", "励志", "健身", "美食", "一条"]
```

### 2.根据用户输入的关键字进行搜索
* 精简后的Url：http://baobab.kaiyanapp.com/api/v3/search?query=关键字

* 參數分析：
  * `query`:搜索关键词

* 响应实例
```
{
	"itemList": [{
			"type": "briefCard",
			"data": {
				"dataType": "BriefCard",
				"id": 300088048,
				"icon": "http://qzapp.qlogo.cn/qzapp/1104373357/A5BB1D11FE8CC2F22C50EDC4A0578CEC/100",
				"iconType": "round",
				"title": "程序员张",
				"subTitle": null,
				"description": null,
				"actionUrl": "eyepetizer://pgc/detail/300088048/?title=%E7%A8%8B%E5%BA%8F%E5%91%98%E5%BC%A0&userType=NORMAL&tabIndex=0",
				"adTrack": null,
				"follow": {
					"itemType": "user",
					"itemId": 300088048,
					"followed": false
				},
				"ifPgc": false
			},
			"tag": null,
			"id": 0,
			"adIndex": -1
		},
      
      .......
      
   ],
	"count": 15,
	"total": 1,
	"nextPageUrl": "nextPageUrl : http://baobab.kaiyanapp.com/api/v3/search?start=10&num=10&query=%E7%A8%8B%E5%BA%8F%E5%91%98",
	"adExist": false
}
      
      
```

* 结果分析：
   * `count`: 数量，值等于itemList的长度
   * `total`: ???
   * `nextPageUrl`: 加载下一页的Url ，如果返回null，表示没有更多搜索结果了
   * `adExist`: ???
   * `itemList`: 数组类型
      * `tag`: ???
      * `id`:  ???
      * `adIndex`:???
      * `type`:界面UI类型,值可能为</br>
               **textCard**:纯文本UI卡片式布局数据</br>
               **briefCard**：短数据卡片式布局数据</br>
               **followCard**：详情卡片式布局数据,这里只分析 **followCard**类型对应的 `data` 数据结构</br>
      * `data`:数据部分
        * `dataType`: "followCard"，我们这里只分析该种字段的数据结构
        * `header`: 搜索结果某一item项的简要介绍
            * `id`: 
            * `title`: 标题
            * `font`:
            * `subTitle`:副标题
            * `subTitleFont`: 副标题字体
            * `textAlign`:字体对齐格式，一般值为**left**
            * `cover`:
            * `label`: 
            * `actionUrl`: 
            * `labelList`:
            * `icon`: 发布者头像
            * `iconType`: 发布者头像类型，**round**表示圆形头像
            * `description`:分类描述
            * `time`:时间戳
            * `showHateVideo`: 
        * `content`:搜索结果某一item项的详情数据
            * `id`:
            * `adIndex`:
            * `type` : 类型，一般是 **video**
            * `data`:
               * `dataType`
               * `id`:
               * `title`:标题
               * `description`:内容描述
               * `library`:
               * `consumption`:
                  * `collectionCount`:点赞数
                  * `shareCount`:分享数
                  * `replyCount`:评论数
               * `resourceType`:
               * `slogan`:
               * `provider`:视频根本来源
                  * `name`: 来源名称
                  * `alias`:来源别名
                  * `icon`: 来源logo的地址Url
               * `category`:类别
               * `author`: 发布者信息
                  * `id`:发布者Id
                  * `icon`:发布者头像
                  * `name`:发布者名称
                  * `description`:发布者描述
                  * `link`:
                  * `latestReleaseTime`:
                  * `videoNum`:
                  * `adTrack`:
                  * `follow`:
                     * `itemType`:
                     * `itemId`:
                     * `followed`:
                  * `shield`:
                     * `itemType`:
                     * `itemId`:
                     * `shielded`:
                  * `approvedNotReadyVideoCount`:
                  * `ifPgc`:
               * `cover`:封面相关
                  * `feed`:封面Url
                  * `detail`:详情页封面Url
                  * `blurred`:模糊背景图片Url
                  * `sharing`:
                  * `homepage`:
               * `playUrl`:
               * `thumbPlayUrl`:
               * `duration`:视频长度，单位秒
               * `webUrl`:网页版url
                  * `raw`:网页版打开地址
                  * `forWeibo`:微博打开地址
               * `releaseTime`:发布时间
               * `campaign`:
               * `waterMarks`:
               * `adTrack`:
               * `type`:
               * `titlePgc`:
               * `descriptionPgc`:
               * `remark`:
               * `ifLimitVideo`:
               * `searchWeight`:
               * `idx`:
               * `shareAdTrack`:
               * `favoriteAdTrack`:
               * `webAdTrack`:
               * `date`:
               * `promotion`:
               * `label`:
               * `descriptionEditor`:
               * `collected`:是否已收藏
               * `played`:
               * `lastViewTime`:
               * `playlists`:
               * `src`:
               * `tags`:数组类型，该视频所属的分类标签列表
                  * `id`:分类标签id
                  * `name`:分类标签名称
                  * `actionUrl`:
                  * `adTrack`:
                  * `desc`:
                  * `bgPicture`:
                  * `headerImage`:分类标签 代表图片
                  * `tagRecType`:
                  * `childTagList`:
                  * `childTagIdList`:
               * `playInfo`:数组类型
                  * `height`:视频高
                  * `width`:视频宽
                  * `name`:清晰度
                  * `type`:
                  * `url`:默认视频播放来源Url地址
                  * `urlList`:数组类型，播放地址列表
                     * `name`:视频来源，如**aliyun** **qcloud** **ucloud**，**开眼**的视频资源主要托管在**阿里云**、**腾讯云**、**ucloud**
                     * `url`: 视频Url
                     * `size`:视频大小
               * `labelList`:数组类型，暂时没发现数据 ╮(╯▽╰)╭
               * `subtitles`:数组类型，暂时没发现数据 ╮(╯▽╰)╭
   
  



### 3.搜索更多
* 精简后的Url: http://baobab.kaiyanapp.com/api/v3/search?start=20&num=10&query=%E6%B5%8B%E8%AF%95

* 参数分析：
  * `query`:搜索关键词
  * `start`:搜索开始项
  * `num`:  搜索数目
               query：关键词
               start: 搜索开始项
               num: 搜索数目项
               
* 返回结果：搜索 第 start~start+num 项 

* 响应实例：同上


### 4.获取栏目列表
* 精简后的Url: http://baobab.kaiyanapp.com/api/v5/index/tab/list

* 注：目前发现的栏目有
  * 【发现】【推荐】【日报】【社区】【广告】【生活】【动画】【搞笑】【开胃】【创意】
  * 【运动】【音乐】【萌宠】【剧情】【科技】【旅行】【影视】【记录】【游戏】【综艺】【时尚】

* 响应实例：
```
{
"tabInfo": {
	"tabList": [{
			"id": -1,
			"name": "发现",
			"apiUrl": "http://baobab.kaiyanapp.com/api/v5/index/tab/discovery"
		}, {
			"id": -2,
			"name": "推荐",
			"apiUrl": "http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0"
		},
        。。。。
	],
	"defaultIdx": 1
   }
}
```

* 结果分析:
	* `defaulstIdx`:	
	* `tabList`:数组类型，栏目列表
  		* `id`:栏目id
  		* `name`:栏目名称
  		* `apiUrl`:该栏目详情数据ApI接口Url

### 5.栏目详情
* 精简后的Url:  `http://baobab.kaiyanapp.com/api/v5/index/tab/{tabId}?page={page}`

* 参数分析：
  * `tabId`: 【发现】【推荐】【日报】【社区】等栏目使用栏目英文名称，其余栏目使用栏目Id
  * `page`：页数

* 响应实例：
```
{
	"itemList": [{
		"type": "horizontalScrollCard",
		"data": { ...... 
		},
	     ......
	],
	"count": 15,
	"total": 0,
	"nextPageUrl": "http://baobab.kaiyanapp.com/api/v5/index/tab/discovery?start=0",
	"adExist": false
}
```
* 结果分析：
	* `adExiset`:
	* `count`:**itemList**数组长度
	* `total`:
	* `nextPageUrl`: 下一页数据API接口，实际上已不起作用，大部分是首页Url
	* `itemList`:数组类型
		* `type`: 数据类型，不同的**type**对应下面不同的**data**结构,同时对应UI界面列表不同的Item项</br>
		**horizontalScrollCard**:代表横向滚动的列表Item项</br>
		**textCard**:代表简单文本的列表Item项</br>
		**followCard**:</br>
		**videoSmallCard**:代表小型视频的列表Item项</br>
		**briefCard**:</br>
		**squareCardCollection**:</br>
		**videoCollectionWithBrief**:</br>
		**autoPlayFollowCard**:</br>
		......
		* `tag`:
		* `id`:
		* `adIndex`:
		* `data`:篇幅所限，不展开具体分析


### 6.获取用户关注内容列表
* 精简后的Url: http://baobab.kaiyanapp.com/api/v5/community/tab/list

* 响应实例：
```
{

}
```

### 7.关注内容详情
* 精简后的Url: http://baobab.kaiyanapp.com/api/v5/community/tab/{tabName}

* 参数分析:
  * `tabName`: 关注内容
  
* 注：作为游客，默认关注了【作品】和【动态】

* 例：【作品】：http://baobab.kaiyanapp.com/api/v6/community/tab/follow

* 响应实例：
```
{

}
```

### 8.获取通知列表 
* 精简后的Url： http://baobab.kaiyanapp.com/api/v3/messages/tabList

* 响应实例：
```
{

}
```

### 9.通知列表某一項詳情
* 精简后的Url: http://baobab.kaiyanapp.com/api/v3/{tabName}

* 参数分析：
  * `tabName`：通知列表某一项
  
* 例：【官方】 http://baobab.kaiyanapp.com/api/v3/messages

* 响应实例：
```
{

}
```

### 10.置頂分类列表
* 精简后的Url：http://baobab.kaiyanapp.com/api/v5/category/list

* 响应实例：
```
{

}
```





