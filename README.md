# Eyepetizer
低仿【开眼】

# 声明

以下所有 API 均由 __开眼__ 提供，本人采取非正常手段获取。获取与共享之行为或有侵犯 __开眼__ 权益的嫌疑。若被告知需停止共享与使用，本人会及时删除此页面与整个项目。请您暸解相关情况，并遵守相关协议。

# 开眼API 说明

* 不考虑用户登录情况

* Fidder抓包的开眼APK版本:4.3.381

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
   * `itemList`:
      * `type`:跟界面UI有关,值可能为"textCard":纯文本UI卡片式布局数据， "briefCard"：短数据卡片式布局数据, "followCard"：详情卡片式布局数据
               特别注意，type的类型有3种，故对应下面的`data`数据结构也有三种，我们这里只分析 "followCard"类型对应的 `data` 数据结构
      * `data`:
        * `dataType`: "followCard",我们这里只分析该种字段的数据结构
        * `header`: 
            * `id`: 
            * `title`: 
            * `font`:
            * `subTitle`:
            * `subTitleFont`: 
            * `textAlign`:
            * `cover`:
            * `label`: 
            * `actionUrl`: 
            * `labelList`:
            * `icon`: 
            * `iconType`: 
            * `description`:
            * `time`:
            * `showHateVideo`: 
        * `content`:
            * `type` :
            * `data`:
               * `dataType`
               * `id`:
               * `title`:
               * `description`:
               * `library`:
               * `consumption`:
                  * `collectionCount`:
                  * `shareCount`:
                  * `replyCount`:
               * `resourceType`:
               * `slogan`:
               * `provider`:
                  * `name`:
                  * `alias`:
                  * `icon`:
               * `category`:
               * `author`:
                  * `id`:
                  * `icon`:
                  * `name`:
                  * `description`:
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
               * `cover`:
                  * `feed`:
                  * `detail`:
                  * `blurred`:
                  * `sharing`:
                  * `homepage`:
               * `playUrl`:
               * `thumbPlayUrl`:
               * `duration`:
               * `webUrl`:
                  * `raw`:
                  * `forWeibo`:
               * `releaseTime`:
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
               * `collected`:
               * `played`:
               * `lastViewTime`:
               * `playlists`:
               * `src`:
               * `tags`:数组类型
                  * `id`:
                  * `name`:
                  * `actionUrl`:
                  * `adTrack`:
                  * `desc`:
                  * `bgPicture`:
                  * `headerImage`:
                  * `tagRecType`:
                  * `childTagList`:
                  * `childTagIdList`:
               * `playInfo`:数组类型
                  * `height`:
                  * `width`:
                  * `name`:
                  * `type`:
                  * `url`:
                  * `urlList`:数组类型
                     * `name`:
                     * `url`:
                     * `size`:
               * `labelList`:数组类型，暂时没发现数据 ╮(╯▽╰)╭
               * `subtitles`:数组类型，暂时没发现数据 ╮(╯▽╰)╭
            * `tag`:
            * `id`:
            * `adIndex`:
      * `adTrack`:
   * `count`:
   * `total`:
   * `nextPageUrl`:
   * `adExist`:



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

}
```

### 5.栏目详情
* 精简后的Url: `http://baobab.kaiyanapp.com/api/v5/index/tab/{tabName}`

* 参数分析：
  * `tabName`: 栏目名称

* 响应实例：
```
{

}
```

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





