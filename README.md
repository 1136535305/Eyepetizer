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
	"itemList": [ {
		"type": "followCard",
		"data": {
			"dataType": "FollowCard",
			"header": {
				"id": 4296,
				"title": "悦食中国",
				"font": null,
				"subTitle": null,
				"subTitleFont": null,
				"textAlign": "left",
				"cover": null,
				"label": null,
				"actionUrl": "eyepetizer://pgc/detail/58/?title=æ¦é£ä¸­å½&userType=PGC&tabIndex=1",
				"labelList": null,
				"icon": "http://img.kaiyanapp.com/2bf6dc5063412267179ac8e6b69ad9df.jpeg?imageMogr2/quality/60",
				"iconType": "round",
				"description": "#开胃 / 收录于 每日编辑精选",
				"time": 1453392000000,
				"showHateVideo": false
			},
			"content": {
				"type": "video",
				"data": {
					"dataType": "VideoBeanForClient",
					"id": 4296,
					"title": "一碗肠旺面的 21 个关键字",
					"description": "在贵阳，清晨面馆刚刚开门营业。人们进去呼噜连汤吃完一碗肠旺面，便是一整天的开始。21 个关键字，带你到贵阳吃一碗完美的肠旺面。From @悦食中国",
					"library": "DAILY",
					"tags": [{
						"id": 20,
						"name": "开胃",
						"actionUrl": "eyepetizer://tag/20/?title=å¼è",
						"adTrack": null,
						"desc": null,
						"bgPicture": "http://img.kaiyanapp.com/afffaebe827656b0bb24e534ab35275c.jpeg?imageMogr2/quality/100",
						"headerImage": "http://img.kaiyanapp.com/afffaebe827656b0bb24e534ab35275c.jpeg?imageMogr2/quality/100",
						"tagRecType": "IMPORTANT",
						"childTagList": null,
						"childTagIdList": null
					}, {
						"id": 242,
						"name": "中餐",
						"actionUrl": "eyepetizer://tag/242/?title=ä¸­é¤",
						"adTrack": null,
						"desc": null,
						"bgPicture": "http://img.kaiyanapp.com/723fdc19732443cf1d3fe071dc50b13d.jpeg?imageMogr2/quality/100",
						"headerImage": "http://img.kaiyanapp.com/ae67ca0d35f365547e4167c5b8e9f24e.jpeg?imageMogr2/quality/100",
						"tagRecType": "NORMAL",
						"childTagList": null,
						"childTagIdList": null
					}],
					"consumption": {
						"collectionCount": 2831,
						"shareCount": 3190,
						"replyCount": 69
					},
					"resourceType": "video",
					"slogan": null,
					"provider": {
						"name": "PGC",
						"alias": "PGC",
						"icon": ""
					},
					"category": "开胃",
					"author": {
						"id": 58,
						"icon": "http://img.kaiyanapp.com/2bf6dc5063412267179ac8e6b69ad9df.jpeg?imageMogr2/quality/60",
						"name": "悦食中国",
						"description": "著名生活美食杂志，集结中国所有热爱美食、有志于生活趣味的朋友， 打造专属于你的人与食物的美好关系。",
						"link": "",
						"latestReleaseTime": 1496313157000,
						"videoNum": 4,
						"adTrack": null,
						"follow": {
							"itemType": "author",
							"itemId": 58,
							"followed": false
						},
						"shield": {
							"itemType": "author",
							"itemId": 58,
							"shielded": false
						},
						"approvedNotReadyVideoCount": 0,
						"ifPgc": true
					},
					"cover": {
						"feed": "http://img.kaiyanapp.com/e320711752a3fa8a924327f03e1d21d7.jpeg?imageMogr2/quality/100",
						"detail": "http://img.kaiyanapp.com/e320711752a3fa8a924327f03e1d21d7.jpeg?imageMogr2/quality/100",
						"blurred": "http://img.kaiyanapp.com/2161d401aa4b0ccc053243fd7175aeb5.jpeg?imageMogr2/quality/100",
						"sharing": null,
						"homepage": null
					},
					"playUrl": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=default&source=aliyun",
					"thumbPlayUrl": null,
					"duration": 98,
					"webUrl": {
						"raw": "http://www.eyepetizer.net/detail.html?vid=4296",
						"forWeibo": "http://wandou.im/135gbg"
					},
					"releaseTime": 1453392000000,
					"playInfo": [{
						"height": 480,
						"width": 848,
						"urlList": [{
							"name": "aliyun",
							"url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=normal&source=aliyun",
							"size": 19261424
						}, {
							"name": "qcloud",
							"url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=normal&source=qcloud",
							"size": 19261424
						}, {
							"name": "ucloud",
							"url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=normal&source=ucloud",
							"size": 19261424
						}],
						"name": "标清",
						"type": "normal",
						"url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=normal&source=aliyun"
					}, {
						"height": 720,
						"width": 1280,
						"urlList": [{
							"name": "aliyun",
							"url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=high&source=aliyun",
							"size": 24165318
						}, {
							"name": "qcloud",
							"url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=high&source=qcloud",
							"size": 24165318
						}, {
							"name": "ucloud",
							"url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=high&source=ucloud",
							"size": 24165318
						}],
						"name": "高清",
						"type": "high",
						"url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=4296&resourceType=video&editionType=high&source=aliyun"
					}],
					"campaign": null,
					"waterMarks": null,
					"adTrack": null,
					"type": "NORMAL",
					"titlePgc": null,
					"descriptionPgc": null,
					"remark": "",
					"ifLimitVideo": false,
					"searchWeight": 0,
					"idx": 0,
					"shareAdTrack": null,
					"favoriteAdTrack": null,
					"webAdTrack": null,
					"date": 1453392000000,
					"promotion": null,
					"label": null,
					"labelList": [],
					"descriptionEditor": "在贵阳，清晨面馆刚刚开门营业。人们进去呼噜连汤吃完一碗肠旺面，便是一整天的开始。21 个关键字，带你到贵阳吃一碗完美的肠旺面。From @悦食中国",
					"collected": false,
					"played": false,
					"subtitles": [],
					"lastViewTime": null,
					"playlists": null,
					"src": null
				},
				"tag": null,
				"id": 0,
				"adIndex": -1
			},
			"adTrack": null
		},
		"tag": null,
		"id": 0,
		"adIndex": -1
	}
	......			 
			      ],
	"count": 13,
	"total": 1,
	"nextPageUrl": "http://baobab.kaiyanapp.com/api/v3/search?start=10&num=10&query=å³é®å­",
	"adExist": false
}  
      
```

* 结果分析：
   * `count`: 数量，值等于`itemLis`的长度
   * `total`: 
   * `nextPageUrl`: 加载下一页的Url ，如果返回null，表示没有更多搜索结果了
   * `adExist`: 
   * `itemList`: 数组类型
      * `tag`: 
      * `id`:  
      * `adIndex`:
      * `type`:界面UI类型,值可能为：**textCard**、**briefCard**、**followCard**....等</br>
               **textCard**:纯文本UI卡片式布局数据</br>
               **briefCard**：短数据卡片式布局数据</br>
               **followCard**：详情卡片式布局数据,这里只分析 **followCard**类型对应的 `data` 数据结构</br>
      * `data`:数据部分
        * `dataType`: **followCard**，我们这里只分析该种字段的数据结构
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
                     * `name`:视频来源:**aliyun**、**qcloud**、**ucloud**，**开眼**的视频资源主要托管在该3处云服务器
                     * `url`: 视频Url
                     * `size`:视频大小
               * `labelList`:数组类型，暂未发现数据
               * `subtitles`:数组类型，暂未发现数据 
   
  



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
        ......
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
	* `count`:`itemList`数组长度
	* `total`:
	* `nextPageUrl`: 下一页数据API接口，实际上已不起作用，大部分是首页Url
	* `itemList`:数组类型
		* `type`: 数据类型，不同的`type`对应下面不同的`data`结构,同时对应UI界面列表不同的Item项</br>
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


### <text id="tag6">6.获取用户关注内容列表</text>
* 精简后的Url: http://baobab.kaiyanapp.com/api/v5/community/tab/list

* 注：这里只关注游客默认的关注列表，故省略参数 `udid`

* 响应实例：分析可知，游客默认只关注**作品**和**动态**
```
{
	"tabInfo": {
		"tabList": [{
			"id": 0,
			"name": "作品",
			"apiUrl": "http://baobab.kaiyanapp.com/api/v5/community/tab/follow"
		}, {
			"id": 1,
			"name": "动态",
			"apiUrl": "http://baobab.kaiyanapp.com/api/v5/community/tab/dynamicFeeds"
		}],
		"defaultIdx": 0
	}
}
```

* 结果分析：
	* `defaultIdx`:
	* `tabList`:数组类型
		* `id`:关注项Id
		* `name`:关注项名称
		* `apiUrl`:关注项详情对应API接口

### 7.关注内容详情
* 精简后的Url: http://baobab.kaiyanapp.com/api/v5/community/tab/{tabName}

* 参数分析:
  * `tabName`: 关注内容
  
* 注：作为游客，默认关注了【作品】和【动态】

* 例：【动态】：http://baobab.kaiyanapp.com/api/v6/community/tab/dynamicFeeds

* 响应实例：
```
{
	"itemList": [{
		"type": "DynamicInfoCard",
		"data": {
			"dataType": "DynamicReplyCard",
			"dynamicType": "reply",
			"text": "评论:",
			"actionUrl": "eyepetizer://replies/video?videoId=121828&top=1029897909421735936&videoTitle=ä¸å¬å°±å¼å¿ï¼å½©è¹åå±å¢æ°æ­ãè¥å®ç¾¤ä¾ ä¼ ã ",
			"user": {
				"uid": 300944793,
				"nickname": "Demo",
				"avatar": "http://img.wdjimg.com/image/account/ba0422c380e1537b8d7ba727f60a8b57_180_180.png",
				"userType": "NORMAL",
				"ifPgc": false,
				"description": null,
				"area": null,
				"gender": null,
				"registDate": 1502274552000,
				"releaseDate": null,
				"cover": null,
				"actionUrl": "eyepetizer://pgc/detail/300944793/?title=Demo&userType=NORMAL&tabIndex=0",
				"followed": false,
				"limitVideoOpen": false,
				"library": "BLOCK",
				"uploadStatus": "NORMAL",
				"bannedDate": null,
				"bannedDays": 0
			},
			"mergeNickName": null,
			"mergeSubTitle": null,
			"merge": false,
			"createDate": 1534381771000,
			"simpleVideo": {
				"id": 121828,
				"resourceType": "video",
				"uid": 0,
				"title": "一听就开心！彩虹合唱团新歌「肥宅群侠传」 ",
				"description": "彩虹合唱团最新 EP「夏日安可」的主打歌「肥宅群侠传」，是根据词曲作者金承志本人的生活经历改编的反战歌曲。因为减肥的问题，引发了家庭内部的战争，父亲愤然离家出走，母亲血亏诊疗费，儿子被送入强制戒胖中心。最后，爱呼唤了和平，母亲带儿子出院，买了三杯九分甜去冰加芋圆仙草冰淇淋的奶茶，一人一杯，修复了家庭关系。很快，大家都明白了，原来胖着也挺好的。",
				"cover": {
					"feed": "http://img.kaiyanapp.com/ecf1b57fe609518616a842c1232cf7cf.jpeg?imageMogr2/quality/60/format/jpg",
					"detail": "http://img.kaiyanapp.com/ecf1b57fe609518616a842c1232cf7cf.jpeg?imageMogr2/quality/60/format/jpg",
					"blurred": "http://img.kaiyanapp.com/bee80be214092a815c0c8e236558619a.jpeg?imageMogr2/quality/60/format/jpg",
					"sharing": null,
					"homepage": "http://img.kaiyanapp.com/ecf1b57fe609518616a842c1232cf7cf.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"
				},
				"category": "音乐",
				"playUrl": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=121828&resourceType=video&editionType=default&source=aliyun",
				"duration": 246,
				"releaseTime": 1534381209000,
				"consumption": null,
				"collected": false,
				"actionUrl": "eyepetizer://ugcResourceDetail?id=121828&resourceType=video",
				"onlineStatus": "ONLINE",
				"count": 0
			},
			"reply": {
				"id": 1029897909421735936,
				"videoId": 121828,
				"videoTitle": "一听就开心！彩虹合唱团新歌「肥宅群侠传」 ",
				"message": "看到彩虹，肥宅就滚进来的一定不止我一个(´･ω･`)",
				"likeCount": 5,
				"showConversationButton": false,
				"parentReplyId": 0,
				"rootReplyId": 1029897909421735936,
				"ifHotReply": true,
				"liked": false,
				"parentReply": null,
				"user": {
					"uid": 300944793,
					"nickname": "Demo",
					"avatar": "http://img.wdjimg.com/image/account/ba0422c380e1537b8d7ba727f60a8b57_180_180.png",
					"userType": "NORMAL",
					"ifPgc": false,
					"description": null,
					"area": null,
					"gender": null,
					"registDate": 1502274552000,
					"releaseDate": null,
					"cover": null,
					"actionUrl": "eyepetizer://pgc/detail/300944793/?title=Demo&userType=NORMAL&tabIndex=1",
					"followed": false,
					"limitVideoOpen": false,
					"library": "BLOCK",
					"uploadStatus": "NORMAL",
					"bannedDate": null,
					"bannedDays": 0
				}
			}
		},
		"tag": null,
		"id": 0,
		"adIndex": -1
	    }
	     ......
	],
	"count": 10,
	"total": 0,
	"nextPageUrl": "http://baobab.kaiyanapp.com/api/v5/community/tab/dynamicFeeds?start=10&num=10",
	"adExist": false
}
```

* 结果分析：
	* `count`: `itemList`数组长度
	* `total`: 
	* `nextPageUrl`: 加载下一页API结构
	* `adExist`:
	* `itemList`: 数据列表
		* `adIndex`:
		* `id`:
		* `tag`:
		* `type`: 不同值的`type`对应下面不同结构的 `data`,这里以值**DynamicInfoCard**为例
		* `data`: 具体数据部分
			* `dataType`:上层结构中不同值的`type`对应不同数据结构的 `data`,这里以值**DynamicInfoCard**为例
			* `actionUrl`:网页版功能相关
			* `createDate`:日期
			* `dynamicType`:**reply**代表是评论
			* `merge`:
			* `mergeNickName`:
			* `mergeSubTitle`:
			* `text`:文本内容
			* `reply`:回复相关信息
				* `id`:评论Id
				* `ifHotReply`:该条评论是否**热评**
				* `likeCount`:评论点赞数
				* `liked`:
				* `message`:评论内容
				* `parentReply`:
				* `parentReplyId`:
				* `rootReplyId`:
				* `showConversationButton`:
				* `videoId`:评论的视频Id
				* `videoTitle`:评论的视频标题
				* `user`: 结构同下 `user`，不重复解释
			* `simpleVideo`:评论的视频的相关信息
				* `actionUrl`:
				* `category`:视频类别
				* `collected`:已收藏?
				* `consumption`:
				* `count`:
				* `description`:视频描叙信息
				* `duration`:视频持续时间，单位秒
				* `id`:视频Id
				* `onlineStatus`:
				* `playUrl`:播放地址
				* `releaseTime`:发表日期
				* `resourceType`:发表内容类型
				* `titile`:视频标题
				* `uid`:
				* `cover`:视频封面
					* `blurred`:模糊背景图片Url
					* `detail`:
					* `feed`:
					* `homePage`:
					* `sharing`:
			* `user`: 评论者相关信息
				* `actionUrl`:网页版功能相关
				* `area`:
				* `avatar`:评论者头像Url
				* `bannedData`:
				* `bannedDays`:
				* `cover`:
				* `description`:
				* `followed`:
				* `gender`:评论者性别
				* `ifPgc`:
				* `library`:
				* `limitVideoOpen`:
				* `nickName`:评论者名称
				* `registDate`:评论者注册日期
				* `releaseDate`:
				* `uid`:评论者Id
				* `uploadStatus`:
				* `userType`:
	

### 8.获取通知列表 
* 精简后的Url： http://baobab.kaiyanapp.com/api/v3/messages/tabList

* 特别注意：**游客**身份，通知列表恒为**互动**和**官方**

* 响应实例：
```
{
	"tabInfo": {
		"tabList": [{
			"id": 0,
			"name": "互动",
			"apiUrl": "http://baobab.kaiyanapp.com/api/v5/discovery/myMessage"
		}, {
			"id": 1,
			"name": "官方",
			"apiUrl": "http://baobab.kaiyanapp.com/api/v3/messages"
		}],
		"defaultIdx": 1
	}
}
```

* 结果分析：同 [6.获取用户关注内容列表](#tag6)
	

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





